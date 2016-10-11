import com.sukhaniuk.databases.configuration.DatabaseConnection;
import com.sukhaniuk.databases.select.SelectCommand;
import com.sukhaniuk.license.ComputerData;
import com.sukhaniuk.license.DriverInfo;
import com.sukhaniuk.license.LicensesArrayList;
import org.junit.Test;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

public class database {
    DatabaseConnection db = new DatabaseConnection();

    @Test
    public void getConnection() {
        assertNotNull(db.getConnection());
    }

    @Test
    public void checkLogin() {
        boolean result = SelectCommand.checkUser("ADMIN", "827ccb0eea8a706c4c34a16891f84e7b");
        assertTrue(result);
    }

    @Test
    public void insertTest() {

    }

    @Test
    public void selectTest() {

    }

    @Test
    public void updateTest() {

    }

    @Test
    public void outMouseElem() {
        System.out.println(java.awt.MouseInfo.getNumberOfButtons());
    }

    @Test
    public void getComputerData() {
        System.out.println("Computer name: " + ComputerData.getComputerName());
        System.out.println("User name: " + ComputerData.getUserName());
        System.out.println("Current dir: " + ComputerData.getCurrentDir());
        System.out.println("Height: " + ComputerData.getScreenSize().height);
        System.out.println("Mouse amount buttons: " + ComputerData.getMouseButtonsCount());
        for (DriverInfo driver : ComputerData.getDriverInfo()) {
            System.out.println("Disk: " + driver.getDisplayName() + " Total space: " + driver.getTotalSpace());
        }
    }

    @Test
    public void testBase64() {
        LicensesArrayList licensesArrayList = new LicensesArrayList();
        assertTrue(licensesArrayList.checkLicense());
    }

    @Test
    public void generateTestBaseCrypt() {
        try {
            String connection = "jdbc.drivers=com.mysql.jdbc.Driver\n" +
                    "jdbc.url=jdbc:mysql://127.0.0.1:3306/secureinf?useUnicode=true&characterEncoding=utf-8\n" +
                    "jdbc.username=root\n" +
                    "jdbc.password=12345";
            byte[] cryptoByte = DatabaseConnection.cryptString(connection);
            String encodedConnectionString = new BASE64Encoder().encode(cryptoByte);
            assertNotNull(encodedConnectionString);
            String test = DatabaseConnection.decryptText(cryptoByte);
            assertNotNull(test);
            assertNotNull(test);
            assertEquals(test, connection);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | BadPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void openCryptoDatabaseConnection(){
        DatabaseConnection connection = new DatabaseConnection();
        connection.openConnection();
        connection.getConnection();
    }
}
