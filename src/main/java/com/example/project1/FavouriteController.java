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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FavouriteController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button buttonlog;

    private User user;
    @FXML
    private Text textemail;

    @FXML
    private Text textusername;


    @FXML
    private Button button;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<DestList> tblSerFav;
    @FXML
    private TableView<DestList> tblKavFav;
    @FXML
    private TableView<DestList> tblKilFav;
    @FXML
    private TableView<DestList> tblDraFav;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colSerFav;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colKavFav;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colKilFav;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colDraFav;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colFser;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colFkav;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colFkil;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colFdra;

    @FXML
    private ObservableList<DestList> data;
    @FXML
    private ObservableList<DestList> data2;
    @FXML
    private ObservableList<DestList> data3;
    @FXML
    private ObservableList<DestList> data4;
    private mysqlconnect connection;

    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("custom-theme.css")));
        stage.setScene(scene);
        stage.show();
    }

    public void switchToFav(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Favourites.fxml"));
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
            root.setStyle("-fx-background-image:url('com/example/project1/images/login.jpg');");
            stage.setScene(scene);
            stage.show();
        }
    }
    public void setLogButton(){
        if (User.username != null) {
            buttonlog.setText("Logout");
        }else{
            buttonlog.setText("LogIn/SignUp");
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setLogButton();
        String username=User.username;
        Connection conn = connection.ConnectDb();

        Callback<TableColumn<DestList, SimpleStringProperty>, TableCell<DestList, SimpleStringProperty>> cellFactory2=(param) -> {
            //Make the tablecell containing button
            final TableCell<DestList,SimpleStringProperty> cell=new TableCell<DestList,SimpleStringProperty>(){


                //override updatItem method
                @Override
                public void updateItem(SimpleStringProperty item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){setGraphic(null);setText(null);}
                    else {
                        DestList p = getTableView().getItems().get(getIndex());
                        String Name = p.getName();
                        //Creating the action button
                        final Button editButton = new Button("ⓧ");
                        editButton.setOnAction(event -> {
                            try {
                                PreparedStatement stmt2 = conn.prepareStatement("DELETE  FROM favourite WHERE name = ? AND username = ?");
                                stmt2.setString(1, Name);
                                stmt2.setString(2,username);
                                stmt2.executeUpdate();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setContentText("Removed \n" + p.getName() + " off your Favorites!");
                                alert.show();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }

                        });
                        setGraphic(editButton);
                        setText(null);

                    }};};
            //return the cell created
            return cell;};
        colFser.setCellFactory(cellFactory2);
        colFkav.setCellFactory(cellFactory2);
        colFkil.setCellFactory(cellFactory2);
        colFdra.setCellFactory(cellFactory2);

        try {
            data = FXCollections.observableArrayList();
            data2 = FXCollections.observableArrayList();
            data3 = FXCollections.observableArrayList();
            data4 = FXCollections.observableArrayList();
            PreparedStatement stmt = conn.prepareStatement("SELECT name FROM cityguide.favourite WHERE town_id= '1'");
            ResultSet rs= stmt.executeQuery();
            while (rs.next()) {
                data.add(new DestList(rs.getString(1), null,null,null,null));
            }
            PreparedStatement stmt1 = conn.prepareStatement("SELECT name FROM cityguide.favourite WHERE town_id= '2' and username = ?");
            stmt1.setString(1,username);
            ResultSet rs1= stmt1.executeQuery();

            while (rs1.next()) {
                data2.add(new DestList(rs1.getString(1),  null,null,null,null));
            }
            PreparedStatement stmt2 = conn.prepareStatement("SELECT name FROM cityguide.favourite WHERE town_id= '3' and username = ?");
            stmt2.setString(1,username);
            ResultSet rs2= stmt2.executeQuery();
            while (rs2.next()) {
                data3.add(new DestList(rs2.getString(1), null,null,null,null));
            }
            PreparedStatement stmt3 = conn.prepareStatement("SELECT name FROM cityguide.favourite WHERE town_id= '4' and username = ?");
            stmt3.setString(1,username);
            ResultSet rs3= stmt3.executeQuery();
            while (rs3.next()) {
                data4.add(new DestList(rs3.getString(1),  null,null,null,null));
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }

        colKavFav.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colDraFav.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colKilFav.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSerFav.setCellValueFactory(new PropertyValueFactory<>("Name"));

        tblSerFav.setItems(data);
        tblDraFav.setItems(data2);
        tblKavFav.setItems(data3);
        tblKilFav.setItems(data4);

    }
}
