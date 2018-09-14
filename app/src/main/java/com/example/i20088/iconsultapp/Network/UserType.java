package com.example.i20088.iconsultapp.Network;

public enum UserType {
    patient,
    doctor;
    public static UserType getUserType(String name){
        UserType type=null;
        switch (name) {
            case "patient":
                type = patient;
                break;
            case "doctor":
                type = doctor;
                break;
            default:
                break;
        }
        return type;
    }
}
