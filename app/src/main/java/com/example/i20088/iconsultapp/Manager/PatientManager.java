package com.example.i20088.iconsultapp.Manager;

import android.content.Context;

import com.example.i20088.iconsultapp.Model.Patient;
import com.example.i20088.iconsultapp.Network.NetworkManager;

public class PatientManager {
    private static PatientManager instance = null;
    private PatientManager(){}
    private Patient patient = null;

    public static PatientManager getInstance(){
        if (instance == null)
        {
            instance = new PatientManager();
        }
        return instance;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void createPatient(Context context){
        NetworkManager networkManager = new NetworkManager(context);
        String firstName = PatientManager.getInstance().getPatient().getFirstname();
        String lastName = PatientManager.getInstance().getPatient().getLastName();
        String email = PatientManager.getInstance().getPatient().getEmail();
        String password = PatientManager.getInstance().getPatient().getPassword();
        String phone = PatientManager.getInstance().getPatient().getPhone();
        String gpsurgery = PatientManager.getInstance().getPatient().getGpsurgery();
        String gpsurgpnamegery = PatientManager.getInstance().getPatient().getGpsurgpnamegery();
        String remarks = PatientManager.getInstance().getPatient().getRemarks();
        networkManager.requetCreatePatient(firstName, lastName, email, password, phone, gpsurgery, gpsurgpnamegery, remarks);
    }
}
