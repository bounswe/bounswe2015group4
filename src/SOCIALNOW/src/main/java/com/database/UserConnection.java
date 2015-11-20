package com.database;

import com.mysql.jdbc.log.Log;

import java.sql.Date;
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
            String sql = "INSERT INTO user (email, name, password, role) VALUES(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, name);
            stmt.setString(3, pass);
            stmt.setString(4, role);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    public static boolean addEvent(int user_id, String title, String description, Date date){
        try {
            DBConnection conn = new DBConnection();
            String sql = "INSERT INTO event (user_id, title, description, date) VALUES(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,user_id);
            stmt.setString(2, title);
            stmt.setString(3, description);
            stmt.setDate(4, date);
            stmt.executeUpdate();
            conn.close();
        }catch (Exception e) {
            return false;
        }

       return true;
    }

}
