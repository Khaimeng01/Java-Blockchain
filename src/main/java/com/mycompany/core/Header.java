/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.core;

/**
 *
 * @author Arvind
 */
public class Header {
    int index;
    String currentHash;
    String previousHash;
    long timestamp;

    public Header(int index, String currentHash, String previousHash, long timestamp) {
        this.index = index;
        this.currentHash = currentHash;
        this.previousHash = previousHash;
        this.timestamp = timestamp;
    }

    public int getIndex() {
        return index;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
