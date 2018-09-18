package com.example.i20088.iconsultapp.Network;

public enum RequestType {
    login,
    userCreation;


    public static RequestType getRequestType(String name) {
        RequestType type = null;
        switch (name) {
            case "login":
                type = login;
                break;
            case "userCreation":
                 type = userCreation;
                 break;
            default:
                break;
        }
        return type;
    }
}
