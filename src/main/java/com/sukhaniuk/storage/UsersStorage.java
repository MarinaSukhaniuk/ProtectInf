package com.sukhaniuk.storage;

import com.sukhaniuk.databases.configuration.DatabaseConnection;
import com.sukhaniuk.databases.models.Role;
import com.sukhaniuk.databases.models.User;
import org.apache.commons.lang.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersStorage {
    private User user;
    private static int permission = 1;

    public UsersStorage(User user) {
        this.user = user;
        this.permission = user.getRole().getPermission();
    }

    /**
     * Login user
     * @param email user name
     * @param password user password
     */
    public static UsersStorage login(String email, String password, Role role){
        //select from db user with variable name and variable password
        User user = new User(1,role,email,password);
        UsersStorage storage = new UsersStorage(user);
        return storage;
    }
    //TODO remove this
    private static Role getRoleByUserID(int roleID){
        String query = "select * from roles where id = ?";
        DatabaseConnection conn = new DatabaseConnection();
        Connection connection = conn.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,roleID);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return new Role(rs.getInt(1),rs.getString(2),rs.getInt(3));
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
    public static boolean checkRole(int [] role){
        if(ArrayUtils.contains(role, permission)){

            return true;
        }
        return false;
    }

    public void logout(HttpServletRequest req){
        req.getSession().removeAttribute("storage");
        user = null;
        permission = 1;
    }
}
