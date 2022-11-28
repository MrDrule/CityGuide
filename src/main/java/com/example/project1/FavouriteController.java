package com.example.project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FavouriteController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private User user;
    @FXML
    private Text textemail;

    @FXML
    private Text textusername;


    @FXML
    private Button button;

    @FXML
    private AnchorPane anchorPane;

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

    @FXML
    public void toRating(ActionEvent event)throws IOException {
        try {


            Parent root = FXMLLoader.load(getClass().getResource("Rating.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Rating Window");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            System.out.println("Cant load Window");
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String username=User.username;
        String email=User.email;

        System.out.println(username+" "+email);

        textusername.setText(username);
        textemail.setText(email);




    }
}
