package com.example.project1;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class mysqlconnect {
    Connection conn = null;
    public static Connection ConnectDb(){
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/project","root","");
             JOptionPane.showMessageDialog(null,"Connection Established");
             return conn;
         }catch (Exception e){
             JOptionPane.showMessageDialog(null,e);
             return null;
         }

    }
}
