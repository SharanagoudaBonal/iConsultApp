package com.example.i20088.iconsultapp.Model;

import java.io.Serializable;

public class User implements Serializable {

    private String userId = null;
    private String firstName = null;
    private String lastName = null;
    private String email =null;
    private String phone =null;
    private String status =null;
    private String gpsurgery =null;
    private String gpsurgpnamegery =null;
   // private UserType userType =null;
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

    /*public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }*/

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
