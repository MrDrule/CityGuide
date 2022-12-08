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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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

public class SerresController implements Initializable {

    public TableColumn colPlcId11;
    public TableColumn colPlcId1;

    public TableColumn colPlcId12;
    public TableColumn colPlcId;
    Connection conn=null;
    ResultSet rs= null;
    PreparedStatement pst = null;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btnbtn;
    @FXML
    private TableView<DestList> tableCC;
    @FXML
    private TableView<DestList> tableCC1;
    @FXML
    private TableView<DestList> tableCC11;
    @FXML
    private TableView<DestList> tablecc12;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colName;
    @FXML
    private TableColumn<DestList,SimpleStringProperty> colAdd;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colRat;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colPri;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colFav;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colName1;
    @FXML
    private TableColumn<DestList,SimpleStringProperty> colAdd1;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colRat1;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colPri1;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colFav1;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colName11;
    @FXML
    private TableColumn<DestList,SimpleStringProperty> colAdd11;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colRat11;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colPri11;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colFav11;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colName12;
    @FXML
    private TableColumn<DestList,SimpleStringProperty> colAdd12;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colRat12;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colPri12;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colFav12;
    @FXML
    private ObservableList<DestList> data;
    @FXML
    private ObservableList<DestList> data2;
    @FXML
    private ObservableList<DestList> data3;
    @FXML
    private ObservableList<DestList> data4;

    @FXML
    private WebView webview1;

    @FXML
    private WebView webview2;

    @FXML
    private WebView webview3;

    @FXML
    private WebView webview4;
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
        Parent root = FXMLLoader.load(getClass().getResource("FORMA_RE.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection conn = connection.ConnectDb();
            data = FXCollections.observableArrayList();
            data2 = FXCollections.observableArrayList();
            data3 = FXCollections.observableArrayList();
            data4 = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT name,vicinity,rating,price_level,place_id FROM cityguide.places WHERE town_id=1 AND type LIKE '%museum%'");
            while (rs.next()) {
                data.add(new DestList(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            ResultSet rs1 = conn.createStatement().executeQuery("SELECT name,vicinity,rating,price_level,place_id FROM cityguide.places WHERE town_id=1 AND type LIKE '%cafe%'");
            while (rs1.next()) {
                data2.add(new DestList(rs1.getString(1), rs1.getString(2), rs1.getString(3),rs1.getString(4),rs1.getString(5)));
            }
            ResultSet rs2 = conn.createStatement().executeQuery("SELECT name,vicinity,rating,price_level,place_id FROM cityguide.places WHERE town_id=1 AND type LIKE '%restaurant%'");
            while (rs2.next()) {
                data3.add(new DestList(rs2.getString(1), rs2.getString(2), rs2.getString(3),rs2.getString(4),rs2.getString(5)));
            }
            ResultSet rs3 = conn.createStatement().executeQuery("SELECT name,vicinity,rating,price_level,place_id FROM cityguide.places WHERE town_id=1 ORDER BY RAND() LIMIT 13");
            while (rs3.next()) {
                data4.add(new DestList(rs3.getString(1), rs3.getString(2), rs3.getString(3),rs3.getString(4),rs3.getString(5)));
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAdd.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colRat.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        colPri.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colPlcId.setCellValueFactory(new PropertyValueFactory<>("PlaceId"));

        colName1.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAdd1.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colRat1.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        colPri1.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colPlcId1.setCellValueFactory(new PropertyValueFactory<>("PlaceId"));

        colName11.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAdd11.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colRat11.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        colPri11.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colPlcId11.setCellValueFactory(new PropertyValueFactory<>("PlaceId"));

        colName12.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAdd12.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colRat12.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        colPri12.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colPlcId12.setCellValueFactory(new PropertyValueFactory<>("PlaceId"));

        Callback<TableColumn<DestList, SimpleStringProperty>, TableCell<DestList, SimpleStringProperty>> cellFactory=(param) -> {
            //Make the tablecell containing button
            final TableCell<DestList,SimpleStringProperty> cell=new TableCell<DestList,SimpleStringProperty>(){

                //override updatItem method
                @Override
                public void updateItem(SimpleStringProperty item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){setGraphic(null);setText(null);}
                    else {
                        DestList p = getTableView().getItems().get(getIndex());

                        //Creating the action button
                        final Button editButton = new Button("♡");
                        editButton.setOnAction(event -> {
                            if (User.username != null) {
                                String username=User.username;
                                String Name= p.getName();
                                String Address= p.getAddress();
                                String Rating=p.getRating();
                                conn = com.example.project1.mysqlconnect.ConnectDb();
                                String sql = "INSERT INTO favourite (username, name, vicinity, rating, town_id) VALUES (?,?,?,?,?)";
                                try {
                                    pst = conn.prepareStatement(sql);
                                    pst.setString(1, username);
                                    pst.setString(2, Name);
                                    pst.setString(3, Address);
                                    pst.setString(4, Rating);
                                    pst.setString(5, "3");
                                    pst.execute();

                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, e);
                                }

                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setContentText("You added \n" + p.getName() + " to your Favorites!");
                                alert.show();

                            }});
                        setGraphic(editButton);
                        setText(null);

                    }
                };


            };

            //return the cell created
            return cell;};

        colFav.setCellFactory(cellFactory);
        colFav1.setCellFactory(cellFactory);
        colFav11.setCellFactory(cellFactory);
        colFav12.setCellFactory(cellFactory);

        tableCC.setItems(null);
        tableCC.setItems(data);

        tableCC1.setItems(null);
        tableCC1.setItems(data2);

        tableCC11.setItems(null);
        tableCC11.setItems(data3);

        tablecc12.setItems(null);
        tablecc12.setItems(data4);

        //Loading Webviews
        WebEngine webEngine = webview1.getEngine();
        webEngine.load("https://snazzymaps.com/embed/443690");

        webEngine = webview2.getEngine();
        webEngine.load("https://www.protothema.gr/tag/serres/");

        webEngine = webview3.getEngine();
        webEngine.load("https://forecast7.com/en/41d0923d54/serres/");

    }

    @FXML
    private void loadDataFromDatabase (javafx.event.ActionEvent event){
        try {
            Connection conn = connection.ConnectDb();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT name,vicinity,rating,price_level,place_id FROM cityguide.places WHERE town_id=4 AND type LIKE '%museum%'");
            while (rs.next()) {
                data.add(new DestList(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5)));

            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        }
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAdd.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colRat.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        colPri.setCellValueFactory(new PropertyValueFactory<>("Price"));

        tableCC.setItems(null);
        tableCC.setItems(data);

    }
}
