/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import Database.Database;
import User.Controller.Listesubmissions;
import static User.Controller.layoutController.Id;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Huawei
 */
public class Layout_adminController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    public static String Id;
    public static Database db ;
    
    public AnchorPane getRootPane() {
        return rootPane;
    }

//    public Layout_adminController(String Id) {
//        System.out.println(Id);
//         this.Id = Id;
//        System.out.println("Id d layout in controller=" + Id);
//    }
    
    
    @FXML
    private void Utilisateurs(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/fxml/ListeUsers.fxml"));
        //ListeUsersController MA = new ListeUsersController();
        //MA.initData(Id,db);
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void Messages(ActionEvent event) throws IOException {
      // AnchorPane pane = FXMLLoader.load(getClass().getResource("/Admin/fxml/Messages_Admin.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/fxml/Messages_Admin.fxml"));
       //Messages_AdminController MA = new Messages_AdminController();
        //System.out.println("Id d layout2 =" + Id);
       // MA.initData(Id,db);
        //loader.setController(MA);
//        Messages_AdminController.Id= Id;
//        Messages_AdminController.db=db;
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);

    }
    @FXML
    private void Competitions(ActionEvent event) throws IOException {
    //AnchorPane pane = FXMLLoader.load(getClass().getResource("/Admin/fxml/ListeCompetitions.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/fxml/ListeCompetitions.fxml"));
        //CompetitionController LC = new CompetitionController();
       // LC.initData(Id,db);
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void Probleme(ActionEvent event) throws IOException {
         //AnchorPane pane;
        //pane = FXMLLoader.load(getClass().getResource("/Admin/fxml/ListeProblemes.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/fxml/ListeProblemes.fxml"));
        //Listeproblemes LP = new Listeproblemes();
        //LP.initData(Id,db);
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);

    }
    @FXML
    private void Dashboard(ActionEvent event) throws IOException {
// AnchorPane pane = FXMLLoader.load(getClass().getResource("/Admin/fxml/Dashboard.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/fxml/Dashboard.fxml"));
        //DashboardController DS = new DashboardController();
        //DS.initData(Id , db);
        AnchorPane pane = loader.load();
        rootPane.getChildren().setAll(pane);
    }
     @FXML
    private void Submitions(ActionEvent event) throws IOException {
        Listesubmissions.IdUser= "admin";
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/User/fxml/ListeSubmissions.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //this.Id = "admin";
        
        // TODO
//        AnchorPane pane = null;
//        try {
//            pane = FXMLLoader.load(getClass().getResource("/Admin/fxml/Dashboard.fxml"));
//        } catch (IOException ex) {
//            
//        }
//        rootPane.getChildren().setAll(pane);
//    }
//    public void initData(String Id  , Database db) {
//        //this.Id = Id;
//        //System.out.println("Id d layout =" + Id);
//        this.db = db;
        
        //yousra
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/fxml/Dashboard.fxml"));
        //DashboardController DS = new DashboardController();
        //DS.initData(Id , db);
        AnchorPane pane;
        try {
            pane = loader.load();
                    rootPane.getChildren().setAll(pane);

        } catch (IOException ex) {
            Logger.getLogger(Layout_adminController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        //yousra
       
 }
   
    
       @FXML
    void logout(ActionEvent event) {
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
            window.close();
    }
    
    
}
