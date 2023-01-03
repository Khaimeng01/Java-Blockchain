/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.core;

import java.io.Serializable;

/**
 *
 * @author Arvind
 */
public class Patient implements Serializable{
    String ID;
    
    //Encrypt
    String Fname;
    //Encrypt
    String Lname;
    //Encrypt
    String IC;
    //Encrypt
    String phoneNumber;
    
    String gender;
    String bloodType;
    String disability;
    String preExistingCondition;
    String currentMedicationPlan;
    
    
    
    
    public Patient() {
    }

    public Patient(String ID, String Fname, String Lname, String IC, String phoneNumber, String gender, String bloodType, String disability, String preExistingCondition, String currentMedicationPlan) {
        this.ID = ID;
        this.Fname = Fname;
        this.Lname = Lname;
        this.IC = IC;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.bloodType = bloodType;
        this.disability = disability;
        this.preExistingCondition = preExistingCondition;
        this.currentMedicationPlan = currentMedicationPlan;
    }
//
//    
//    public Patient(String ID, String Fname, String Lname, String IC, String phoneNumber, String gender) {
//        this.ID = ID;
//        this.Fname = Fname;
//        this.Lname = Lname;
//        this.IC = IC;
//        this.phoneNumber = phoneNumber;
//        this.gender = gender;
//    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public String getIC() {
        return IC;
    }

    public void setIC(String IC) {
        this.IC = IC;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String isGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    
}
