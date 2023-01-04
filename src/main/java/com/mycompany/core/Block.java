/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.hashing.Hasher;

/**
 *
 * @author Arvind
 */
public class Block implements Serializable {
    private static final long seriaLVersionUID = 1L;
    @Override
    public String toString() {
        return "Block{" + "tranxs=" + tranxs + ", header=" + header + '}';
    }
    
    //agg relationship with get() set()
    public TranxCollection tranxs;

    public TranxCollection getTranxs() {
        return tranxs;
    }

    public void setTranxs(TranxCollection tranxs) {
        this.tranxs = tranxs;
    }

    public Block() {
    }

    //composition relationship
    public Header header;

    public Header getHeader() {
        return header;
    }
    public Block(String previoushash){
        header = new Header();
        header.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        header.setPrevHash(previoushash);
        header.setCurrHash("Blockhash");// hash = index+timestamp+previousHash
        String info = String.join ("+" ,Integer.toString(header.getIndex()) ,Long.toString(header.getTimestamp()), header.getPrevHash());
        String blockhash = new Hasher().sha256(info); //use Serialised getBytes here, ensure the hashing accepts byte[]
        header.setCurrHash(blockhash);
        
    }
    
    //To support serialisation convert block to byte[] (Optional)
    private byte[] getBytes(){
        try (
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(baos);   
                ){
           out.writeObject(this);
           byte[] blockByteArr = baos.toByteArray();
           return blockByteArr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    //compostion relationship Header
    public class Header implements Serializable{
        private static final long seriaLVersionUID = 1L;
        public int index;
        public String currHash, prevHash;
        public long timestamp;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getCurrHash() {
            return currHash;
        }

        public void setCurrHash(String currHash) {
            this.currHash = currHash;
        }

        public String getPrevHash() {
            return prevHash;
        }

        public void setPrevHash(String prevHash) {
            this.prevHash = prevHash;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }
        
        //display object info using toString()
        @Override
        public String toString() {
            return "Header{" + "index=" + index + ", currHash=" + currHash + ", prevHash=" + prevHash + ", timestamp=" + timestamp + '}';
        }
        
        
    }
}
