package io.data.encryption.annotations;

import io.data.encryption.config.EncryptConfiguration;
import io.data.encryption.config.PropertiesConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({PropertiesConfiguration.class,EncryptConfiguration.class})
@Component
public @interface EnableEncryptData {

}
