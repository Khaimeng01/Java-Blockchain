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
import java.util.Arrays;
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
//       //Genesis Block (Starter Block)
//       Block genesis = new Block("0");
//       System.out.println(genesis+"1");
//
//       String tranx1 = "alice|bob|10.0|credit";
//       String tranx2 = "mutu|bob|20.0|credit";
//       TranxCollection tranx = new TranxCollection();
//       tranx.add(tranx1);
//       tranx.add(tranx2);
//
//       //New block
//       Block newBlock = new Block(genesis.getHeader().getCurrHash());
//       newBlock.setTranxs(tranx);
//       System.out.println(newBlock);
//        test1();
//test2();
    }


    public void FileScanner() throws RemoteException {
        String USERFILENAME = "user.txt";
        String RANDOMFILENAME = "randomkey.txt";
        String APPFILENAME = "appointment.txt";
        String LEDGEERFILENAME = "myLedgerFile.json";
        String USERFILEHEADER = "Username||Password" + "\n";
        String RANDOMFILEHEADER = "Username||Key" + "\n";
        String APPFILEHEADER = "ID||Date||PatientID||DoctorName||DepartmentName||DigitalSignature" + "\n";
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
    }
    
//    static final String MASTER_DIR = "master";
//    static final String MASTER_BINARY = MASTER_DIR+"/mychain";
//    
//    static void test1(){
//        Blockchain bc = Blockchain.getInstance(MASTER_BINARY);
//        //String tranx1 = "bob|alice|debit|50";
//        Patient p1 = new Patient("1", "Bob", "Sam", "0031231301", "012334556788", true );
//        Patient p2 = new Patient("2", "Alex", "John", "0031231301", "012334556788", true );
//       
//        //String tranx2 = "pete|bob|credit|200";
//        if ( !( new File(MASTER_DIR).exists() ) ) {
//            /* make a dir if not found */            
//            new File( MASTER_DIR ).mkdir();
//            bc.genesis();
//        } else {
//            TranxCollection tranxs = new TranxCollection();
//            tranxs.add(p1);
//            tranxs.add(p2);
//            String prevHash = bc.get().getLast().getHeader().getCurrHash();
//            Block newBlock = new Block( prevHash );
//            newBlock.setTranxs(tranxs);
//            bc.nextBlock(newBlock);
//        }

//    }
    
//    static void test2(){
//         //generate merkle tree sample
//        
//        //dummy transaciton
//        String[] arr = {
//            "alice|bob|credit|rm10",
//            "alice|bob|debit|rm2@",
//            "a1ice|bob|credit|rm30",
//            "alice|bob|debit|rm40"
//        };
//        
//        MerkleTree mt = MerkleTree.getInstance( Arrays.asList(arr) ) ;
//        mt.build();
//        String root = mt . getRoot();
//        System.out.println( "Merkle Root: " + root); 
//    }


}