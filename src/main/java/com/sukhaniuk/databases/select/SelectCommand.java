package com.sukhaniuk.databases.select;

import com.sukhaniuk.databases.configuration.DatabaseConnection;
import com.sukhaniuk.databases.models.Role;
import com.sukhaniuk.databases.models.User;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class SelectCommand {
    private static final Logger log = Logger.getLogger(SelectCommand.class.getName());

    private static DatabaseConnection db = new DatabaseConnection();
    public List<User> usersList = new LinkedList();

    public static User selectUserByMail(String email) {
        log.info("try to select users");
        String query = "select * from users inner join roles on users.role_id = roles.id where users.email  = '" + email + "'";
        db.openConnection();
        try {
            db.rs = db.st.executeQuery(query);
            if (db.rs.next()) {
                return new User(
                        db.rs.getInt("id"),
                        new Role(db.rs.getInt("role_id"),
                                db.rs.getString("name"),
                                db.rs.getInt("permission")),
                        db.rs.getString("email"),
                        db.rs.getString("password"));
            }
        } catch (SQLException ex) {
            log.error("execute query error" + ex);
        } finally {
            db.closeConnection();
        }
        return null;
    }

    public static boolean checkUser(String email, String pass) {
        log.info("check user");
        String query = "select * from users where email = '" + email + "' and password = '" + pass + "'";
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

    public static void UpdatePassword(String newPassword, String email) {
        log.info("check user");
        String query = "update users set password = '" + newPassword + "' where email = '" + email + "'";
        System.out.println(query);
        db.openConnection();
        try {
            db.st.execute(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        db.closeConnection();
    }

    public List GetUsers() {
        usersList.clear();
        String query = null;
        query = "select users.id user_id, roles.id role_id, roles.name, roles.permission,  users.email, users.password\n" +
                "from users inner join roles on users.role_id = roles.id order by user_id";
        db.openConnection();

        try {
            db.rs = db.st.executeQuery(query);
            while (db.rs.next()) {
                usersList.add(new User(
                                db.rs.getInt("user_id"),
                                new Role(db.rs.getInt("role_id"),
                                        db.rs.getString("name"),
                                        db.rs.getInt("permission")),
                                db.rs.getString("email"),
                                db.rs.getString("password")
                        )
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        db.closeConnection();
        return usersList;
    }
}
