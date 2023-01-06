/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.core;

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
    

    @Override
    public String toString() {
        return "Appointment{" + "ID=" + ID + ", date=" + date + ", patientID=" + patientID + ", doctorName=" + doctorName + ", departmentName=" + departmentName  + '}';
    }


}
