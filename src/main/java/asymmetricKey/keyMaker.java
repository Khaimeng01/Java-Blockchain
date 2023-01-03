/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asymmetricKey;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class keyMaker {
    private static final String algorithm = "RSA";
	
	private KeyPairGenerator keygen;
	private KeyPair keyPair;
	private static PublicKey publicKey;
	private static PrivateKey privateKey;
	
	public static PublicKey getPublicKey() {
		return publicKey;
	}
	
	public static PrivateKey getPrivateKey() {
		return privateKey;
	}

	public keyMaker() {
		try {
			keygen = KeyPairGenerator.getInstance(algorithm);
			keygen.initialize(1024);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * create
	 */
	public static void create()
	{
		keyMaker myKeyMaker = new keyMaker();
		//generate keyPair
		myKeyMaker.keyPair = myKeyMaker.keygen.generateKeyPair();
		//get public key
		publicKey = myKeyMaker.keyPair.getPublic();
		//get private key
		privateKey  = myKeyMaker.keyPair.getPrivate();
		
	}
	
	/**
	 * put the key in a specified file path
	 */
	public static void put(String path, byte[] key)
	{
		File file = new File(path );
		file.getParentFile().mkdirs();
		try {
			Files.write(Paths.get(path), key, StandardOpenOption.CREATE);
			System.out.println( "Complete" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
