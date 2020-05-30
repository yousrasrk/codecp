/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert.*;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.control.*;

/**
 *
 * @author Amal
 */
public class Alert {

    public void AllGood(String S, ActionEvent event) {
        javafx.scene.control.Alert a = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.NONE);
        a.setGraphic(new ImageView(this.getClass().getResource("/images/success.jpg").toString()));
        a.setHeaderText(S);
        ButtonType OK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        a.getButtonTypes().setAll(OK);
        Optional<ButtonType> result1 = a.showAndWait();
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    public String popupEnregistrer(String S, ActionEvent event) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.NONE);
        alert.setGraphic(new ImageView(this.getClass().getResource("/images/save.png").toString()));
        alert.setTitle("LOG OUT");
        alert.setHeaderText(S);
        ButtonType OUI = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType NON = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(OUI, NON);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == OUI) {
        //dir dakchif data baase
            //revenir vers profile
//            ((Node) event.getSource()).getScene().getWindow().hide();
//            // et afficher alerte success
//            javafx.scene.control.Alert a = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.NONE);
//            a.setGraphic(new ImageView(this.getClass().getResource("/images/success.jpg").toString()));
//            a.setHeaderText(S);
//            ButtonType OK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
//            a.getButtonTypes().setAll(OK);
//            Optional<ButtonType> result1 = a.showAndWait();
            ((Node) event.getSource()).getScene().getWindow().hide();
            return("oui");
            
        }
        return("Non");
    }

    public void Annuler(String S, ActionEvent event) {
        //revenir vers profile

        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.NONE);
        alert.setGraphic(new ImageView(this.getClass().getResource("/images/save.png").toString()));
        alert.setTitle("LOG OUT");
        alert.setHeaderText(S);
        ButtonType OUI = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType NON = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(OUI, NON);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == OUI) {
        //dir dakchif data baase

            //revenir vers profile
            ((Node) event.getSource()).getScene().getWindow().hide();
        // et afficher alerte success

        } else {

        }

    }

    public void AlerErreur(String S, ActionEvent event) {
        // diyal annuler
        javafx.scene.control.Alert al = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.NONE);
        al.setTitle("errur");
        Button btn1 = new Button("hi");
        al.setContentText(S);

        al.setHeaderText(null);
        al.showAndWait();

    }

}
