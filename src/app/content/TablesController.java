package app.content;

import app.MainController;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import org.sqlite.core.DB;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class TablesController implements Initializable{

    Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Connection c = null;
        Statement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:app.db");

            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery(" SELECT * FROM Tables;");

            int i = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int table = rs.getInt("table");
                int type = rs.getInt("type");
                float width_b = rs.getFloat("width");
                float heigth_b = rs.getFloat("heigth");
                float x_b = rs.getFloat("x");
                float y_b = rs.getFloat("y");

//                System.out.println("ID: " + id);
//                System.out.println("NAME: " + name);
//                System.out.println("TABLE: " + table);
//                System.out.println("TYPE: " + type);
//                System.out.println("WIDTH: " + width_b);
//                System.out.println("HEIGTH: " + heigth_b);
//                System.out.println("X: " + x_b);
//                System.out.println("Y: " + y_b);
//                System.out.println();

                JFXButton btn_b = new JFXButton();
                btn_b.setText(name);
                btn_b.setStyle("-fx-background-color: lightgreen;");
                btn_b.setLayoutX(x_b);
                btn_b.setLayoutY(y_b);
                btn_b.setPrefWidth(width_b);
                btn_b.setPrefHeight(heigth_b);
                MainController.CA.getChildren().add(btn_b);
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");

    }
}
