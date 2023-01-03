/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asymmetricKey;

import java.awt.RenderingHints.Key;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author User
 */
public class PredefinedCharsSecretKey {
    
    	/**
	 * symmetric algorithm
	 */
	private static final String ASYMM_ALGORITHM = "RSA";
	
	/**
	 * predefined chars (secret)
	 */
	private static final String SECRET_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static SecretKeySpec create()    
	{
		int keysize = 16;
		return new SecretKeySpec( Arrays.copyOf(SECRET_CHARS.getBytes(), keysize), ASYMM_ALGORITHM );
	}
    
}
