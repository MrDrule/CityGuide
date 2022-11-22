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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Button btnEdit;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_email;

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

    }
    @FXML
    private void loadDataFromDatabase(){
        try {
            pst=connection.prepareStatement("SELECT * FROM users LIMIT 1");
            rs=pst.executeQuery();
            while (rs.next()){
                data.add(UsersDetails.getInstance(rs.getNString(1),rs.getString(2),rs.getString(4)));
            }
        }catch (SQLException e){
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE,null,e);
        }
        tableUser.setItems(data);
    }

    public void Edit(){
        try {
            connection = com.example.project1.mysqlconnect.ConnectDb();
            String value1 = txt_username.getText();
            String value2 = txt_password.getText();
            String value3 = txt_email.getText();
            String sql ="UPDATE users SET username='"+value1+"',password='"+value2+"',email='"+value3+"' WHERE username='"+value1+"'";
            pst=connection.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Update!");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e);
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

}
