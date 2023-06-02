package edu.upc.dsa;

import edu.upc.dsa.util.PasswordSecurity;
import org.junit.Assert;
import org.junit.Test;

public class PasswordSecurityTest {

    @Test
    public void testEncryptDecrypt() {
        String password = "123456789";
        String encryptedPassword;
        String decryptedPassword;
        try {
            encryptedPassword = PasswordSecurity.encrypt(password);
            System.out.println(encryptedPassword);
            decryptedPassword = PasswordSecurity.decrypt(encryptedPassword);
            System.out.println(decryptedPassword);

            Assert.assertEquals(password, decryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
