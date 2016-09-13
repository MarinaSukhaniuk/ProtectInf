package com.sukhaniuk;

import com.sukhaniuk.databases.models.Alert;

/**
 * Created by Marina on 13.09.2016.
 */
public class Validation {
    public static boolean checkpassword(String password) {
        if (password != null) {
            return false;
        }
        if (password.length() < 3 || password.length() > 20) {
            return false;
        }
        return true;

    }
}
