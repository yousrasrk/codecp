/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

//import dialogPane.DialogPane;
import Admin.Controller.Layout_adminController;
import Admin.Controller.ListeUsersController;
import Connect.LoginController;
import DaoObj.daoUser;
import Obj.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import sun.security.jgss.LoginConfigImpl;

/**
 * FXML Controller class
 *
 * @author khalf
 */
public class Profile implements Initializable {

    /**
     * Initializes the controller class.
     */
    //private AnchorPane root;
    @FXML
    private Circle myCircle;
    @FXML
    private Label Email;
    @FXML
    private Label LanguageF;
    @FXML
    private Label Usernaem;

    @FXML
    private Label DI;

    @FXML
    private Label Tel;

    daoUser daoU = new daoUser(Layout_adminController.db);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SetProfile();
    }

    private void SetProfile() {
        ResultSet U = daoU.find(layoutController.Id);

        try {
            Usernaem.setText(U.getString("Username"));
            DI.setText(U.getString(10));
            LanguageF.setText(U.getString(9));
            Tel.setText(U.getString(5));
            Email.setText(U.getString(4));
            File file = new File(U.getString("Photo"));
            Image image = new Image(file.toURI().toString());
            myCircle.setFill(new ImagePattern(image));
            ImageView img = new ImageView();
            img.setImage(image);

        } catch (SQLException ex) {
            System.out.println("hna muchkil");
        }
    }

    @FXML
    private void ModifierProfil(ActionEvent event) {
        Parent root = null;

        try {
            ModifierProfileController.Id= layoutController.Id;
            System.out.println("The id is " +  ModifierProfileController.Id);
            root = FXMLLoader.load(getClass().getResource("/User/fxml/ModifierProfile.fxml"));
        } catch (IOException ex) {
            System.out.println("Bouton Modifier : probleme Load()");
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

}
