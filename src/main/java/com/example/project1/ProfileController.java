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
    PreparedStatement pst= null;
    ResultSet rs=null;
    Connection connection = null;
    int id;
    UsersDetails usersDetails = null;
    @FXML
    private Label label;

    @FXML
    private TableView<UsersDetails> tableUser;
    @FXML
    private TableColumn<UsersDetails, String> columnUsername;
    @FXML
    private TableColumn<UsersDetails,String> columnPassword;
    @FXML
    private TableColumn<UsersDetails, String> columnEmail;
    @FXML
    private TableColumn<UsersDetails, String> columnName;
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

    private ObservableList<UsersDetails> data;
    /* private mysqlconnect connection;*/


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = com.example.project1.mysqlconnect.ConnectDb();
        setCellTable();
        data=FXCollections.observableArrayList();
        loadDataFromDatabase();
    }

    public TableView<UsersDetails> getTableUser() {
        return tableUser;
    }

    private void setCellTable(){
        columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
    @FXML
    private void loadDataFromDatabase(){
        try {
            pst=connection.prepareStatement("SELECT * FROM users LIMIT 1");
            rs=pst.executeQuery();
            while (rs.next()){
                data.add(UsersDetails.getInstance(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(5),rs.getString(4)));
            }
        }catch (SQLException e){
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE,null,e);
        }
        tableUser.setItems(data);
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
                JOptionPane.showMessageDialog(null, "Your username have been updated!");
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
    }public void EditPassword(){
        if (txt_password.getText().isBlank()==false) {
            try {
                connection = com.example.project1.mysqlconnect.ConnectDb();
                String value2 = txt_password.getText();
                id = UsersDetails.id;
                String sql = "UPDATE users SET password='" + value2 + "' WHERE ID='" + id + "'";
                pst = connection.prepareStatement(sql);
                pst.executeUpdate();
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
    private void refreshTable() {
        try {
            data.clear();
            id=UsersDetails.id;
            String query = "SELECT * FROM users  WHERE ID='"+id+"' LIMIT 1" ;
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()){
                data.add(new UsersDetails(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getInt("id"),
                        rs.getString("name")
                ));
                tableUser.setItems(data);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
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
        Parent root = FXMLLoader.load(getClass().getResource("FORMA_RE.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
