package com.example.i20088.iconsultapp.Network;

public enum RequestType {
    login;

    public static RequestType getRequestType(String name) {
        RequestType type = null;
        switch (name) {
            case "login":
                type = login;
                break;
            default:
                break;
        }
        return type;
    }
}
