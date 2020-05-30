/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import DaoObj.daoUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 *
 * @author Amal
 */
public class UpdatePsswordController implements Initializable {

    @FXML
    private PasswordField Nouveau;

    @FXML
    private Button enregistrer;

    @FXML
    private Button Annuler;

    @FXML
    private PasswordField Cnouveau;

    @FXML
    private PasswordField ancien;
    public static String Id;
    private daoUser daoU = new daoUser(layoutController.db);
    Admin.Controller.Alert Al;

    @FXML
    void Enregistrer(ActionEvent event) {
        if (ancien.getText().equals(daoU.findPwd(Id))) {
            if (Nouveau.getText().equals(Cnouveau.getText())) {
                //enregistrer the thing
                //success
                daoU.updatePasswd(Id, Nouveau.getText());
                Al.AllGood("Vote mot de passe a bien ete modifier", event);

            } else {
                //confirmation password incorrect
                //System.out.println("makaytsawawchi ");
                Al.AlerErreur("Le mot de passe et confirmation ne sont pas les meme", event);
            }
        } else {
            //confirmation password correct
            Al.AlerErreur("Erreur", event);
        }
    }

    @FXML
    void Annuler(ActionEvent event) {
        Al.Annuler("est ce que vous etes sur de vouloir annuler?", event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
