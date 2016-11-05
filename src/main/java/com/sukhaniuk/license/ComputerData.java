package com.sukhaniuk.license;

import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ComputerData {
    private static final FileSystemView fsv = FileSystemView.getFileSystemView();

    public static String getUserName() {
        return System.getProperty("user.name");
    }

    public static String getComputerName() {
        try {
            return String.valueOf(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrentDir() {
        return System.getProperty("user.dir");
    }

    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static File[] getRootsFolder() {
        return fsv.getRoots();
    }

    public static File[] getRootsList() {
        return File.listRoots();
    }

    public static int getMouseButtonsCount() {
        return java.awt.MouseInfo.getNumberOfButtons();
    }

    public static ArrayList<DriverInfo> getDriverInfo() {
        ArrayList<DriverInfo> list = new ArrayList();
        File[] f = getRootsList();
        for (int i = 0; i < f.length; i++) {
            DriverInfo driverInfo = new DriverInfo(
                    f[i].toString(),
                    fsv.getSystemDisplayName(f[i]),
                    fsv.isDrive(f[i]),
                    fsv.isFloppyDrive(f[i]),
                    f[i].canRead(),
                    f[i].canWrite(),
                    f[i].getTotalSpace(),
                    f[i].getUsableSpace()
            );
            list.add(driverInfo);
        }
        return list;
    }
}
