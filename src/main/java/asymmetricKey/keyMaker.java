/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asymmetricKey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
		keyPair = myKeyMaker.keygen.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
                PrivateKey privateKey = keyPair.getPrivate();
                FileWriter write = new FileWriter("AS.txt");
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

	}	
}
