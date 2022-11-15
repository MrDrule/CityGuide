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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label label;

    @FXML
    private TableView<UsersDetails> tableUser;
    @FXML
    private TableColumn<UsersDetails, SimpleStringProperty> columnUsername;
    @FXML
    private TableColumn<UsersDetails,SimpleStringProperty> columnPassword;
    @FXML
    private TableColumn<UsersDetails, SimpleStringProperty> columnEmail;
    @FXML
    private Button btnLoad;
    private ObservableList<UsersDetails> data;
    private mysqlconnect connection;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = new mysqlconnect();
    }
    @FXML
    private void loadDataFromDatabase(javafx.event.ActionEvent event){
        try {
            Connection conn = connection.ConnectDb();
            data= FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT username,password,email from cityguide.users");
            while (rs.next()){
                data.add(new UsersDetails(rs.getString(1),rs.getString(2),rs.getString(3)));

            }
        }catch (SQLException e){
            System.err.println("Error"+e);
        }
        columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableUser.setItems(null);
        tableUser.setItems(data);
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
