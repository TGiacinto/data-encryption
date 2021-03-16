# Data-Encryption

The data encryption library allows to obtain an encrypted text from a readable text and to save it into a database. The encryption ensures security against any attack which attempts to retrieve data from db.
## Usage

Add @EnableEncryptData annotation in the main spring class

Add these properties
```
io.data.encryption.secret-key=<YOUR_SECRET_KEY>
io.data.encryption.secret-key-algorithm=AES
io.data.encryption.iv-key=<YOUR_IV_KEY>
io.data.encryption.algorithm=AES/CBC/PKCS5Padding
```

Add @Encrypt annotation on entity field.

```java
import io.data.encryption.annotations.Encrypt;

import javax.persistence.Id;

public class MyEntity {

    @Id
    private String id;
    @Encrypt
    private String name;

    private int age;
    
    //getter and setter ...
}
```

### Author

TGiacinto
Alelli22
