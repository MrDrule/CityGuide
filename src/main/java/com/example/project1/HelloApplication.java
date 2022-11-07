package com.example.project1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        stage = new Stage();

        stage.setWidth(916);
        stage.setHeight(740);
        stage.setX(700);
        stage.setY(100);
        stage.setResizable(false);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 680, 530);
            stage.setTitle("City Guide");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}