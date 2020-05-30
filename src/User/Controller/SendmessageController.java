/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import Admin.Controller.Layout_adminController;
import DaoObj.daoMessagePerso;
import DaoObj.daoUser;
import Obj.Message_Perso;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


/**
 * FXML Controller class
 *
 * @author khalf
 */
public class SendmessageController implements Initializable {

    @FXML
    private Button buttonEnvoyer;

    @FXML
    private Button buttonannuler;

    @FXML
    private TextArea message;

    @FXML
    private TextField objet;

    @FXML
    private TextField Destinatair;
    Admin.Controller.Alert Al;
    private daoMessagePerso DaoM = new daoMessagePerso(layoutController.db);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SendMessage(ActionEvent event) {
        //recuperer message
        daoUser U = new daoUser(Layout_adminController.db);
        String Id_Dest = U.findId(Destinatair.getText());
        if (Id_Dest.equals("")) {
            //username incorrect
            System.out.println("Username incorrect");
        } else {

            Message_Perso Ms = new Message_Perso(layoutController.Id, Id_Dest, message.getText(), objet.getText());
            //System.out.println(Layout_adminController.Id+" "+Recep.getText()+" "+Msg.getText()+" "+Obj.getText());
            if (DaoM.create(Ms)) {
                //System.out.println("Msg envoyer");
                // System.out.println("Msg sent");
                // Al.AllGood("Msg envoye" , event);
                ((Node) event.getSource()).getScene().getWindow().hide();
                // et afficher alerte success
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setGraphic(new ImageView(this.getClass().getResource("/images/success_icon.png").toString()));
                alert.setHeaderText("Votre message a été bien envoyer ");
                ButtonType OK = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                alert.getButtonTypes().setAll(OK);
                Optional<ButtonType> result = alert.showAndWait();
            } else {
                //System.out.println("Msg Non envoye");
                System.out.println("Error");
                Al.AlerErreur("Message non envoye", event);
            }
        }

        //revenir vers la page precedente
        //si tout passe bien 
        //revenir vers profile
    }

    @FXML
    private void Annuler(ActionEvent event) {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Al.Annuler("est ce que vous etes sur de vouloir annuler?", event);
    }

}
