package io.data.encryption.config;

import io.data.encryption.interceptors.EncryptDataInterceptor;
import io.data.encryption.utils.EncryptUtils;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Configuration
@CommonsLog
public class EncryptConfiguration implements HibernatePropertiesCustomizer {


    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.interceptor", interceptor());
    }


    @Bean
    public EncryptDataInterceptor interceptor() {
        return new EncryptDataInterceptor();
    }


    private void print(){
        log.info("SECRET_KEY: ".concat("**********"));
        log.info("IV_KEY: ".concat("**********"));
        log.info("SECRET_KEY_ALGORITHM: ".concat(PropertiesConfiguration.SECRET_KEY_ALGORITHM));
        log.info("ALGORITHM: ".concat(PropertiesConfiguration.ALGORITHM));
    }

    @Bean
    public void isValidKey() throws BadPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException {

        print();

        String test = "dataEncryption";
        String encrypt = EncryptUtils.encrypt(test);
        String decrypt = EncryptUtils.decrypt(encrypt);
        Assert.isTrue(test.equals(decrypt), "Failed test keys");
        log.info("Encrypt data was successfully enabled");
    }

}
