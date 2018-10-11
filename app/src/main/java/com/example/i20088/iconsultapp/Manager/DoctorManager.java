package com.example.i20088.iconsultapp.Manager;

import com.example.i20088.iconsultapp.Model.Doctor;

public class DoctorManager {
    private static DoctorManager instance = null;

    private DoctorManager() {
    }

    private Doctor doctor = null;

    public static DoctorManager getInstance() {
        if (instance == null) {
            instance = new DoctorManager();
        }
        return instance;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

}
