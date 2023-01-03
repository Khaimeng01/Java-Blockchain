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
    
    public AsymmetricCrypto(String algorithm) {
        
        try {   
            cipher = Cipher.getInstance(algorithm);
        }   catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
        }
    }
    
   //encryption
    public String encrypt (String data, PublicKey key) throws Exception {
        String ciphertext = "";
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherBytes = cipher.doFinal(data.getBytes());
        ciphertext= Base64.getEncoder().encodeToString(cipherBytes);
        return ciphertext;
    }

   //decryption
    public String decrypt (String ciphertext, PrivateKey key) throws Exception {
        String origin = "";
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherBytes = Base64.getDecoder().decode(ciphertext);
        origin = new String(cipher.doFinal(cipherBytes));
        return origin;
    }
} 
