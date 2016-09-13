package com.sukhaniuk.storage;

import com.sukhaniuk.databases.models.Role;
import com.sukhaniuk.databases.models.User;
import org.apache.commons.lang.ArrayUtils;

public class UsersStorage {
    private User user;
    private static int permission = 1;

    public UsersStorage(User user) {
        this.user = user;
        this.permission = user.getRole().getPermission();
    }

    /**
     * Login user
     * @param name user name
     * @param password user password
     */
    public static UsersStorage login(String name, String password){
        //select from db user with variable name and variable password
        Role role = new Role(1,"USER",6);
        User user = new User(1,role,name,password);
        UsersStorage storage = new UsersStorage(user);
        return storage;
    }

    public static boolean checkRole(int [] role){
        if(ArrayUtils.contains(role, permission)){
            return true;
        }
        return false;
    }

    public void logout(){
        user = null;
        permission = 0;
    }
}
