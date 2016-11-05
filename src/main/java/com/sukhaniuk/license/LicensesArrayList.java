package com.sukhaniuk.license;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

public class LicensesArrayList {
    public static ArrayList<String> licenses = new ArrayList<>();

    public LicensesArrayList() {

        getLicenses();
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

    public boolean checkLicense() {
        String thisComputerBase64 = generateKey();
        for (String license : LicensesArrayList.licenses) {
            if (license.equals(thisComputerBase64)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getLicenses() {
        licenses.clear();
        readLicenseFromTxtFile();
        return licenses;
    }
    public void readLicenseFromTxtFile(){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("Licenses.txt").getFile());

        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNext()){
                licenses.add(scanner.nextLine());
            }
        }catch (IOException ex){
            System.out.println("Licenses file not found");
            System.exit(0);
        }

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
