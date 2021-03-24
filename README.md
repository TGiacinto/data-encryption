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

Query to get the encrypted data
``` java
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(@Encrypt String name);
}
```


### How to install

Add this dependency
```
 <dependency>
    <groupId>io.data</groupId>
    <artifactId>encryption</artifactId>
    <version>1.0.1</version>
 </dependency>
```


Or run in your terminal :

- mvn clean package
- copy the jar into your project
- add in pom 👇

```
 <dependency>
    <groupId>io.data</groupId>
    <artifactId>encryption</artifactId>
    <version>1.0.0</version>
    <scope>system</scope>
    <systemPath>YOUR_PATH/encryption-1.0.1.jar</systemPath>
 </dependency>
```

#### Authors

[TGiacinto](https://github.com/TGiacinto).  
[Alessio22](https://github.com/Alessio22).  
