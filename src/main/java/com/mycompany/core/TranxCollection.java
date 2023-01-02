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
    
    public String merkelRoot = "default";
    
    public List<Patient> tranxlist;
    //public List<String> tranxlist; //can use other collection API for transaction collection
    
    public TranxCollection(){
        tranxlist = new ArrayList<>(SIZE);
    }
    
    //add transaction
    public void add(Patient transaction){
        tranxlist.add(transaction);
    }

    @Override
    public String toString() {
        return "TranxCollection{" + "merkelRoot=" + merkelRoot + ", tranxlist=" + tranxlist + '}';
    }
    
    
    
}