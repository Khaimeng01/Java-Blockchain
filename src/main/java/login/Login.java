/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import com.mycompany.core.Appointment;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import symmetricKey.randomsecretkey;
import symmetricKey.symmetriccrypto;

/**
 *
 * @author Arvind
 */
public class Login {
    String username, password;
    final static String FILENAME = "user.txt";

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean verify() {
        System.out.println("INSIDE");
        String row, txtUsername, txtPassword,row1;
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            System.out.println("A1");
            br.readLine();
             System.out.println("A2");
            while ((row = br.readLine()) != null) {
                String[] field = row.split("\\|\\|");
                txtUsername = field[0];
                txtPassword = field[1];
                symmetriccrypto symm = new symmetriccrypto();
               if (username.equals(txtUsername)) {
                    try{
                        BufferedReader brTest = new BufferedReader(new FileReader("randomkey.txt"));
                        while ((row1 = brTest.readLine()) != null) {
                            String[] field1 = row1.split("\\|\\|");
                            String encryptedUsername = field1[0];
                            String encryptedTxtKey = field1[1];
                            if(encryptedUsername.equals(username)){
                                byte[] encodedKey = Base64.getDecoder().decode(encryptedTxtKey);
//                                System.out.println("BYTE "+Arrays.toString(encodedKey));
                                System.out.println("A8");
//                                SecretKeySpec key = new SecretKeySpec(encryptedTxtKey.getBytes("UTF-8"), "AES");
//                                SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES"); 
                                System.out.println("EncodedKey Length "+encodedKey.length);
                                Key key = new SecretKeySpec(encodedKey,0,encodedKey.length, "AES");
                                System.out.println("A9");
                                System.out.println("Encrypted Key "+ key.toString()+"\t"+key.getAlgorithm()+"\t"+key.getFormat());
                                String returnPass  = symm.decrypt(txtPassword, key);
                                System.out.println("Return Pass : "+returnPass);
                                if(returnPass.equals(password)){
                                    System.out.println("RETURN HERE");
                                    return true;
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}

