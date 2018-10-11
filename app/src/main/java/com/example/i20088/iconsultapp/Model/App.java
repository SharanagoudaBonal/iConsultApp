package com.example.i20088.iconsultapp.Model;

public class App {
    private String patientId, gpName, roomName, schelDate;

    public App() {
    }

    public App(String patientId, String gpName, String roomName, String schelDate) {
        this.gpName = patientId;
        this.schelDate = gpName;
        this.gpName = roomName;
        this.schelDate = schelDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSchelDate() {
        return schelDate;
    }

    public void setSchelDate(String schelDate) {
        this.schelDate = schelDate;
    }
}
