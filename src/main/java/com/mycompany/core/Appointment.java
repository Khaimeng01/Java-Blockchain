/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Arvind
 */
public class Appointment {
    String ID;
    String date;
    String patientID;
    String doctorName;
    String departmentName;
    String digitalSignature;
    
    private static final String APPFILENAME = "appointment.txt";

    public Appointment(String ID, String date, String patientID, String doctorName, String departmentName) {
        this.ID = ID;
        this.date = date;
        this.patientID = patientID;
        this.doctorName = doctorName;
        this.departmentName = departmentName;
    }
    
    public Appointment(String ID, String date, String patientID, String doctorName, String departmentName, String digitalSignature) {
        this.ID = ID;
        this.date = date;
        this.patientID = patientID;
        this.doctorName = doctorName;
        this.departmentName = departmentName;
        this.digitalSignature = digitalSignature;
    }

    public Appointment() {
    }
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public String getDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(String digitalSignature) {
        this.digitalSignature = digitalSignature;
    }
    
//    public ArrayList<Appointment> loadAppointment(){
//        ArrayList<Appointment> appList = new ArrayList<>();
//        
//        try (BufferedReader br = new BufferedReader(new FileReader(APPFILENAME))) {
//            br.readLine();
//            String row;
//            while((row = br.readLine())!= null){
//                String[] data = row.split("\\|\\|");            
//                String ID = data[0];
//                String date = data[1];
//                String patientID = data[2];
//                String doctorName = data[3];
//                String departmentName = data[4];
//                appList.add(new Appointment(ID,date,patientID,doctorName,departmentName));              
//            }
//            return appList;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    
//     public ArrayList<Appointment> loadSalesExec(String search){
//        ArrayList<Appointment> appList = new ArrayList<>();
//        
//        try (BufferedReader br = new BufferedReader(new FileReader(APPFILENAME))) {
//            br.readLine();
//            String row;
//            while((row = br.readLine())!= null){
//                String[] data = row.split("\\|\\|");            
//                String ID = data[0];
//                String date = data[1];
//                String patientID = data[2];
//                String doctorName = data[3];
//                String departmentName = data[4];
//                Appointment c = new Appointment(ID,date,patientID,doctorName,departmentName);   
//                if((c.getID().matches(search+".*"))|| (c.getDate().matches(search+".*")) || (c.getPatientID().matches(search+".*"))
//                        || (c.getDoctorName().matches(search+".*")) || (c.getDepartmentName().matches(search+".*"))){
//                    appList.add(c);
//                }             
//            }
//            return appList;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}
