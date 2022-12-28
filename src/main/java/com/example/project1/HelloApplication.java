package com.example.project1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.sql.*;
import org.apache.ibatis.jdbc.ScriptRunner;


public class HelloApplication extends Application {
    private static mysqlconnect connection;
    private static final Connection conn = connection.ConnectDb();

    @Override
    public void start(Stage stage) throws IOException {

        stage = new Stage();
        stage.setWidth(916);
        stage.setHeight(740);
        stage.setX(300);
        stage.setY(100);
        stage.setResizable(false);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 680, 530);
            stage.setTitle("City Guide");
            stage.setScene(scene);
            stage.show();
            scene.getStylesheets().add(String.valueOf(getClass().getResource("custom-theme.css")));


        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {
       //checkdbexist();
       // String[] arguments = new String[] {""};
        //mainAPI.main(arguments);
        launch(args);
    }
    public static void checkdbexist(){
        ResultSet rs = null;
        try{
            String dbName = "cityguidetest";
            if(conn != null){
                System.out.println("check if a database exists using java");
                rs = conn.getMetaData().getCatalogs();

                while(rs.next()){
                    String catalogs = rs.getString(1);

                    if(dbName.equals(catalogs)){
                        System.out.println("the database "+dbName+" exists");
                    }
                }

            }
            else{
                System.out.println("unable to create database connection");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            if( rs != null){
                try{
                    rs.close();
                }
                catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
            if( conn != null){
                try{
                    conn.close();
                }
                catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        }
    }

}



