package io.data.encryption.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@EnableConfigurationProperties(PropertiesConfiguration.class)
@ConfigurationProperties(prefix = "io.data.encryption")
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class PropertiesConfiguration {

    public static String SECRET_KEY;
    public static String SECRET_KEY_ALGORITHM = "AES";
    public static String IV_KEY;
    public static String ALGORITHM = "AES/CBC/PKCS5Padding";


    public void setAlgorithm(String ALGORITHM) {
        PropertiesConfiguration.ALGORITHM = ALGORITHM;
    }

    public void setIvKey(String ivKey) {
        IV_KEY = ivKey;
    }

    public void setSecretKey(String secretKey) {
        SECRET_KEY = secretKey;
    }

    public void setSecretKeyAlgorithm(String secretKeyAlgorithm) {
        SECRET_KEY_ALGORITHM = secretKeyAlgorithm;
    }
}
