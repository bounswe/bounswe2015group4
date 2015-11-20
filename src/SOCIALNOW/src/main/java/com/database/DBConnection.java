package com.database;

/**
 * Created by Erdem  on 11/19/2015.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;


public class DBConnection {
    static Logger log = Logger.getLogger("DBCONNECTION");
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/database";
    static final String USER = "root";
    static final String PASS = "";

    private Connection conn = null;


    public DBConnection() throws SQLException, ClassNotFoundException{
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        }catch (Exception e){

            log.info(e.toString());
        }

    }


    public PreparedStatement prepareStatement(String sql) throws SQLException{
        return conn.prepareStatement(sql);
    }


    public void close() throws SQLException{
        conn.close();
    }
}