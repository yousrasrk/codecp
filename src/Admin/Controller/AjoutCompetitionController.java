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
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Amal
 */
public class AjoutCompetitionController implements Initializable {

    @FXML
    private DatePicker Date_fin;

    @FXML
    private TextField Titre;

    @FXML
    private DatePicker Date_debut;

    @FXML
    private Button Annuler;
    private DaoCompetition daoC = new DaoCompetition(Layout_adminController.db);
    daoProbleme daoP = new daoProbleme(Layout_adminController.db);

    @FXML
    private Button Enregistrer;

    
    Alert Al = new Alert();
    
    
    
    
    
    
    //table=================================
    

    @FXML
    private TableColumn<Probleme, CheckBox> selectCol;

    

    @FXML
    private TableColumn<Probleme, Level> Niveau;

   

    @FXML
    private TableView<Probleme> tableview;


    @FXML
    private TableColumn<Probleme, String> Prblm;
    
    ObservableList<Probleme> data_table;
    
    
    
    
    private void initTable() {
        initcol();

    }

    private void initcol() {
        Prblm.setCellValueFactory(new PropertyValueFactory<Probleme, String>("Titre"));
        Niveau.setCellValueFactory(new PropertyValueFactory<Probleme, Level>("Level"));
        selectCol.setCellValueFactory(new PropertyValueFactory<Probleme, CheckBox>("select"));
      
        tableview.setItems(loadData());
    }

    private ObservableList<Probleme> loadData() {
         data_table = FXCollections.observableArrayList();
        ResultSet Rs = daoP.all();
        try {
            while (Rs.next()) {
                data_table.add(new Probleme(Rs.getString(1), Rs.getString(2), Rs.getString(4), Level.valueOf(Rs.getString(3)), Rs.getString(5)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeUsersController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return data_table;

    }
    
    
   
 
    //==================================================
    @FXML
    void AjoutCompetition(ActionEvent event) {
        //date
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date DD = Date.from(Date_debut.getValue().atStartOfDay(defaultZoneId).toInstant());
        System.out.println(DD);
        Date DF = Date.from(Date_fin.getValue().atStartOfDay(defaultZoneId).toInstant());
        if (DD.compareTo(DF) > 0) {
            //DD kbira men DF
            Al.AlerErreur("erreur dans la date", event);
        }
        Competition C = new Competition(Level.EASY, true, Titre.getText(), DD, DF);
        if (daoC.create(C)) {
            AjouterPrblInCompetition(C.getIdCompetition());
            //System.out.println("competition ajouter");
            Al.AllGood("competition ajouter", event);
        } else {
            Al.AlerErreur("erreur", event);
        }

    }

    void AjouterPrblInCompetition(String IdC) {
        Vector<String> PrblmSelected= new Vector<String>();
        //System.out.println("La taille " +data_table.size());
        for(int i=0 ; i< data_table.size() ; i++){
            
            if(tableview.getItems().get(i).getSelect().isSelected()){
                //System.out.println(data_table.get(i).getId_Probleme());
                PrblmSelected.add(tableview.getItems().get(i).getId_Probleme());
            }
        }
        for(int i=0 ; i< PrblmSelected.size() ; i++){
            daoC.AddProbleme(IdC, PrblmSelected.elementAt(i));     
        }
        //System.out.println("Done");
    }
    @FXML
    void Annuler(ActionEvent event) {
        Al.Annuler("est ce que vous etes sur de vouloir annuler?", event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         initTable();
        loadData();
    }

}
