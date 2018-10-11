package com.example.i20088.iconsultapp.Network;

public enum RequestType {
    login,
    userCreation,
    allAppointments,
    allHospitals,
    allPatients,
    allDoctors,
    createAppointment;


    public static RequestType getRequestType(String name) {
        RequestType type = null;
        switch (name) {
            case "login":
                type = login;
                break;
            case "userCreation":
                 type = userCreation;
                 break;
            case "allAppointments":
                type = allAppointments;
                break;
            case "allHospitals":
                type = allHospitals;
                break;
            case "allPatients":
                type = allPatients;
                break;
            case "allDoctors":
                type = allDoctors;
                break;
            case "createAppointment":
                type = createAppointment;
                break;
            default:
                break;
        }
        return type;
    }
}
