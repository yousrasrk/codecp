/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import DaoObj.daoMessagePerso;
import DaoObj.daoUser;
import Obj.Message_Perso;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Amal
 */
public class EnvoyerMsg implements Initializable {

    @FXML
    private TextArea Msg;

    @FXML
    private TextField Obj;

    @FXML
    private TextField Recep;

    @FXML
    private Button Annuler;

    @FXML
    private Button Enregistrer;
    private daoMessagePerso DaoM = new daoMessagePerso(Layout_adminController.db);
    private Admin.Controller.Alert Al = new Alert();

    @FXML
    void EnvoyerDef(ActionEvent event) {
        daoUser U = new daoUser(Layout_adminController.db);
        String Id_Recep = U.findId(Recep.getText());
        if (Id_Recep.equals("")) {
            //username incorrect
            //System.out.println("Username incorrect");
            Al.AlerErreur("Username incorrect", event);
            
        } else {
            
            Message_Perso Ms = new Message_Perso("admin", Id_Recep, Msg.getText(), Obj.getText());
            //System.out.println(Layout_adminController.Id+" "+Recep.getText()+" "+Msg.getText()+" "+Obj.getText());
            if (DaoM.create(Ms)) {
                //System.out.println("Msg envoyer");
                //System.out.println("Msg sent");
                Al.AllGood("Message envoye", event);
            } else {
                //System.out.println("Msg Non envoye");
                //System.out.println("Error");
                Al.AlerErreur("Une erreur est survenue", event);
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
