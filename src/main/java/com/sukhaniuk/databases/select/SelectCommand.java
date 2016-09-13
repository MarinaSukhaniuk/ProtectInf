package com.sukhaniuk.databases.select;

import com.sukhaniuk.databases.configuration.DatabaseConnection;
import com.sukhaniuk.databases.models.Role;
import com.sukhaniuk.databases.models.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;


public class SelectCommand {
    private static final Logger log = Logger.getLogger(SelectCommand.class.getName());

    private static DatabaseConnection db = new DatabaseConnection();

    public static ArrayList<User> selectUsers() {
        log.info("try to select users");
        ArrayList<User> result = new ArrayList();
        String query = "select * from user inner join role on user.roleID = role.id";
        db.openConnection();
        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                result.add(new User(
                        db.rs.getInt("id"),
                        new Role(db.rs.getInt("role_id"), db.rs.getString("name"), db.rs.getInt("permission")),
                        db.rs.getString("email"),
                        db.rs.getString("password")));
            }
        } catch (SQLException ex) {
            log.error("execute query error" + ex);
        } finally {
            db.closeConnection();
        }
        return result;
    }

    public static boolean checkUser(String email, String pass) {
        log.info("try to select users");
        String query = "select * from user where email = '" + email + "' and password = '" + pass +"'";
        System.out.println(query);
        db.openConnection();
        try {
            db.rs = db.st.executeQuery(query);
            if (db.rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            log.error("execute query error" + ex);
        } finally {
            db.closeConnection();
        }
        return false;
    }
}
