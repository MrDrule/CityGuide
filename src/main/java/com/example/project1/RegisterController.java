package com.example.project1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {
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
    @FXML
    private Button btn_signUp;


    private Stage stage;
    private Scene scene;
    private Parent root;
    ResultSet rs= null;
    PreparedStatement pst = null;
    private static mysqlconnect connection;

    private static final Connection conn = connection.ConnectDb();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void add_users() {
        String sql = "INSERT INTO users (username,password,name,email) VALUES (?,?,?,?)";
        if (txt_username_up.getText().isBlank()==false && txt_password_up.getText().isBlank()==false && email_up.getText().isBlank()==false &&txt_name_up.getText().isBlank()==false){
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
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Please enter your registration information");
            alert.showAndWait();
        }

    }
    public void switchToMenu(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToLogin(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("FORMA_RE.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        root.setStyle("-fx-background-image:url('com/example/project1/images/login.jpg');");
        stage.setScene(scene);
        stage.show();
    }
}
