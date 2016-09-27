package com.sukhaniuk.license;

import java.util.ArrayList;

public class LicensesArrayList {
    private static ArrayList<String> licenses = new ArrayList<>();

    public static ArrayList<String> getLicenses() {
        licenses.add("123456");
        licenses.add("123456");
        licenses.add("123456");
        licenses.add("123456");
        licenses.add("123456");
        return licenses;
    }
}
