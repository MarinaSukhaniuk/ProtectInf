package com.sukhaniuk;

import com.sukhaniuk.databases.models.Alert;
import com.sukhaniuk.databases.select.SelectCommand;

/**
 * Created by Marina on 13.09.2016.
 */
public class Validation {
    public static boolean checkpassword(String password) {
        if (password.length()==0) {
            System.out.print("Password is null");
            return false;

        }
        if (password.length() < 3 || password.length() > 20 || password.matches("(.*)[a-z]*.[A-Z].[0-9].[-+/=]*")) {
            System.out.print("Lenght of pass is wring");
            return false;
        }
        System.out.print("Password is ok");
        return true;
    }
    public static boolean checkuser(String password, String email){
        if(SelectCommand.checkUser(email,password))
        {
            System.out.println("user is ok");
            return true;
        }
        else return false;
    }
}
