/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import DaoObj.daoSubmit_Comp;
import Obj.Submit_In_Comp;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Huawei
 */
public class ListeSubCompController implements Initializable {

   @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Submit_In_Comp> tableView;

    @FXML
    private TableColumn<Submit_In_Comp,String> Username;

    @FXML
    private TableColumn<Submit_In_Comp, String> Competition;

    @FXML
    private TableColumn<Submit_In_Comp, String> Probleme;

    @FXML
    private TableColumn<Submit_In_Comp, String> Language;

    @FXML
    private TableColumn<Submit_In_Comp, String> Resultat;

    @FXML
    private TableColumn<Submit_In_Comp, Date> Date;
     public static String IdUser;
      public static String IdComp;
 private daoSubmit_Comp daoComp = new daoSubmit_Comp(layoutController.db);
     
     private void initTable() {
        initcol();System.out.println("hoola1");
    }
     private void initcol() {
        Username.setCellValueFactory(new PropertyValueFactory<Submit_In_Comp,String>("Username"));
       Competition.setCellValueFactory(new PropertyValueFactory<Submit_In_Comp,String>("Id_Competition"));
       Probleme.setCellValueFactory(new PropertyValueFactory<Submit_In_Comp,String>("Id_Probleme"));
       Language.setCellValueFactory(new PropertyValueFactory<Submit_In_Comp,String>("Language"));
       Resultat.setCellValueFactory(new PropertyValueFactory<Submit_In_Comp,String>("Verdict"));
       Date.setCellValueFactory(new PropertyValueFactory<Submit_In_Comp,Date>("Date"));
        tableView.setItems(loadData());
        
        System.out.println("hoola2");
    }
    
    
    private ObservableList<Submit_In_Comp> loadData() {
        ObservableList<Submit_In_Comp> data_table = FXCollections.observableArrayList();
        ResultSet Rs =daoComp.AfficherUser(IdUser , IdComp);
         
          System.out.println("hoola3");
         //  data_table.add(new Submit_In_Comp("aa","bb","cc",null,"dd","ee")); 
        try {
            while (Rs.next()) {
                  System.out.println("hoola4");
               data_table.add(new Submit_In_Comp(Rs.getString(3),Rs.getString(1),Rs.getString(2),Rs.getDate(4),Rs.getString(5),Rs.getString(6)));
              
        
                 System.out.println("hoola5");
         
                
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeSubCompController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data_table;
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         System.out.println("hoolaa");
      initTable();
       loadData();
    }    
    
}
