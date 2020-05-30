/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import DaoObj.daoUser;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author Amal
 */
public class ProfilController implements Initializable {

    
    @FXML
    private Label date;

    @FXML
    private ImageView img = new ImageView();

    @FXML
    private Label language;

    @FXML
    private Label tele;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private Label email;

    @FXML
    private Circle myCircle;

    @FXML
    private Label username;
    private daoUser daoU = new daoUser(Layout_adminController.db);
    ResultSet U;

    @FXML
    void AfficherDetail(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Admin/fxml/Profile.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    void Usero() {
        //System.out.println("the id " + ListeUsersController.getId());
        this.U = daoU.find(ListeUsersController.getId());
//         try {
//            System.out.println(U.getString(1));
//        } catch (SQLException ex) {
//            System.out.println("hna muchkil");
//        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Usero();
        try {
            username.setText(U.getString("Username"));
            date.setText(U.getString(10));
            language.setText(U.getString(9));
            tele.setText(U.getString(5));
            nom.setText(U.getString(7));
            prenom.setText(U.getString(8));
            email.setText(U.getString(4));
////            System.out.println(U.getString("path"));
////            Image Im = new Image("C:\\Users\\Amal\\Desktop\\AMALinfo2\\Java\\FinCodeCp\\UserProfile\\Username.jpg" , true);
            File file = new File(U.getString("Photo"));
            Image image = new Image(file.toURI().toString());
             myCircle.setFill(new ImagePattern(image));
            img.setImage(image);
        } catch (SQLException ex) {
            System.out.println("hna muchkil");
        }


 
//        Image im = new Image("/images/email.png",false);
//        
//        myCircle.setFill(new ImagePattern(im));
        

    }
}
