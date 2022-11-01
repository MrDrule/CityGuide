package com.example.project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
        @FXML
        private AnchorPane pane_login;
        @FXML
        private TextField txt_username;
        @FXML
        private TextField txt_password;
        @FXML
        private Button btn_login;
        @FXML
        private AnchorPane pane_signup;
        @FXML
        private TextField txt_username_up;
        @FXML
        private TextField txt_password_up;
        @FXML
        private TextField email_up;
        @FXML
        private TextField txt_name_up;

    private Stage stage;
    private Scene scene;
    private Parent root;

        Connection conn = null;
        ResultSet rs= null;
        PreparedStatement pst = null;

        public void LoginPaneShow(){
            pane_login.setVisible(true);
            pane_signup.setVisible(false);
        }
        public void SignUpPaneShow(){
            pane_login.setVisible(false);
            pane_signup.setVisible(true);
        }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void add_users(javafx.event.ActionEvent event) {
        conn= com.example.project1.mysqlconnect.ConnectDb();
        String sql = "INSERT INTO users (username,password,name,email) VALUES (?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,txt_username_up.getText());
            pst.setString(2,txt_password_up.getText());
            pst.setString(3,txt_name_up.getText());
            pst.setString(4,email_up.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null,"Saved");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public void Login(javafx.event.ActionEvent event) {
        conn = com.example.project1.mysqlconnect.ConnectDb();
        String sql = "Select * from users where username = ? and password = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1,txt_username.getText());
            pst.setString(2,txt_password.getText());
            rs = pst.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null,"Username and password is correct!");

                btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            }
            else {
                JOptionPane.showMessageDialog(null,"Invalid Username or Password");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e);
        }

    }

    public void switchToMenu(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}