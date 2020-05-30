/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import Admin.Controller.Layout_adminController;
import DaoObj.daoProbleme;
import Obj.Probleme;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khalf
 */
public class Listeproblemes implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Probleme> tableView;
    @FXML
    private TableColumn<Probleme, String> Titre;
    @FXML
    private TableColumn<Probleme, Enumeration.Level> niveau;
    public static String id;
    
    daoProbleme daoP = new daoProbleme(Layout_adminController.db);

    /**
     * Initializes the controller class.
     */
     private void initTable() {
        initcol();
    }
     private void initcol() {
        Titre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
        niveau.setCellValueFactory(new PropertyValueFactory<>("Level"));
        tableView.setItems(loadData());
    }
    
    
    private ObservableList<Probleme> loadData() {
        ObservableList<Probleme> data_table = FXCollections.observableArrayList();
        ResultSet Rs = daoP.all();
        try {
            while (Rs.next()) {
               data_table.add(new Probleme(Rs.getString(1), Rs.getString(2), Rs.getString(4),Enumeration.Level.valueOf(Rs.getString(3)),Rs.getString(5)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Listeproblemes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data_table;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        loadData();
        tableView.setRowFactory(tv -> {
            TableRow<Probleme> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    //load the dubmittion
                    Probleme P = tableView.getSelectionModel().getSelectedItem();
                   
                    try {
                        Parent root = null;
                        ShowProblemeController.IdPro = P.getId_Probleme();
                        ShowProblemeController.IdUser = layoutController.Id;
                        root = FXMLLoader.load(getClass().getResource("/User/fxml/showProbleme.fxml"));
                        Scene scene = new Scene(root);
                        
                        
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(Listesubmissions.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row;
});
    }    
//   @FXML
//    private void showProbleme(ActionEvent event) {
//        Parent root = null;
//            try {
//                Probleme P = tableView.getSelectionModel().getSelectedItem();
//                id = P.getId_Probleme();
//                 root = FXMLLoader.load(getClass().getResource("/User/fxml/showProbleme.fxml"));
//            } catch (IOException ex) {
//                System.out.println("Bouton show : probleme Load()");
//                Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        
//            
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setScene(scene);
//        stage.show();
//    }

    
    
}
