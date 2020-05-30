/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import DaoObj.DaoCompetition;
import DaoObj.daoProbleme;
import Enumeration.Level;
import Obj.Competition;
import Obj.Probleme;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;

import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Amal
 */
public class DetailCompetitionController  implements Initializable {
    
    @FXML
    private TextField DD;

    @FXML
    private TextField DF;

    @FXML
    private TextField Titre;

    @FXML
    private TextField Niveau;

    @FXML
    private Button Annuler;

    @FXML
    private TextField Id;

    @FXML
    private Button Enregistrer;
    @FXML
    private TextField Statut;
    
    //yousra
    
     @FXML
    private TableView<Probleme> tableView;

    @FXML
    private TableColumn<Probleme, String> probleme;

    @FXML
    private TableColumn<Probleme, Level> niveau;
    

     Alert Al = new Alert();
    static String id;
    
      private void initTable() {
        initcol();
    }

    private void initcol() {
        probleme.setCellValueFactory(new PropertyValueFactory<Probleme,String>("Titre"));
        niveau.setCellValueFactory(new PropertyValueFactory<Probleme,Level>("Level"));
        
        tableView.setItems(loadData());
        
    }
    
  
    private ObservableList<Probleme> loadData() {
        ObservableList<Probleme> data_table = FXCollections.observableArrayList();
          
        ResultSet Rs = daoc.AffcherListeProbleme(CompetitionController.getId());
        try {
            while (Rs.next()) {
               data_table.add(new Probleme(Rs.getString(1), "", Rs.getString(2),Level.valueOf(Rs.getString(3)) , ""));


            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeUsersController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return data_table;
    }
    //yousra
    
    private DaoCompetition daoc = new DaoCompetition(Layout_adminController.db);
    ResultSet c;
    public void initChanp() throws SQLException{
        Titre.setText(c.getString(6));
        DD.setText(c.getDate(4).toString());
        DF.setText(c.getDate(5).toString());
        Niveau.setText(c.getString(2));
        Id.setText(c.getString(1));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         //System.out.println("Fack jaouhari" + CompetitionController.getId());
        try {
            
            c = daoc.find(CompetitionController.getId());
            //c.next();
           
            initChanp();
        } catch (SQLException ex) {
            System.out.println("error initialize");
        }
           initTable();
        loadData();
        
    }
    @FXML
    void EnregistrerCompt(ActionEvent event) {
        Competition C = new Competition(Level.EASY, true, Titre.getText(), Date.from(Instant.EPOCH),Date.from(Instant.EPOCH));
        if(daoc.update(C, CompetitionController.getId())){
            System.out.println("yes");
        }else{
            System.out.println("No");
        }
        

    }
    
    
    @FXML
    void Rank(ActionEvent event) throws IOException {
        RankController.id = CompetitionController.getId();
        Parent root = FXMLLoader.load(getClass().getResource("/Admin/fxml/Rank.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }
    
}
