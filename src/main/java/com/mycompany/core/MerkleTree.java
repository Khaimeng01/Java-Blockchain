/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.core;

import java.util.ArrayList;
import java.util.List;
import com.mycompany.hashing.Hasher;

/**
 *
 * @author Arvind
 */
public class MerkleTree {
    private List<Patient> tranxLst;
    private String root = "0";
    private Hasher hasher = new Hasher();
    public String getRoot() {
        return root;
    }
    /**     
     * @implNote     
     * Set the transaction list to the MerkleTree object.     
     *      
     * @param tranxLst     */    
    private MerkleTree(List<Patient> tranxLst) {
        super();
        this.tranxLst = tranxLst;
    }
    /**    
     * Design pattern: Singleton     
     */    
    private static MerkleTree instance;
    public static MerkleTree getInstance( List<Patient> tranxLst ) {
        if( instance == null ) {
            return new MerkleTree(tranxLst);
        }
        return instance;
    }
    /**     
     * @implNote     
     * Build merkle tree      
     *      
     * @implSpec     
     * + build() : void     
     */    
    public void build() {
        List<String> tempLst = new ArrayList<>();
        for (Patient tranx : this.tranxLst) {
            tempLst.add(tranx.ID + tranx.Fname + tranx.Lname + tranx.IC + tranx.phoneNumber + tranx.gender + tranx.bloodType + tranx.disability 
                    + tranx.preExistingCondition + tranx.currentDisease + tranx.currentMedicationPlan);
        }
        List<String> hashes = genTranxHashLst( tempLst );
//        while(  hashes.size() != 1 ) {
//            hashes = genTranxHashLst( hashes );
//        }
        this.root = hashes.get(0);
    }
    /**     
     * @implNote     
     * Generate hashes of transactions      
     *      
     * @implSpec      
     * - genTranxHashLst(List<String>) : List<String>     */    
    private List<String> genTranxHashLst(List<String> tranxLst) {
        List<String> hashLst = new ArrayList<>();
        int i = 0;
        while( i < tranxLst.size() ) {
            String left = tranxLst.get(i);
            i++;
            String right = "";
            if( i != tranxLst.size() ) right = tranxLst.get(i);
            String tohash = left.concat(right);
            String hash = hasher.sha256(tohash);
            hashLst.add(hash);
            i++;
        }
        return hashLst;
    }
}
