/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import Admin.Controller.Layout_adminController;
import DaoObj.DaoCompetition;
import Enumeration.Level;
import Obj.Competition;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author khalf
 */
public class Listecompetitions implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Competition> tableView;
    @FXML
    private TableColumn<Competition, String> Titre;
    @FXML
    private TableColumn<Competition, Level> Niveau;
    private DaoCompetition daoC = new DaoCompetition(Layout_adminController.db);
    static String id;

    /**
     * Initializes the controller class.
     */
    private void initcol() {
        Titre.setCellValueFactory(new PropertyValueFactory<Competition, String>("Titre"));
        Niveau.setCellValueFactory(new PropertyValueFactory<Competition, Level>("Level_Comp"));
        tableView.setItems(loadData());
    }

    private void initTable() {
        initcol();
    }

    private ObservableList<Competition> loadData() {
        ObservableList<Competition> data_table = FXCollections.observableArrayList();
        ResultSet Rs = daoC.all();
        try {
            while (Rs.next()) {
                //System.out.println( Rs.getString(2));
                data_table.add(new Competition(Level.valueOf(Rs.getString(2)), Rs.getBoolean(3), Rs.getString(1), Rs.getString(6), Rs.getDate(4), Rs.getDate(5)));
            }
        } catch (SQLException ex) {
            System.out.println("Muchkil f load data");
        }
        return data_table;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        loadData();
        tableView.setRowFactory(tv -> {
            TableRow<Competition> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    //load the dubmittion
                    Competition C = tableView.getSelectionModel().getSelectedItem();
                    System.out.println("id d user in liste compt "+ id);
                    showController.IdUser=id;
                    showController.IdComp= C.getIdCompetition();
                    showController.db= Layout_adminController.db;
                    try {
                        Parent root = null;
                        root = FXMLLoader.load(getClass().getResource("/User/fxml/showCompetition.fxml"));
                        Scene scene = new Scene(root);
                        
                        
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println("error liste competition");
                    }
                }
            });
            return row;
});
    }

//    @FXML
//    private void showCompetition(ActionEvent event) {

//        Parent root = null;
//        try {
//            Competition C = tableView.getSelectionModel().getSelectedItem();
//            showController.IdUser="admin";
//            showController.IdComp= C.getIdCompetition();
//            showController.db= Layout_adminController.db;
//            //id = C.getIdCompetition();
//            //System.out.println(id);
//            root = FXMLLoader.load(getClass().getResource("/User/fxml/showCompetition.fxml"));
//        } catch (IOException ex) {
//            System.out.println("Bouton show : probleme Load()");
//
//        }
//
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setScene(scene);
//        stage.show();
    //}
    
     
}
