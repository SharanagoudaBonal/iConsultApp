package com.example.i20088.iconsultapp.Network;


import com.example.i20088.iconsultapp.Model.User;

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

    public void setUser(User user) {
        this.user = user;
    }
}
