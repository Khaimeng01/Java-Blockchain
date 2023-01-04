/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.core;

import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

/**
 *
 * @author Arvind
 */
public class Blockchain {
    private static LinkedList<Block> db = new LinkedList<>();
    /* Singleton pattern */    private static Blockchain _instance;
    public static Blockchain getInstance( String chainFile )
    {
        if( _instance == null )
                _instance = new Blockchain( chainFile );
        return _instance;
    }
    private String ledgerFile, chainFile;
    private Blockchain( String chainFile )
    {   super(); //invoke superclass's constructor        
        this.chainFile = chainFile+".bin";
        this.ledgerFile = "myLedgerFile.json";
    }
    /* Singleton pattern */    
    /**     * genesis()     */    
    public void genesis(Block genesis)
    {
//        Block genesis = new Block("0");
        db.add(genesis);
        /* persist to the binary file */        
        persist();
        /* show ledger */        
        distribute();
    }
    /**     * nextBlock()     */    
    public void nextBlock( Block newBlock )
    {
        /* obtain existing blockchain binary */        
        db = get();
        newBlock.getHeader().setIndex( db.getLast().getHeader().getIndex() + 1 );
        db.add(newBlock);
        /* persist to the binary file */        
        persist();
        /* show ledger */        
        distribute();
    }
    /**     * get()     */    
    public LinkedList<Block> get()
    {
        try(
            FileInputStream fis = new FileInputStream( this.chainFile );
            ObjectInputStream in = new ObjectInputStream( fis );
            ) {
            return (LinkedList<Block>)in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**     * persist()     */    
    private void persist()
    {
        try(
            FileOutputStream fos = new FileOutputStream( this.chainFile );
            ObjectOutputStream out = new ObjectOutputStream( fos );
            ) {
            out.writeObject(db);
            System.out.println( ">> Master binary file is updated!" );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**     * distribute() - display/ write the ledger file     */    
    public void distribute()
    {
        /* convert the chain to String using gson api */        
//        try {
//            System.out.println("DISTRIBUTE ENTER LOOP");
//            System.out.println("DB SIZE: "+db.size());
//            for (int i=0; i< db.size();i++){
//                System.out.println("DISTRIBUTE ENTER LOOP IN "+i + " FOR STEP 1");
//                String chain = new GsonBuilder().setPrettyPrinting().create().toJson(db.get(i));
//                FileWriter fw = new FileWriter("myLedgerFile.txt",true);
//                BufferedWriter bw = new BufferedWriter(fw);
//                PrintWriter out = new PrintWriter(bw);
//                System.out.println("DISTRIBUTE ENTER LOOP IN "+i + " FOR STEP 2");
//                System.out.println( chain );
//                System.out.println("DISTRIBUTE ENTER LOOP IN "+i + " FOR STEP 3");
//                /* write to ledger file in text */    
//                //BufferedWriter bw = new BufferedWriter(new FileWriter("myLedgerFile.txt", false));
//                System.out.println("DISTRIBUTE ENTER LOOP IN "+i + " FOR STEP 4");
//                out.write(chain);
//                System.out.println("DISTRIBUTE ENTER LOOP IN "+i + " FOR STEP 5");
////                Files.write( Paths.get(this.ledgerFile) ,  chain.getBytes() , StandardOpenOption.CREATE);
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            String chain = new GsonBuilder().setPrettyPrinting().create().toJson(db);
            System.out.println( chain );
            /* write to ledger file in text */
            Files.write( Paths.get(this.ledgerFile) ,  chain.getBytes() , StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
