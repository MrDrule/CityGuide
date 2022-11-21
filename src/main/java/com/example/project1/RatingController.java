package com.example.project1;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;

public class RatingController implements Initializable {
    @FXML
    private Rating rating;
    @FXML
    private TextField txtfield;
    @FXML
    private Label rn;
    @FXML
    private AnchorPane anchorPane;

    private User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rating.ratingProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number old, Number newT) {
                rn.setText(newT.toString());
            }
        });
    }

    public void ratingButton (ActionEvent event){
        if (User.username != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Thank you for Rating!");
            alert.showAndWait();

            String tf = txtfield.getText();
            double rating = Double.parseDouble(rn.getText());
            String username = User.username;


            System.out.println(tf);
            System.out.println(rating);
            addRatingtoDatabase(username, tf, rating);
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Please Login First!");
            alert.showAndWait();
        }

    }
    @FXML
    private void exit(ActionEvent event){
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static void addRatingtoDatabase(String username,String text,double rate){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cityguide", "root", "");
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO ratings (username , ratingtxt,ratingvalue) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, text);
            preparedStatement.setDouble(3, rate);
            preparedStatement.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception var17) {
            var17.printStackTrace();
        }


    }

}