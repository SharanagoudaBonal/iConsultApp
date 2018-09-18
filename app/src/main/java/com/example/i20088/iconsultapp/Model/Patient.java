package com.example.i20088.iconsultapp.Model;

import java.io.Serializable;

public class Patient implements Serializable{
    //private String userId = null;
    private int patientId = 0;
    private String firstname = null;
    private String lastName = null;
    private String email =null;
    private String password;
    private String phone =null;
    private String gpsurgery =null;
    private String gpsurgpnamegery =null;
    private String remarks =null;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGpsurgery() {
        return gpsurgery;
    }

    public void setGpsurgery(String gpsurgery) {
        this.gpsurgery = gpsurgery;
    }

    public String getGpsurgpnamegery() {
        return gpsurgpnamegery;
    }

    public void setGpsurgpnamegery(String gpsurgpnamegery) {
        this.gpsurgpnamegery = gpsurgpnamegery;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Patient(int patientId,String firstname, String lastName, String email, String password, String phone, String gpsurgery, String gpsurgpnamegery, String remarks) {
       this.patientId = patientId;
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gpsurgery = gpsurgery;
        this.gpsurgpnamegery = gpsurgpnamegery;
        this.remarks = remarks;
    }
}
