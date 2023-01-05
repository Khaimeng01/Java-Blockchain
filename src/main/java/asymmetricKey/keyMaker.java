/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asymmetricKey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class keyMaker {
    private static final String algorithm = "AES";
	
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
	public  PublicKey aSCreate() throws IOException
	{
		keyMaker myKeyMaker = new keyMaker();
                System.out.println("A_2");
		keyPair = myKeyMaker.keygen.generateKeyPair();
                System.out.println("A_3");
		PublicKey publicKey = keyPair.getPublic();
                System.out.println("A_4");
                PrivateKey privateKey = keyPair.getPrivate();
                System.out.println("A_5");
                FileWriter write = new FileWriter("AS.txt");
                System.out.println("A_6");
                aSPut(privateKey.getEncoded());
                return publicKey;
	}
	
        public static void aSPut( byte[] keyBytes )
	{
            BufferedReader br;
            try {
//               File file = new File("AS.txt");             
               BufferedWriter bw = new BufferedWriter(new FileWriter("AS.txt",true));
               String a = Base64.getEncoder().encodeToString(keyBytes);
               bw.write(a);
               bw.close();
               
           } catch (FileNotFoundException ex) {

           } catch (IOException e) {
               System.out.println(e);
           }
           System.out.println("A_8");

	}
        

	/**
	 * put the key in a specified file path
	 */
//	public static void put(String path, byte[] key)
//	{
//		File file = new File(path );
//		file.getParentFile().mkdirs();
//		try {
//			Files.write(Paths.get(path), key, StandardOpenOption.CREATE);
//			System.out.println( "Complete" );
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
