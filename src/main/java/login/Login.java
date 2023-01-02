/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
        String row, txtUserId, txtUsername, txtPassword;
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            br.readLine();
            while ((row = br.readLine()) != null) {
                String[] field = row.split("\\|\\|");
                txtUserId = field[0];
                txtUsername = field[1];
                txtPassword = field[2];

                if (username.equals(txtUsername) && password.equals(txtPassword)) {
//                    if (txtPosition.equals("E")) {
//                        System.out.println("This is a Sales Executive!");
//                        return new SalesExec(txtUserId, txtUsername, txtPositionId);
//                    } else if (txtPosition.equals("A")) {
//                        System.out.println("This is an Admin!");
//                        return new Admin(txtUserId, txtUsername, txtPositionId);
//                    }
                    return true;
                }
//                else {
//                    System.out.println("No position assigned!");
//                    return new Admin("A000", "Username Not Found", "A000");
//                } 

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}

