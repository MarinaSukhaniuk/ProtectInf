package com.sukhaniuk.databases.insert;

import com.sukhaniuk.databases.configuration.DatabaseConnection;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Shyshkin Vladyslav on 05.05.2016.
 */
public class DatabaseInsert {
    private static final Logger log = Logger.getLogger(DatabaseInsert.class.getName());

    private static DatabaseConnection db = new DatabaseConnection();


    public static String insertData(String role_id, String login, String password) {
        log.info("try to insert to database to");
        db.openConnection();
        String command = "insert into users(role_id, login, password) VALUES (" +
                "'" + role_id + "'," +
                "'" + login + "'," +
                "'" + password + "')";
        log.info(command);
        System.out.println(command);
        try {
            db.st.execute(command);
        } catch (SQLException ex) {
            log.error("insert failed" + ex);
            return "Группа не добавлена по причине:" + ex;
        } finally {
            db.closeConnection();
        }
        return "done";
    }
}

