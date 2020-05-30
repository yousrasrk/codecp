/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import Database.Database;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author khalf
 */
public class layoutController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button profile;
    public static String Id;
    public static Database db ;
    
    @FXML
    private void Profile(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/User/fxml/Profile.fxml"));
       rootPane.getChildren().setAll(pane);
        }

    @FXML
    private void Messages(ActionEvent event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/User/fxml/messages.fxml"));
        rootPane.getChildren().setAll(pane);

    }
    @FXML
    private void Competitions(ActionEvent event) throws IOException {
        Listecompetitions.id= Id;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/User/fxml/ListeCompetition.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void Probleme(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/User/fxml/ListeProbleme.fxml"));
        rootPane.getChildren().setAll(pane);

    }
    @FXML
    private void Submitions(ActionEvent event) throws IOException {
        Listesubmissions.IdUser= Id;
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/User/fxml/ListeSubmissions.fxml"));
        rootPane.getChildren().setAll(pane);
    }
//    @FXML
//    private void Deconnecter(MouseEvent  event) {
//       
//        
//        Alert alert = new Alert(AlertType.NONE);
//        alert.setGraphic(new ImageView(this.getClass().getResource("/User/fxml/logout1.png").toString()));
//        alert.setTitle("LOG OUT");
//        alert.setHeaderText("Vous voulez vraiment vous deconnectez ");
//        ButtonType OUI = new ButtonType("Oui",ButtonData.YES);
//        ButtonType NON = new ButtonType("Non",ButtonData.CANCEL_CLOSE);
//
//        
//        alert.getButtonTypes().setAll(OUI,NON);
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == OUI){
//            //if oui normqlement deconnecter
//           //((Node)event.getSource()).getScene().getWindow().hide();
//        } 
//        
//        
//    }
     @FXML
    void logout(ActionEvent event) {
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        //else
       AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/User/fxml/Profile.fxml"));
            
        } catch (IOException ex) {
            System.out.println("error");
        }
        
        rootPane.getChildren().setAll(pane);
        
        
        
    }    

    

   
    

    
    
}
