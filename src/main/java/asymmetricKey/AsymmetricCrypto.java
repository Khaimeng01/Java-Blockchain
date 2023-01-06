/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package asymmetricKey;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author User
 */
public class AsymmetricCrypto {
    
    private Cipher cipher;
    private static final String algo = "AES";
//    public AsymmetricCrypto(){
//        this("RSA");
//    }

    public AsymmetricCrypto() {
       
        try {   
            cipher = Cipher.getInstance(algo);
        }   catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
        }
    }
    
   //encryption
    public String encrypt (String data, PublicKey key) throws Exception {
        String ciphertext = null;
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherBytes = cipher.doFinal(data.getBytes());
        ciphertext= Base64.getEncoder().encodeToString(cipherBytes);
        return ciphertext;
    }

   //decryption
    public String decrypt (String ciphertext, PrivateKey key) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherBytes = Base64.getDecoder().decode(ciphertext);
        byte[] dataBytes=(cipher.doFinal(cipherBytes));
        return new String(dataBytes);
    }
} 
