package com.example.i20088.iconsultapp.Manager;


import android.content.Context;

import com.example.i20088.iconsultapp.Model.User;
import com.example.i20088.iconsultapp.Network.NetworkManager;

public class UserManager {
    private static UserManager instance = null;
    private UserManager() {}

    private User user = null;

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public boolean isUser(){
        return user != null;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void createUser(Context context){
        NetworkManager networkManager = new NetworkManager(context);
        String firstName = UserManager.getInstance().getUser().getFirstName();
        String lastName = UserManager.getInstance().getUser().getLastName();
        String email = UserManager.getInstance().getUser().getEmail();
        String password = UserManager.getInstance().getUser().getPassword();
        String phone = UserManager.getInstance().getUser().getPhone();
        String gpName = UserManager.getInstance().getUser().getGpname();
        String gpsurgery = UserManager.getInstance().getUser().getGpsurgery();
        String remarks = UserManager.getInstance().getUser().getRemarks();
        networkManager.requetCreateUser(firstName, lastName, email, password, phone, gpName, gpsurgery, remarks);
        System.out.println("User manager "+firstName +" gpname "+gpName);
    }
}
