/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package digitalSignature;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;


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
	public static PrivateKey dSCreate(String ID) throws IOException
	{
		MyKeyPair myKeyMaker = new MyKeyPair();
		keyPair = myKeyMaker.keygen.generateKeyPair();
                PublicKey publicKey = keyPair.getPublic();
                PrivateKey privateKey = keyPair.getPrivate();
                dSPut(publicKey.getEncoded(),ID);
                return privateKey;
		
	}
	
	/**
	 * put the key in a specified file path
	 */
	public static void dSPut( byte[] keyBytes,String ID)
	{
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("DigitalSignature.txt",true))){
               System.out.println("INSIDE DATA"+Arrays.toString(keyBytes));
               String a = Base64.getEncoder().encodeToString(keyBytes);
               System.out.println("String data"+a);
               bw.write(ID+ "||"+a+ "\n");
               bw.close();
               System.out.println("New Admin successfully registered!");

           } catch (FileNotFoundException ex) {

           } catch (IOException e) {
               System.out.println(e);
           }

	}
}

