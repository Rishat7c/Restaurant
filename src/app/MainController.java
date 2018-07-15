package app;

import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.scene.layout.AnchorPane;

import javafx.scene.input.MouseEvent;
import sun.plugin.javascript.navig.Anchor;

public class MainController implements Initializable {

    @FXML
    private Label userlbl;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane workArea;

    @FXML
    public AnchorPane contentArea;

    public static AnchorPane rootP;
    public static JFXDrawer rootD;
    public static Label rootU;
    public AnchorPane rootW;
    public static AnchorPane CA;
    private double AmountPos[];

    public void initialize(URL Location, ResourceBundle resources) {

        rootP = anchorPane;
        rootD = drawer;
        rootU = userlbl;
        rootW = workArea;
        CA = contentArea;

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/app/content/Tables.fxml"));
            //CA.getChildren().removeAll();
            //CA.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            VBox box = FXMLLoader.load(getClass().getResource("/app/MainContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(MainContentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        AmountPos = new double[4];
        AmountPos[0] = rootP.getPrefWidth(); // Узнаем размер главного AnchorPane
        AmountPos[1] = rootD.getPrefWidth(); // Узнаем размер Drawer
        AmountPos[2] = rootW.getPrefWidth(); // Узнаем размер рабочей области workArea
        AmountPos[3] = AmountPos[0] - AmountPos[1]; // Узнали какого размера должна быть рабочая область в момент открытого бургера

        rootW.setLeftAnchor(rootW,200.0); // Last position 230.0

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();

            if(drawer.isShown())
            {
                drawer.close(); // close
                //userlbl.setText(String.valueOf(AmountPos[0]));
                rootW.setLeftAnchor(rootW,200.0);

            } else {
                drawer.open(); // open
                //userlbl.setText(String.valueOf(AmountPos[1]));
                //rootW.setPrefWidth(AmountPos[3]);
                rootW.setLeftAnchor(rootW, 0.0);
            }
        });

    }

    public void GetUser(String user) {
        userlbl.setText(user + " (admin)");
    }

}
