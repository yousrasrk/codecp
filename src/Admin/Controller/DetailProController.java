/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import DaoObj.DaoCompetition;
import DaoObj.daoParticipe;
import DaoObj.daoProbleme;
import DaoObj.daoSolution;
import User.Controller.Listeproblemes;
import User.Controller.ShowProblemeController;
import User.Controller.SolutionController;
import User.Controller.layoutController;
import User.Controller.submitController;
import static User.Controller.submitController.IdPro;
import static User.Controller.submitController.IdUser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Amal
 */
public class DetailProController implements Initializable {

    @FXML
    private Label Titre;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label Niveau;

    @FXML
    private Button buttonshowsolution;

    @FXML
    private GridPane Enonce;

    @FXML
    private Button buttonSubmit;

    @FXML
    private Text Enoncé;
    daoProbleme daoP = new daoProbleme(Layout_adminController.db);
    DaoCompetition daoC = new DaoCompetition(layoutController.db);
//    void loadPr(String path ){
//        File file = new File(path);
//        String Res= new String() ;
//        BufferedReader br;
//        try {
//            br = new BufferedReader (new InputStreamReader(new FileInputStream(file), "UTF8"));
//            String st;
//        
//        while ((st = br.readLine()) != null) {
//  
//            Res += st+"\n";
//        }
//            //(new FileReader(file));
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Probleme.setText(Res);
//        
//    }
//    void loadSo(String path){
//        File file = new File(path);
//        String Res= new String() ;
//        BufferedReader br;
//        try {
//            br = new BufferedReader (new InputStreamReader(new FileInputStream(file), "UTF8"));
//            String st;
//        
//        while ((st = br.readLine()) != null) {
//            //System.out.println(st);
//            Res += st+"\n";
//        }
//            //(new FileReader(file));
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(DetailProController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        Solutions.setText(Res);
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //daoSolution daoS = new daoSolution(Layout_adminController.db);
        ResultSet Rs = daoP.find(ProController.id);

        try {
            Niveau.setText(Rs.getString(3));
            Titre.setText(Rs.getString(4));
            Enoncé.setText(ShowEnonce(Rs.getString(2)));
        } catch (SQLException ex) {
            Logger.getLogger(ShowProblemeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ShowProblemeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Submit(ActionEvent event) {
        Parent root = null;
        try {
            // submitController.IdPro = ProController.id;
            submitController.IdPro = ProController.id;
            submitController.IdUser = "admin";
            submitController.db = Layout_adminController.db;
            
            //System.out.println("The Id probleme is" +submitController.IdPro );
            root = FXMLLoader.load(getClass().getResource("/User/fxml/submit.fxml"));
        } catch (IOException ex) {
            System.out.println("Bouton submit : probleme Load()");

        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void showSolutions(ActionEvent event) {
        Parent root = null;
        if (CompetUserParticipeWithPrblm()) {
            buttonSubmit.setDisable(false);
            System.out.println("sorry");
        } else {
            try {
            //si admin participe a la competition

                SolutionController.Id = ProController.id;
                root = FXMLLoader.load(getClass().getResource("/User/fxml/Solution.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("Bouton submit : probleme Load()");

            }
        }

      
    }

    private String ShowEnonce(String path) throws IOException {
        File file = new File(path);

        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        //(new FileReader(file));

        String st;
        String Res = new String();
        while ((st = br.readLine()) != null) {
            //System.out.println(st);
            Res += st + "\n";
        }
        return Res;
    }

    Boolean CompetUserParticipeWithPrblm() {
        //ga3 les competitions li fihum had prblm
        Vector<String> ListeCompet = daoC.existPr(ProController.id);
        //  System.out.println(ListeCompet.size());
        for (int i = 0; i < ListeCompet.size(); i++) {
            //if had user kayparticiper l chi we7da men dek compet 
            System.out.println(ListeCompet.elementAt(i));
            if (daoParticipe.existUser("admin", ListeCompet.elementAt(i))) {
                return true;
            }
        }
        return false;
    }

}
