package com.database;

import com.mysql.jdbc.log.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

/**
 * Created by Erdem on 11/20/2015.
 */
public class UserConnection {

     static  Logger log = Logger.getLogger("USERCONNECTION");
    public static String[] checkUser(String email, String pass) {
        try {
            DBConnection conn = new DBConnection();
            String sql = "SELECT * FROM user WHERE email=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            String[] names;


            if (rs.next()) {
                names = new String[]{rs.getInt("id")+"", rs.getString("name"), rs.getString("email"),rs.getString("role")};
            } else {
                names = new String[]{};
            }
            rs.close();
            stmt.close();
            conn.close();
            return names;
        } catch (Exception e) {

            return new String[]{};
        }
    }


    public static boolean addUser(String email, String pass, String name, String role) {
        try {
            DBConnection conn = new DBConnection();
            String sql = "INSERT INTO user VALUES(?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,0);
            stmt.setString(2, email);
            stmt.setString(3, name);
            stmt.setString(4, pass);
            stmt.setString(5, role);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
