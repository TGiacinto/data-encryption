# Data-Encryption

The data encryption library allows you to save data in the database from a readable text to an encrypted text of
unreadable characters

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

    private int age;

    //getter and setter ...
}
```

### How to install

Run in your terminal: mvn clean install and in your pom add:

```
   <dependency>
    <groupId>io.data</groupId>
    <artifactId>encryption</artifactId>
    <version>1.0.0</version>
   </dependency>
```

Or run in your terminal: mvn clean package,  copy the jar inside your project and add this in the pom :

```
   <dependency>
    <groupId>io.data</groupId>
    <artifactId>encryption</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>YOUR_PATH/encryption-1.0.0.jar</systemPath>
   </dependency>
```

Soon it will be released on maven repository

#### Authors

[TGiacinto](https://github.com/TGiacinto).  
[Alessio22](https://github.com/Alessio22).  
