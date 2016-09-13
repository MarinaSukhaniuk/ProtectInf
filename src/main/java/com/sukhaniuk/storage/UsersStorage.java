package com.sukhaniuk.storage;

import com.sukhaniuk.databases.models.User;

public class UsersStorage {
    private User user;
    private int permission;

    public UsersStorage(User user) {
        this.user = user;
    }

    /**
     * Login user
     * @param name user name
     * @param password user password
     */
    public static void login(String name, String password){
        //select from db user with variable name and variable password
    }

    public void logout(){
        user = null;
        permission = 0;
    }
}
