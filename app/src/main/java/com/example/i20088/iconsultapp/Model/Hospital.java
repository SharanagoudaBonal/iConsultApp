package com.example.i20088.iconsultapp.Model;

import java.io.Serializable;

public class Hospital implements Serializable {
    private int gpsurgid;
    private String gpsurgcode;
    private String gpsurgname;

    public Hospital(int gpsurgid, String gpsurgcode, String gpsurgname) {
        this.gpsurgid = gpsurgid;
        this.gpsurgcode = gpsurgcode;
        this.gpsurgname = gpsurgname;
    }

    public int getGpsurgid() {
        return gpsurgid;
    }

    public void setGpsurgid(int gpsurgid) {
        this.gpsurgid = gpsurgid;
    }

    public String getGpsurgcode() {
        return gpsurgcode;
    }

    public void setGpsurgcode(String gpsurgcode) {
        this.gpsurgcode = gpsurgcode;
    }

    public String getGpsurgname() {
        return gpsurgname;
    }

    public void setGpsurgname(String gpsurgname) {
        this.gpsurgname = gpsurgname;
    }
}
