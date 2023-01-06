/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hashing;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Arvind
 */
public class Hasher {
	
	/*SHA family includes MD5 */
	
	public String md5(String input) { //its just normal method name
		
		return hash(input, "MD5");
	}
	
	public String sha256(String input) { //its just normal method name
		
		return hash(input, "SHA-256");
	}
        
        public String sha256(byte[] arr){
            return hash(arr, "SHA-256");
        }
	
	public String sha384(String input) { //its just normal method name
		
		return hash(input, "SHA-384");
	}
	
	public String sha512(String input) { //its just normal method name
		
		return hash(input, "SHA-512");
	}
	/**
	 * hash(String, String) : String
	 */
	private String hash(String input, String algorithm) {
		MessageDigest md; // from java.security lib
		try {
			//instantiate md
			md = MessageDigest.getInstance(algorithm);
			//fetch input to md
			md.update(input.getBytes());
                        
                        //adding securtiy using salt, prepend

			//digest
			byte[] hashbytes =  md.digest();
                        
                        //adding securtiy using salt, append
			
			//2) byte[] to HEX -typically seen online, using external API
			return Hex.encodeHexString(hashbytes);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
        
        
        
        /**
	 * hash(byte[], String) : String
	 */
        private String hash(byte[] input, String algorithm) {
		MessageDigest md; // from java.security lib
		try {
			//instantiate md
			md = MessageDigest.getInstance(algorithm);
			//fetch input to md
			md.update(input);
                        
                        //adding securtiy using salt, prepend
                        
			//digest
			byte[] hashbytes =  md.digest();
			//convert to String;
                        
                        //adding securtiy using salt, append
			
			//2) byte[] to HEX -typically seen online, using external API
			return Hex.encodeHexString(hashbytes);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}