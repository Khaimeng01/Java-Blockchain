/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.LoginView;

/**
 *
 * @author Arvind
 */
public class Main {

    public static void main(String[] args) {
        
        try {
            Main main = new Main();
            main.FileScanner();
            
        } catch (RemoteException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        new LoginView().setVisible(true);
    }


    public void FileScanner() throws RemoteException {
        String USERFILENAME = "user.txt";
        String RANDOMFILENAME = "randomkey.txt";
        String APPFILENAME = "appointment.txt";
        String LEDGEERFILENAME = "myLedgerFile.json";
        String DIGITALFILENAME = "DigitalSignature.txt";
        String USERFILEHEADER = "Username||Password" + "\n";
        String RANDOMFILEHEADER = "Username||Key" + "\n";
        String APPFILEHEADER = "ID||Date||PatientID||DoctorName||DepartmentName||DigitalSignature" + "\n";
        String DIGITALFILEHEADER = "patientID||DigitalSignature" + "\n";
        //SCANS FOR user.txt FILE
        try{
            File file = new File(USERFILENAME);
            if (file.exists() && file.isFile()) {
                System.out.println("user text file exists!");
                
            } else {
                try {
                    FileWriter write = new FileWriter(USERFILENAME);
                    System.out.println("user.txt FILE SUCCESSFULLY CREATED!");
                    write.close();
                    
                } catch (IOException e) {
                    System.out.println("File not found!");
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        
        //tries to read user.txt file and automatically print header and admin.
        BufferedReader br;
        try {
            File file = new File(USERFILENAME);
            br = new BufferedReader(new FileReader(USERFILENAME));
            br.readLine();
            
            if (file.length() == 0) {
                
                BufferedWriter bw = new BufferedWriter(new FileWriter(USERFILENAME));
                bw.write(USERFILEHEADER);
                bw.close();
                System.out.println("HEADER for user.txt successfully created!");                
            } else {
                System.out.println("HEADER has already been created!");
            }
            
        } catch (FileNotFoundException ex) {
            
        } catch (IOException e) {
            System.out.println(e);
        }

        //SCANS FOR randomkey.txt FILE
        try{
            File file = new File(RANDOMFILENAME);
            if (file.exists() && file.isFile()) {
                System.out.println("randomkey text file exists!");
                
            } else {
                try {
                    FileWriter write = new FileWriter(RANDOMFILENAME);
                    System.out.println("random.txt FILE SUCCESSFULLY CREATED!");
                    write.close();
                    
                } catch (IOException e) {
                    System.out.println("File not found!");
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        
        //tries to read randomkey.txt file and automatically print header and admin.
        try {
            File file = new File(RANDOMFILENAME);
            br = new BufferedReader(new FileReader(RANDOMFILENAME));
            br.readLine();
            
            if (file.length() == 0) {
                
                BufferedWriter bw = new BufferedWriter(new FileWriter(RANDOMFILENAME));
                bw.write(RANDOMFILEHEADER);
                bw.close();
                System.out.println("HEADER for random.txt successfully created!");                
            } else {
                System.out.println("HEADER has already been created!");
            }
            
        } catch (FileNotFoundException ex) {
            
        } catch (IOException e) {
            System.out.println(e);
        }

        //SCANS FOR appointment.txt FILE
        try{
            File file = new File(APPFILENAME);
            if (file.exists() && file.isFile()) {
                System.out.println("appointment text file exists!");
                
            } else {
                try {
                    FileWriter write = new FileWriter(APPFILENAME);
                    System.out.println("appointment.txt FILE SUCCESSFULLY CREATED!");
                    write.close();
                    
                } catch (IOException e) {
                    System.out.println("File not found!");
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        
        //tries to read randomkey.txt file and automatically print header and admin.
        try {
            File file = new File(APPFILENAME);
            br = new BufferedReader(new FileReader(APPFILENAME));
            br.readLine();
            
            if (file.length() == 0) {
                
                BufferedWriter bw = new BufferedWriter(new FileWriter(APPFILENAME));
                bw.write(APPFILEHEADER);
                bw.close();
                System.out.println("HEADER for appointment.txt successfully created!");                
            } else {
                System.out.println("HEADER has already been created!");
            }
            
        } catch (FileNotFoundException ex) {
            
        } catch (IOException e) {
            System.out.println(e);
        }

        //SCANS FOR myLedgerFile.json FILE
        try{
            File file = new File(LEDGEERFILENAME);
            if (file.exists() && file.isFile()) {
                System.out.println("Ledger json file exists!");
                
            } else {
                try {
                    FileWriter write = new FileWriter(LEDGEERFILENAME);
                    System.out.println("myLedgerFile.json FILE SUCCESSFULLY CREATED!");
                    write.close();
                    
                } catch (IOException e) {
                    System.out.println("File not found!");
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        
        //SCANS FOR DigitalSignature.txt FILE
        try{
            File file = new File(DIGITALFILENAME);
            if (file.exists() && file.isFile()) {
                System.out.println("DigitalSignaturetext file exists!");
                
            } else {
                try {
                    FileWriter write = new FileWriter(DIGITALFILENAME);
                    System.out.println("DigitalSignature.txt FILE SUCCESSFULLY CREATED!");
                    write.close();
                    
                } catch (IOException e) {
                    System.out.println("File not found!");
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        
        //tries to read randomkey.txt file and automatically print header and admin.
        try {
            File file = new File(DIGITALFILENAME);
            br = new BufferedReader(new FileReader(DIGITALFILENAME));
            br.readLine();
            
            if (file.length() == 0) {
                
                BufferedWriter bw = new BufferedWriter(new FileWriter(DIGITALFILENAME));
                bw.write(DIGITALFILEHEADER);
                bw.close();
                System.out.println("HEADER for DigitalSignature.txt successfully created!");                
            } else {
                System.out.println("HEADER has already been created!");
            }
            
        } catch (FileNotFoundException ex) {
            
        } catch (IOException e) {
            System.out.println(e);
        }
        
        
    }
}