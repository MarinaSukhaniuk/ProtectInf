package com.sukhaniuk.license;

import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;

public class LicensesArrayList {
    private static ArrayList<String> licenses = new ArrayList<>();

    public LicensesArrayList() {
        generateKey();
    }

    private static String generateKey() {
        License license = new License(ComputerData.getComputerName(),
                ComputerData.getUserName(),
                ComputerData.getCurrentDir(),
                ComputerData.getScreenSize().height,
                ComputerData.getMouseButtonsCount(),
                ComputerData.getDriverInfo());
        String hash = license.generateHash();
        System.out.println(hash);
        System.out.println(License.decodeToString(hash));
        return hash;
    }

    public static boolean checkLicense() {
        String thisComputerBase64 = generateKey();
        for (String license : getLicenses()) {
            if (license.equals(thisComputerBase64)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> getLicenses() {
        licenses.add("c2h5c2xhdi1MYXRpdHVkZS1FNzI0MC8xMjcuMC4xLjF8fHxzaHlzbGF2fHx8L2hvbWUvc2h5c2xhdi9Qcm9qZWN0cy9wZXJzb25hbC9NYXJpbmEvc3ByaW5nX3RlbXBsYXRlfHx8NzY4fHx8MTR8fHw0NjY1MzY3MzQ3Mnx8fA==");
        licenses.add("c2h5c2xhdi1MYXRpdHVkZS1FNzI0MC8xMjcuMC4xLjF8fHxzaHlzbGF2fHx8L2hvbWUvc2h5c2xhdi9TZXJ2ZXIvamV0dHktZGlzdHJpYnV0aW9uLTkuMy4xMS52MjAxNjA3MjF8fHw3Njh8fHwxNHx8fDQ2NjUzNjczNDcyfHx8");
        return licenses;
    }
}

class License {
    private String computerName;
    private String userName;
    private String currntDir;
    private int height;
    private int mouseAmountsButtons;
    private ArrayList<DriverInfo> driverInfo;

    public License(String computerName, String userName, String currntDir, int height, int mouseAmountsButtons, ArrayList<DriverInfo> driverInfo) {
        this.computerName = computerName;
        this.userName = userName;
        this.currntDir = currntDir;
        this.height = height;
        this.mouseAmountsButtons = mouseAmountsButtons;
        this.driverInfo = driverInfo;
    }

    public String getComputerName() {
        return computerName;
    }

    public void setComputerName(String computerName) {
        this.computerName = computerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCurrntDir() {
        return currntDir;
    }

    public void setCurrntDir(String currntDir) {
        this.currntDir = currntDir;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMouseAmountsButtons() {
        return mouseAmountsButtons;
    }

    public void setMouseAmountsButtons(int mouseAmountsButtons) {
        this.mouseAmountsButtons = mouseAmountsButtons;
    }

    public ArrayList<DriverInfo> getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(ArrayList<DriverInfo> driverInfo) {
        this.driverInfo = driverInfo;
    }

    public String generateHash() {
        StringBuilder builder = new StringBuilder();
        builder.append(computerName);
        builder.append("|||");
        builder.append(userName);
        builder.append("|||");
        builder.append(currntDir);
        builder.append("|||");
        builder.append(height);
        builder.append("|||");
        builder.append(mouseAmountsButtons);
        builder.append("|||");
        long sum = 0;
        for (DriverInfo driver : ComputerData.getDriverInfo()) {
            sum = sum + driver.getTotalSpace();
        }
        builder.append(sum);
        builder.append("|||");
        System.out.println(builder.toString());
        return new String(Base64.encodeBase64(builder.toString().getBytes()));
    }

    public static String decodeToString(String value) {
        if (value == null) {
            return null;
        }
        return new String(Base64.decodeBase64(value));
    }
}
