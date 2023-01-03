/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package digitalSignature;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author laikh
 */
public class KeyPairAccess {
    /**
	 * getPublicKey
	 */
	public static PublicKey getPublicKey() throws Exception
	{
                String path = "DigitalSignature.txt";
		byte[] keyBytes = Files.readAllBytes(Paths.get(path));
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		return KeyFactory.getInstance("RSA").generatePublic(spec);
	}
	
	/**
	 * getPrivateKey
	 */
	public static PrivateKey getPrivateKey() throws Exception
	{
                String path = "DigitalSignature.txt";
		byte[] keyBytes = Files.readAllBytes(Paths.get(path));
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		return KeyFactory.getInstance("RSA").generatePrivate(spec);
	}
}
