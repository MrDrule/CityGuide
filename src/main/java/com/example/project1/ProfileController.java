package com.example.project1;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.collections.ObservableList;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfileController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public User user;
    @FXML
    private Button buttonlog;
    PreparedStatement pst= null;
    ResultSet rs=null;
    Connection connection = null;
    int id;
    @FXML
    private Label label;
    @FXML
    private Label labelemail;

    @FXML
    private Label labelname;

    @FXML
    private Label labelpassword;

    @FXML
    private Label labelusername;

    @FXML
    private TableColumn<OtherUsers, SimpleStringProperty> columnName;

    @FXML
    private TableColumn<OtherUsers, SimpleStringProperty> columnUser;
    @FXML
    private TableView<OtherUsers> tableUser;

    @FXML
    private Button btnEdtUsr;
    @FXML
    private Button btnEdtPass;
    @FXML
    private Button btnEdtEmail;
    @FXML
    private Button btnEdtName;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_name;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillTable();
        setUserData();
        setLogButton();
    }
    public void setLogButton(){
        if (User.username != null) {
            buttonlog.setText("Logout");
        }else{
            buttonlog.setText("LogIn/SignUp");
        }
    }

    public void fillTable(){
        try {
            connection = com.example.project1.mysqlconnect.ConnectDb();
            ObservableList<OtherUsers> data = FXCollections.observableArrayList();
            String username=User.username;
            String sql="SELECT username, name FROM favourite WHERE username NOT LIKE '"+username+"' ORDER BY RAND() LIMIT 15";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                data.add(new OtherUsers(rs.getString(1), rs.getString(2)));
            }
            columnUser.setCellValueFactory(new PropertyValueFactory<>("Username"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            tableUser.setItems(data);

        }catch (SQLException e) {
            System.err.println("Error" + e);
        }
    }

    public void setUserData(){
        labelusername.setText(User.username);
        labelemail.setText(User.email);
        labelname.setText(User.name);
        labelpassword.setText(User.password);
    }


    public void EditUsername(){
        if(txt_username.getText().isBlank()==false) {
            try {
                connection = com.example.project1.mysqlconnect.ConnectDb();
                String value1 = txt_username.getText();
                id = UsersDetails.id;
                String sql = "UPDATE users SET username='" + value1 + "' WHERE ID='" + id + "'";
                pst = connection.prepareStatement(sql);
                pst.executeUpdate();
                txt_username.clear();
                JOptionPane.showMessageDialog(null, "Your username have been updated!");
                labelusername.setText(value1);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Please enter your new username");
            alert.showAndWait();
        }
    }
    public void EditPassword(){
        if (txt_password.getText().isBlank()==false) {
            try {
                connection = com.example.project1.mysqlconnect.ConnectDb();
                String value2 = txt_password.getText();
                id = UsersDetails.id;
                String sql = "UPDATE users SET password='" + value2 + "' WHERE ID='" + id + "'";
                pst = connection.prepareStatement(sql);
                pst.executeUpdate();
                labelpassword.setText(value2);
                txt_password.clear();
                JOptionPane.showMessageDialog(null, "Your password have been updated!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Please enter your new password");
            alert.showAndWait();
        }
    }public void EditEmail(){
        if (txt_email.getText().isBlank()==false) {
            try {
                connection = com.example.project1.mysqlconnect.ConnectDb();
                String value3 = txt_email.getText();
                id = UsersDetails.id;
                String sql = "UPDATE users SET email='" + value3 + "' WHERE ID='" + id + "'";
                pst = connection.prepareStatement(sql);
                pst.executeUpdate();
                labelemail.setText(value3);
                txt_email.clear();
                JOptionPane.showMessageDialog(null, "Your email has been updated!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Please enter your new email");
            alert.showAndWait();
        }
    }public void EditName(){
        if (txt_name.getText().isBlank()==false) {
            try {
                connection = com.example.project1.mysqlconnect.ConnectDb();
                String value4 = txt_name.getText();
                id = UsersDetails.id;
                String sql = "UPDATE users SET name='" + value4 + "' WHERE ID='" + id + "'";
                pst = connection.prepareStatement(sql);
                pst.executeUpdate();
                labelname.setText(value4);
                txt_name.clear();
                JOptionPane.showMessageDialog(null, "Your name have been updated!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Please enter your new name");
            alert.showAndWait();
        }
    }
    @FXML
    private void refreshTable() throws SQLException {
        try {
            connection = com.example.project1.mysqlconnect.ConnectDb();
            String username = labelusername.getText();
            String sql = "SELECT * FROM users WHERE username='" + username + "'";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                labelpassword.setText(rs.getString(1));
                labelname.setText(rs.getString(2));
                labelemail.setText(rs.getString(3));
                labelusername.setText(rs.getString(4));
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void switchToFav(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Favourites.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("custom-theme.css")));
        stage.setScene(scene);
        stage.show();
    }
    public void switchToReg(ActionEvent event) throws IOException {
        if (User.username != null) {
            user=new User();
            buttonlog.setText("LogIn/SignUp");
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(String.valueOf(getClass().getResource("custom-theme.css")));
            stage.setScene(scene);
            stage.show();
        }else{
            Parent root = FXMLLoader.load(getClass().getResource("FORMA_RE.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void switchToProf(ActionEvent event) throws IOException {
        if (User.username != null) {
            Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Please Login to check your connection's information!");
            alert.showAndWait();
        }


    }

}