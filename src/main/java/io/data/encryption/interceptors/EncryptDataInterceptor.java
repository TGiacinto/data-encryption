package io.data.encryption.interceptors;

import io.data.encryption.annotations.Encrypt;
import io.data.encryption.utils.EncryptUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

@Component
@CommonsLog
public class EncryptDataInterceptor extends EmptyInterceptor {

    public enum DataEncryption {
        ENCRYPT, DECRYPT
    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

        boolean isChanged = false;

        for (int i = 0; i < state.length; i++) {
            try {
                state[i] = EncryptUtils.decrypt((String) state[i]);
                isChanged = true;
            } catch (Exception ignored) {
                //silent is gold
            }
        }
        return isChanged;
    }

    @Override
    public void postFlush(Iterator entities) {
        while (entities.hasNext()) {
            Object entity = entities.next();
            encryptOrDecrypt(entity, DataEncryption.DECRYPT);
        }


    }


    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        encryptOrDecrypt(entity, DataEncryption.ENCRYPT);
        return super.onSave(entity, id, state, propertyNames, types);
    }


    private void encryptOrDecrypt(Object entity, DataEncryption dataEncryption) {
        getFields(entity).forEach(field -> {
            try {
                field.setAccessible(true);
                String value = (String) field.get(entity);
                field.set(entity, DataEncryption.ENCRYPT.equals(dataEncryption) ? EncryptUtils.encrypt(value) : EncryptUtils.decrypt(value));
                field.setAccessible(false);
            } catch (IllegalAccessException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
                log.error(e);
            }
        });

    }

    private Stream<Field> getFields(Object entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(Encrypt.class) != null)
                .filter(field -> field.getType().isAssignableFrom(String.class));
    }


}
