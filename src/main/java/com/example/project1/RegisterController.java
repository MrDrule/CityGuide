package com.example.project1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class RegisterController {
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
    static ResultSet rs= null;
    static PreparedStatement pst = null;
    private static mysqlconnect connection;
    private static final Connection conn = connection.ConnectDb();
    public void add_users() throws SQLException {
        String sql = "INSERT INTO users (username,password,name,email) VALUES (?,?,?,?)";
        if (txt_username_up.getText().isBlank()==false && txt_password_up.getText().isBlank()==false && email_up.getText().isBlank()==false &&txt_name_up.getText().isBlank()==false){
            if (!checkusername(txt_username_up.getText())&&!checkemail(email_up.getText())){
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, txt_username_up.getText());
                    pst.setString(2, txt_password_up.getText());
                    pst.setString(3, txt_name_up.getText());
                    pst.setString(4, email_up.getText());
                    pst.execute();

                    JOptionPane.showMessageDialog(null, "Account has created!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }else if (checkusername(txt_username_up.getText())){
                if (checkemail(email_up.getText())){
                    JOptionPane.showMessageDialog(null, "Username and Email already are used!");
                }else{
                    JOptionPane.showMessageDialog(null, "Username is taken!");
                }
            }else if (checkemail(email_up.getText())){
                if (checkusername(txt_username_up.getText())){
                    JOptionPane.showMessageDialog(null, "Username and Email already are used!");
                }else{
                    JOptionPane.showMessageDialog(null, "Email has been used!");
                }
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
        stage.setScene(scene);
        stage.show();
    }

    public static boolean checkusername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1,username);
        rs = pst.executeQuery();
        if(rs.next()){
            return true;
        }else
            return false;
    }
    public static boolean checkemail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email=?";
        pst = conn.prepareStatement(sql);
        pst.setString(1,email);
        rs = pst.executeQuery();
        if(rs.next()){
            return true;
        }else
            return false;
    }

}
