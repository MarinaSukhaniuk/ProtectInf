import com.sukhaniuk.databases.configuration.DatabaseConnection;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shyslav on 9/13/16.
 */
public class database {
    DatabaseConnection db = new DatabaseConnection();
    @Test
    public void getConnection(){
        assertNotNull(db.getConnection());
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