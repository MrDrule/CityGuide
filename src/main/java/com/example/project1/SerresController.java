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
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class SerresController implements Initializable {
    @FXML
    private Button buttonlog;
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
    private TableColumn<DestList, SimpleStringProperty> colPlcId;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colRating;
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
    private TableColumn<DestList, SimpleStringProperty> colPlcId1;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colRating1;
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
    private TableColumn<DestList, SimpleStringProperty> colPlcId11;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colRating11;
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
    private TableColumn<DestList, SimpleStringProperty> colPlcId12;
    @FXML
    private TableColumn<DestList, SimpleStringProperty> colRating12;
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
    private final Connection conn = connection.ConnectDb();


    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("custom-theme.css")));
        stage.setScene(scene);
        stage.show();
    }

    public void switchToFav(ActionEvent event) throws IOException {
        if (User.username != null) {
            Parent root = FXMLLoader.load(getClass().getResource("Favourites.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.getDialogPane().setHeaderText("Please Login to check your connection's information!");
            alert.showAndWait();
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
    public void switchToReg(ActionEvent event) throws IOException {
        if (User.username != null) {
            User user = new User();
            buttonlog.setText("LogIn/SignUp");
            Parent root = FXMLLoader.load(getClass().getResource("Serres.fxml"));
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
        try {
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
            ResultSet rs3 = conn.createStatement().executeQuery("SELECT name,vicinity,rating,price_level,place_id FROM cityguide.places WHERE town_id=1 AND type NOT LIKE '%museum%' ORDER BY rating DESC LIMIT 10");
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
                        String username=User.username;
                        String Name= p.getName();
                        String Address= p.getAddress();
                        String Rating=p.getRating();
                        //Creating the action button

                        final Button editButton = new Button("♡");
                        if (User.username != null && Name!=null){
                            try {
                                Integer liked = checkbutton(username,Name);
                                if (liked==1){
                                    editButton.setText("❤");
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        editButton.setOnAction(event -> {
                            if (User.username != null) {
                                try {
                                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cityguide.favourite WHERE name= ? AND username= ?");
                                    stmt.setString(1, Name);
                                    stmt.setString(2,username);
                                    ResultSet rs = stmt.executeQuery();
                                    if (!rs.next()){
                                        String sql = "INSERT INTO favourite (username, name, vicinity, rating, town_id) VALUES (?,?,?,?,?)";
                                        try {
                                            pst = conn.prepareStatement(sql);
                                            pst.setString(1, username);
                                            pst.setString(2, Name);
                                            pst.setString(3, Address);
                                            pst.setString(4, Rating);
                                            pst.setString(5, "1");
                                            pst.execute();

                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setContentText("You added \n" + p.getName() + " to your Favorites!");
                                            editButton.setText("❤");
                                            alert.show();
                                        } catch (Exception e) {
                                            JOptionPane.showMessageDialog(null, e);}
                                    }
                                    else{
                                        PreparedStatement stmt2 = conn.prepareStatement("DELETE  FROM favourite WHERE name = ? AND username=?");
                                        stmt2.setString(1, Name);
                                        stmt2.setString(2,username);
                                        stmt2.executeUpdate();
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setContentText("Removed \n" + p.getName() + " off your Favorites!");
                                        editButton.setText("♡");
                                        alert.show();
                                    }} catch (SQLException e) {
                                    e.printStackTrace();
                                }

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
                        String username=User.username;
                        String Name= p.getName();
                        //Creating the action button
                        Button editButton = new Button("☆");
                        if (User.username != null && Name!=null){
                            try {
                                Integer rated = checkratebutton(username,Name);
                                if (rated==1){
                                    editButton.setText("★");
                                }
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        editButton.setOnAction(event -> {
                            try {
                                String id=p.getPlaceId();
                                RatingPlace rate= new RatingPlace(Name,id);
                                Parent parent = FXMLLoader.load(getClass().getResource("Rating.fxml"));
                                Scene scene = new Scene(parent);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }

                        });
                        setGraphic(editButton);
                        setText(null);

                    }};};
            //return the cell created
            return cell;};
        colRating.setCellFactory(cellFactory2);
        colRating1.setCellFactory(cellFactory2);
        colRating11.setCellFactory(cellFactory2);
        colRating12.setCellFactory(cellFactory2);

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

        webEngine = webview4.getEngine();
        File b = new File("src/main/java/com/example/project1/serresweather.html");
        webEngine.load(b.toURI().toString());


    }
    public Integer checkbutton(String username , String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cityguide.favourite WHERE name= ? AND username= ?");
        stmt.setString(1, name);
        stmt.setString(2,username);
        ResultSet rs5 = stmt.executeQuery();
        if(rs5.next()){
            return 1;
        }else {
            return 0;
        }
    }
    public Integer checkratebutton(String username , String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cityguide.ratings WHERE name= ? AND username= ?");
        stmt.setString(1, name);
        stmt.setString(2,username);
        ResultSet rs5 = stmt.executeQuery();
        if(rs5.next()){
            return 1;
        }else {
            return 0;
        }
    }

}
