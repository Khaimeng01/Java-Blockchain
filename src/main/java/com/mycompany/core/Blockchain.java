/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.core;

import com.google.gson.GsonBuilder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            FileInputStream fis = new FileInputStream( this.chainFile);
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
        try {
            String chain = new GsonBuilder().setPrettyPrinting().create().toJson(db);
            /* write to ledger file in text */
            Files.write( Paths.get(this.ledgerFile) ,  chain.getBytes() , StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
