package io.data.encryption;

import io.data.encryption.config.PropertiesConfiguration;
import io.data.encryption.utils.EncryptUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import javax.crypto.IllegalBlockSizeException;

@SpringBootTest(classes = {PropertiesConfiguration.class})
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
class EncryptionApplicationTests {



    @Test
    void encrypt() {
        Assertions.assertDoesNotThrow(()->EncryptUtils.encrypt("Test"));
    }

    @Test
    void decryptBadInput() {
        Assertions.assertThrows(IllegalBlockSizeException.class,()->EncryptUtils.decrypt("Test"));
    }

    @Test
    void decrypt() {
        Assertions.assertDoesNotThrow(()->{
            String encryptedString = EncryptUtils.encrypt("test");
            EncryptUtils.decrypt(encryptedString);
        });
    }

}
