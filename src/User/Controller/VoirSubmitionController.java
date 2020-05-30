/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author khalf
 */
public class VoirSubmitionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showSolution(ActionEvent event) {
        Parent root = null;
            try {
                 root = FXMLLoader.load(getClass().getResource("/User/fxml/Solution.fxml"));
            } catch (IOException ex) {
                System.out.println("Bouton showsolution : probleme Load()");
                
            }
            
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Sendmsg(ActionEvent event) {
        
        Parent root = null;
            try {
                 root = FXMLLoader.load(getClass().getResource("/User/fxml/sendmessage.fxml"));
            } catch (IOException ex) {
                System.out.println("Bouton repondre : probleme Load()");
                
            }
            
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Nouveau message");
        stage.setScene(scene);
        stage.show();
    }
    
}
