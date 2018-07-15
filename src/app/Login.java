package app;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.StageStyle;

public class Login extends Application {

    Connection connection;

    public Login() {
        connection = SqliteConnection.Connector();

        if(connection == null) {
            System.out.println("connection not successful");
            System.exit(1);
        }

    }

    public boolean isDbConnection() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isLogin(String user, String password) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from Users where name = ? and password = ?";

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {

            return false;

        } finally {

            preparedStatement.close();
            resultSet.close();

        }
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/app/login.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("Restaurant - Login");
        //stage.setMinHeight(355);
        //stage.setMinWidth(422);
        //stage.setMaximized(true);
        stage.show();
        //stage.setResizable(false); // Фиксированное окно

    }


    public static void main(String[] args) {
        launch(args);
    }
}

//linear-gradient(to bottom, #00BFFF, #ADFF2F)