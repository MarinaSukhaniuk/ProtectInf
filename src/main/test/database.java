import com.sukhaniuk.databases.configuration.DatabaseConnection;
import com.sukhaniuk.databases.select.SelectCommand;
import org.junit.Test;

import static org.junit.Assert.*;

public class database {
    DatabaseConnection db = new DatabaseConnection();
    @Test
    public void getConnection(){
        assertNotNull(db.getConnection());
    }

    @Test
    public void checkLogin(){
        boolean result = SelectCommand.checkUser("ADMIN","827ccb0eea8a706c4c34a16891f84e7b");
        assertTrue(result);
    }

    @Test
    public void insertTest(){

    }

    @Test
    public void selectTest(){

    }

    @Test
    public void updateTest(){

    }
}
