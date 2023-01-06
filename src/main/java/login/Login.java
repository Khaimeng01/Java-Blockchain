/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
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
        String row, txtUsername, txtPassword,row1;
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            br.readLine();
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
                                Key key = new SecretKeySpec(encodedKey,0,encodedKey.length, "AES");
                                String returnPass  = symm.decrypt(txtPassword, key);
                                if(returnPass.equals(password)){
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

