/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package digitalSignature;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author laikh
 */
public class MyKeyPair {
    private static final String algorithm = "RSA";
	
	private KeyPairGenerator keygen;
	private static KeyPair keyPair;
	private static PublicKey publicKey;
	private static PrivateKey privateKey;
	
	public static PublicKey getPublicKey() {
		return publicKey;
	}
	
	public static PrivateKey getPrivateKey() {
		return privateKey;
	}

	public MyKeyPair() {
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
	public static PrivateKey create() throws IOException
	{
		MyKeyPair myKeyMaker = new MyKeyPair();
		//generate keyPair
		keyPair = myKeyMaker.keygen.generateKeyPair();
                PublicKey publicKey = keyPair.getPublic();
                System.out.print(publicKey);
                PrivateKey privateKey = keyPair.getPrivate();
                FileWriter write = new FileWriter("DigitalSignature.txt");
                put(publicKey.getEncoded());
                put(privateKey.getEncoded());
                return privateKey;
		
	}
	
	/**
	 * put the key in a specified file path
	 */
	public static void put( byte[] keyBytes )
	{
            System.out.println("Test_1");
            BufferedReader br;
            try {
               
               File file = new File("DigitalSignature.txt");
//               br = new BufferedReader(new FileReader("DigitalSignature.txt"));
//               br.readLine();
             
               BufferedWriter bw = new BufferedWriter(new FileWriter("DigitalSignature.txt",true));
               bw.write(String.valueOf(keyBytes)+"\n");
               bw.close();
               System.out.println("New Admin successfully registered!");

           } catch (FileNotFoundException ex) {

           } catch (IOException e) {
               System.out.println(e);
           }

	}
        

}


//		File f = new File(path );
//		f.getParentFile().mkdirs();
//		try {
//			Files.(Paths.get(path), keyBytes, StandardOpenOption.CREATE);
//			System.out.println( "Keypair exported to '"+ path + "'" );
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
