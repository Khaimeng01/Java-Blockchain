/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.core;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author Arvind
 */
public class Main {

    public static void main(String[] args) {
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
        //test1();
        test2();
    }
    
    static final String MASTER_DIR = "master";
    static final String MASTER_BINARY = MASTER_DIR+"/mychain";
    
    static void test1(){
        Blockchain bc = Blockchain.getInstance(MASTER_BINARY);
        String tranx1 = "bob|alice|debit|50";
        String tranx2 = "pete|bob|credit|200";
        if ( !( new File(MASTER_DIR).exists() ) ) {
            /* make a dir if not found */            
            new File( MASTER_DIR ).mkdir();
            bc.genesis();
        } else {
            TranxCollection tranxs = new TranxCollection();
            tranxs.add(tranx1);
            tranxs.add(tranx2);
            String prevHash = bc.get().getLast().getHeader().getCurrHash();
            Block newBlock = new Block( prevHash );
            newBlock.setTranxs(tranxs);
            bc.nextBlock(newBlock);
        }
    }
    
    static void test2(){
         //generate merkle tree sample
        
        //dummy transaciton
        String[] arr = {
            "alice|bob|credit|rm10",
            "alice|bob|debit|rm2@",
            "a1ice|bob|credit|rm30",
            "alice|bob|debit|rm40"
        };
        
        MerkleTree mt = MerkleTree.getInstance( Arrays.asList(arr) ) ;
        mt.build();
        String root = mt . getRoot();
        System.out.println( "Merkle Root: " + root); 
    }

}