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
        System.out.println("B_1");
	String cipherText = null;
        System.out.println("B_2");
	// init
        System.out.println("B_3");
	cipher.init(Cipher.ENCRYPT_MODE, key);
	// encrypt
        System.out.println("B_4");
	byte[] cipherBytes = cipher.doFinal(data.getBytes());
	// convert to string
        System.out.println("B_5");
	cipherText = Base64.getEncoder().encodeToString(cipherBytes);
	return cipherText;
}

public String decrypt(String cipherText, Key key) throws Exception {
        // init
        cipher.init(Cipher.DECRYPT_MODE, key);
        // convert to byte[]
        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        // decrypt
        byte[] dataBytes = cipher.doFinal(cipherBytes);
        return new String(dataBytes);
//        System.out.println("B_1");
//	// init
//	cipher.init(Cipher.DECRYPT_MODE, key);
//        System.out.println("B_2");
//	// convert to byte[]
////        cipher.update(ciphetText.)
////	byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
//        cipher.update(cipherText.getBytes());
////        System.out.println("B_3");
////        System.out.println("CipherBytes "+Arrays.toString(cipherBytes));
//	// decrypt
//	byte[] dataBytes = cipher.doFinal();
//        System.out.println("dataBytes "+Arrays.toString(dataBytes));
//        System.out.println("B_4");
//	return new String("Tester");
    }



        
}

        
        
        
