/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package symmetricKey;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author User
 */
public class symmetriccrypto {
  
    private static final String SECRET = "bcdisawesome";
    
    private static final String ALGORITHM = "AES";
    private Key key;
    
    public Key getKey() {
        return key;
}
    
private static Key genKey() {
    return new SecretKeySpec (Arrays.copyOf(SECRET.getBytes(), 16), ALGORITHM);
}

private Cipher cipher;

public symmetriccrypto() {
    try{
        cipher = Cipher.getInstance(ALGORITHM);
        key = genKey();
    } catch (NoSuchAlgorithmException | NoSuchPaddingException ex) {
        ex.printStackTrace();
    }    

}

public String encrypt(String data, Key key) throws Exception {
	String cipherText = null;
	// init
	cipher.init(Cipher.ENCRYPT_MODE, key);
	// encrypt
	byte[] cipherBytes = cipher.doFinal(data.getBytes());
	// convert to string
	cipherText = Base64.getEncoder().encodeToString(cipherBytes);
	return cipherText;
}

public String decrypt(String cipherText, Key key) throws Exception {
        System.out.println("1234");
	// init
	cipher.init(Cipher.DECRYPT_MODE, key);
        System.out.println("JOEL");
	// convert to byte[]
	byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        System.out.println("James");
	// decrypt
	byte[] dataBytes = cipher.doFinal(cipherBytes);
        System.out.println("Tom");
	return new String(dataBytes);
    }

    public String encrypt(char[] password, Key key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String decrypt(String passwordstr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
}

        
        
        
