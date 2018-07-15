package app;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainContentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void open_Menu(javafx.event.ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/app/content/Menu.fxml"));
        MainController.CA.getChildren().removeAll();
        MainController.CA.getChildren().setAll(fxml);
    }

    @FXML
    private void open_Stock(javafx.event.ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/app/content/Stock.fxml"));
        MainController.CA.getChildren().removeAll();
        MainController.CA.getChildren().setAll(fxml);
    }

    @FXML
    private void open_Alcohol(javafx.event.ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/app/content/Alcohol.fxml"));
        MainController.CA.getChildren().removeAll();
        MainController.CA.getChildren().setAll(fxml);
    }

    @FXML
    private void open_Staff(javafx.event.ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/app/content/Staff.fxml"));
        MainController.CA.getChildren().removeAll();
        MainController.CA.getChildren().setAll(fxml);
    }

    @FXML
    private void open_Card(javafx.event.ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/app/content/Card.fxml"));
        MainController.CA.getChildren().removeAll();
        MainController.CA.getChildren().setAll(fxml);
    }

    public void SignOut(javafx.event.ActionEvent event) {

        try {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            stage.initStyle(StageStyle.TRANSPARENT);
            Pane root = loader.load(getClass().getResource("/app/login.fxml").openStream());
            Scene scene = new Scene(root);
            scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
            stage.setScene(scene);
            stage.setTitle("Restaurant - Login");
            stage.show();

        } catch (Exception e) {

        }

    }

}