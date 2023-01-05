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
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.KeyGenerator;


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
			keygen = KeyPairGenerator.getInstance("RSA");
			keygen.initialize(1024);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * create
	 */
	public static PrivateKey dSCreate() throws IOException
	{
		MyKeyPair myKeyMaker = new MyKeyPair();
		keyPair = myKeyMaker.keygen.generateKeyPair();
                PublicKey publicKey = keyPair.getPublic();
                PrivateKey privateKey = keyPair.getPrivate();
                FileWriter write = new FileWriter("DigitalSignature.txt");
                dSPut(publicKey.getEncoded());
                return privateKey;
		
	}
	
	/**
	 * put the key in a specified file path
	 */
	public static void dSPut( byte[] keyBytes )
	{
            BufferedReader br;
            try {
               
               File file = new File("DigitalSignature.txt");             
               BufferedWriter bw = new BufferedWriter(new FileWriter("DigitalSignature.txt",true));
               System.out.println("INSIDE DATA"+Arrays.toString(keyBytes));
               
               String a = Base64.getEncoder().encodeToString(keyBytes);
               System.out.println("String data"+a);
               bw.write(a);
               bw.close();
               System.out.println("New Admin successfully registered!");

           } catch (FileNotFoundException ex) {

           } catch (IOException e) {
               System.out.println(e);
           }

	}
        
//        public static void put2( byte[] keyBytes )
//	{
//                System.out.print("B_1");
//                String path = "DigitalSignature.txt";
//                System.out.print("B_2");
//		File f = new File(path );
//                System.out.print("B_3");
//		f.getParentFile().mkdirs();
//		try {
//                        System.out.print("B_4");
//			Files.write(Paths.get(path), keyBytes, StandardOpenOption.CREATE);
//			System.out.println( "Keypair exported to '"+ path + "'" );
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
        

}

