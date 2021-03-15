# Data-Encryption

The data encryption library allows you to save data in the database from a readable text to an encrypted text of unreadable characters
## Usage

Add @EnableEncryptData annotations in the main spring class

Add this property
```
io.data.encryption.secret-key=<YOUR_SECRET_KEY>
io.data.encryption.secret-key-algorithm=AES
io.data.encryption.iv-key=<YOUR_IV_KEY>
io.data.encryption.algorithm=AES/CBC/PKCS5Padding
```

Add @Encrypt on entity field.

```java
import io.data.encryption.annotations.Encrypt;

import javax.persistence.Id;

public class MyEntity {

    @Id
    private String id;
    @Encrypt
    private String name;

    private int eta;
    
    //getter and setter ...
}
```

### Author

TGiacinto
Alelli22
