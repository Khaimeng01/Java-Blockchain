package com.mycompany.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Arvind
 */
public class TranxCollection implements Serializable {
    private static final long seriaLVersionUID = 1L;
    
    public static final int SIZE = 10;
    
    public String merkelRoot ;
    
    public List<Patient> tranxlist;
    
    public TranxCollection(){
        System.out.println("RMA_1");
        tranxlist = new ArrayList<>(SIZE);
    }
    
    //add transaction
    public void add(Patient transaction){
        System.out.println("RMA_2");
        tranxlist.add(transaction);
        System.out.println("RMA_3");
        MerkleTree mt = MerkleTree.getInstance( tranxlist ) ;
        System.out.println("RMA_4");
        mt.build();
        System.out.println("RMA_5");
        merkelRoot = mt.getRoot();
        System.out.println("RMA_6");
    }

    @Override
    public String toString() {
        return "TranxCollection{" + "merkelRoot=" + merkelRoot + ", tranxlist=" + tranxlist + '}';
    }
    
    
    
    
}