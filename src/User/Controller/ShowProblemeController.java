/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import DaoObj.DaoCompetition;
import DaoObj.daoParticipe;
import DaoObj.daoProbleme;
import DaoObj.daoSubmit;
import Database.Database;

import static User.Controller.submitController.db;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author khalf
 */
public class ShowProblemeController implements Initializable {

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
    private TextArea Enoncé;
    private daoProbleme daoP = new daoProbleme(layoutController.db);
    DaoCompetition daoC = new DaoCompetition(layoutController.db);
    public static String IdPro;
    public static String IdUser;
    public static Database db ;
    Vector<String> ListeCompetUserParticipe = new Vector<String>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ResultSet Rs = daoP.find(IdPro);
        Enoncé.setEditable(false);
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
            submitController.IdPro = IdPro;
            submitController.IdUser = IdUser;
            submitController.db = db;
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
        //si user participe a une competition 

        if (CompetUserParticipeWithPrblm()) {
            buttonSubmit.setDisable(false);
            //System.out.println("sorry");
        } else {
            Parent root = null;
            try {
                SolutionController.Id = IdPro;
                root = FXMLLoader.load(getClass().getResource("/User/fxml/Solution.fxml"));
            } catch (IOException ex) {
                System.out.println("Bouton submit : probleme Load()");

            }

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
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
//        System.out.println("here");
//        System.out.println("The idpro "+ IdPro);
//        System.out.println("The Id user is " + IdUser);
        Vector<String> ListeCompet = daoC.existPr(IdPro);
        //  System.out.println(ListeCompet.size());
        for (int i = 0; i < ListeCompet.size(); i++) {
            //if had user kayparticiper l chi we7da men dek compet 
            //System.out.println(ListeCompet.elementAt(i));
            if (daoParticipe.existUser(IdUser, ListeCompet.elementAt(i))) {
                return true;
            }
        }
        return false;
    }

}
