package com.example.project1;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mysqlconnect {
    Connection conn = null;
    public static String count;
    public static Connection ConnectDb(){
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityguide","root","");
             //JOptionPane.showMessageDialog(null,"Connection Established");
             return conn;
         }catch (Exception e){
             JOptionPane.showMessageDialog(null,e);
             return null;
         }
    }

    public static String placescount() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cityguide","root","");
        ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) FROM places;");
        count = rs.getString(1);
        return count;
    }
}
