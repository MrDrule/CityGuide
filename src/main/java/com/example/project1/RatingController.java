package com.example.project1;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

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
    private static mysqlconnect connection;

    private static final Connection conn = connection.ConnectDb();

    static PreparedStatement pst = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        rating.ratingProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number old, Number newT) {
                rn.setText(newT.toString());
            }
        });
    }

    public void ratingButton(ActionEvent event) {
        if (User.username != null) {

            String tf = txtfield.getText();
            double rating = Double.parseDouble(rn.getText());
            String username = User.username;
            String name = RatingPlace.name;
            String id = RatingPlace.id;

            checkratingindb(username, tf, rating, name, id);

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Please Login First!");
            alert.showAndWait();
        }

    }

    @FXML
    private void exit(ActionEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static void addRatingtoDatabase(String username, String text, double rate, String name, String id) {
        try {

            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO ratings (username ,name, ratingtxt,ratingvalue,place_id) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, text);
            preparedStatement.setDouble(4, rate);
            preparedStatement.setString(5, id);
            preparedStatement.executeUpdate();
            stmt.close();

        } catch (Exception var17) {
            var17.printStackTrace();
        }
    }

    public static void checkratingindb(String username, String text, double rate, String name, String id) {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM cityguide.ratings WHERE place_id LIKE '" + id + "' AND username LIKE '" + username + "'");
            if (!rs.next()) {
                addRatingtoDatabase(username, text, rate, name, id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setHeaderText("Thank you for Rating!");
                alert.showAndWait();
                alert.close();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setHeaderText("You already rated this Establishment");
                alert.setContentText("Do you want to replace the previous rating?");
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                final Optional<ButtonType> result = alert.showAndWait();
                if (!result.isPresent() || result.get() == ButtonType.YES) {
                    updaterating(username, text, rate, name, id);
                } else {
                    alert.close();
                }


            }


        } catch (Exception var17) {
            var17.printStackTrace();
        }
    }

    public static void updaterating(String username, String text, double rate, String name, String id) {
        String sql = "UPDATE ratings SET ratingtxt=? , ratingvalue=? WHERE username=? AND place_id=?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, text);
            pst.setDouble(2, rate);
            pst.setString(3, username);
            pst.setString(4, id);
            pst.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}