package com.example.i20088.iconsultapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Doctor extends ArrayList<Doctor> implements Serializable {
    private int docId;
    private String firstname;
    private String lastname;
    public Doctor(int docId, String firstname, String lastname) {
        this.docId = docId;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Doctor(){}

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
