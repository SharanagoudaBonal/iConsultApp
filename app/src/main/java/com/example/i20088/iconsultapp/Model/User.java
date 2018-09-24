package com.example.i20088.iconsultapp.Model;

import android.widget.TextView;

import com.example.i20088.iconsultapp.Network.UserType;

import java.io.Serializable;

public class User implements Serializable {

    private String userId = null;
    private String firstName = null;
    private String lastName = null;
    private String email =null;
    private  String password = null;
    private String phone =null;
    private String status =null;
    private String gpname =null;
    private String gpsurgery =null;
    //private boolean isPatient;
    private UserType userType =null;
    private String remarks =null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getGpname() {
        return gpname;
    }

    public void setGpname(String gpname) {
        this.gpname = gpname;
    }

    public String getGpsurgery() {
        return gpsurgery;
    }

    public void setGpsurgery(String gpsurgery) {
        this.gpsurgery = gpsurgery;
    }

  /*  public boolean isPatient() {
        return isPatient;
    }

    public void setPatient(boolean patient) {
        isPatient = patient;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }   */

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public User() {
    }

   /* public User(String userId, String firstName, String lastName, String email,String password, String phone, String status, String gpname, String gpsurgery,UserType userType, String remarks) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.gpname = gpname;
        this.gpsurgery = gpsurgery;
        this.userType = userType;
        this.remarks = remarks;
    }*/
}
