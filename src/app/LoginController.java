package app;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {

    public Login login = new Login();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ImageView btn_setting;
    @FXML
    private ImageView btn_user;
    @FXML
    private ImageView btn_exit;
    @FXML
    private ImageView btn_info;
    @FXML
    private AnchorPane h_user,h_setting,h_info;

    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) {
        if(event.getTarget() == btn_user) {
            if(h_user.isVisible() == false) {
                h_user.setVisible(true);
                h_setting.setVisible(false);
                h_info.setVisible(false);
            } else {
                h_user.setVisible(false);
            }
        } else if(event.getTarget() == btn_setting) {
            if(h_setting.isVisible() == false) {
                h_setting.setVisible(true);
                h_user.setVisible(false);
                h_info.setVisible(false);
            } else {
                h_setting.setVisible(false);
            }
        } else if(event.getTarget() == btn_info) {
            if(h_info.isVisible() == false) {
                h_setting.setVisible(false);
                h_user.setVisible(false);
                h_info.setVisible(true);
            } else {
                h_info.setVisible(false);
            }
        } else if(event.getTarget() == btn_exit) {
            System.exit(0);
        }
    }

    @FXML
    private void btnLoginAction(ActionEvent event) {

        try {
            if (login.isLogin(txtUsername.getText(), txtPassword.getText())) {

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("/app/Main.fxml").openStream());
                MainController mainController = (MainController)loader.getController();
                mainController.GetUser(txtUsername.getText());
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Restaurant - Main");

                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setWidth(bounds.getWidth());
                stage.setHeight(bounds.getHeight());
                stage.setMaximized(true);

                stage.show();

            } else {

                isConnected.setText("Username or password invalid!");

            }
        } catch (SQLException e) {

            isConnected.setText("Username or password invalid!");
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

//        if(txtUsername.getText().equals("test") && txtPassword.getText().equals("test")) {
//
//            ((Node) (event.getSource())).getScene().getWindow().hide();
//
//            Parent parent = FXMLLoader.load(getClass().getResource("/app/Main.fxml"));
//            Stage stage = new Stage();
//            Scene scene = new Scene(parent);
//            stage.setScene(scene);
//            stage.setTitle("Main Frame");
//            stage.show();
//
//        } else {
//            lblMessage.setText("Username or password invalid!");
//        }
    }

    /**
     * Initialize - контроллер класс
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO: Дописать конструктор

//        if(login.isDbConnection()) {
//            isConnected.setText("Connected");
//        } else {
//            isConnected.setText("Not connected");
//        }

        if(!login.isDbConnection()){
            isConnected.setText("Not connected");
        }

    }

}
