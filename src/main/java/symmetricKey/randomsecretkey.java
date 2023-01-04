/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package symmetricKey;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author User
 */
public class randomsecretkey {
    
    private static final String ALGORITHM = "AES";
    	public static Key create() {
		try {
			KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
			kg.init(256, new SecureRandom());
			return kg.generateKey();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
}
