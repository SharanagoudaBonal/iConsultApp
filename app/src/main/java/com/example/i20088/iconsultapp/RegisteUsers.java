package com.example.i20088.iconsultapp;

public class RegisteUsers {
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String status;
    private String gpsurgery;
    private String gpsurgpnamegery;
    private String remarks;

    public RegisteUsers(String firstname, String lastname, String email, String phone, String status, String gpsurgery, String gpsurgpnamegery, String remarks) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.gpsurgery = gpsurgery;
        this.gpsurgpnamegery = gpsurgpnamegery;
        this.remarks = remarks;
    }
}
