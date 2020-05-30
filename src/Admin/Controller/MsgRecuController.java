/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import User.Controller.*;
import Admin.Controller.Layout_adminController;
import Admin.Controller.Messages_AdminController;
import DaoObj.daoMessage;
import DaoObj.daoMessagePerso;
import Obj.Message_Perso;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amal
 */
public class MsgRecuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Message_Perso> tableview;
    @FXML
    private TableColumn<Message_Perso, String> Obj;

    @FXML
    private TableColumn<Message_Perso, String> Recepteur;

    @FXML
    private TableColumn<Message_Perso, Date> Date;
    private daoMessagePerso daoM = new daoMessagePerso(Layout_adminController.db);

//    @FXML
//    void ShowDetail(ActionEvent event) {
//        Parent root = null;
//        try {
//            Message_Perso Ms = tableview.getSelectionModel().getSelectedItem();
//            String id = Ms.getId_Msg();
//            root = FXMLLoader.load(getClass().getResource("/User/fxml/Detail_msg.fxml"));
//        } catch (IOException ex) {
//            System.out.println("Bouton show : probleme Load()");
//            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setTitle("Message reçu");
//        stage.setScene(scene);
//        stage.show();
//
//    }
    private void initTable() {
        System.out.println("initTable");
        initcol();
    }

    private void initcol() {

        Recepteur.setCellValueFactory(new PropertyValueFactory<Message_Perso, String>("Username_eme"));
        System.out.println("initcol");
        Date.setCellValueFactory(new PropertyValueFactory<Message_Perso, Date>("date"));
        // Vu.setCellValueFactory(new PropertyValueFactory<Message_Perso,String>("Lu"));
        Obj.setCellValueFactory(new PropertyValueFactory<Message_Perso, String>("Objet"));
        tableview.getItems().addAll(loadData());
    }

    private ObservableList<Message_Perso> loadData() {
        System.out.println("LoadData");
        ObservableList<Message_Perso> data_table = FXCollections.observableArrayList();
        ResultSet Rs = daoM.allEmet("admin");
        try {
            while (Rs.next()) {
                data_table.add(new Message_Perso(Rs.getString(3), Rs.getString(2), Rs.getString(1), Rs.getString(4), Rs.getString(7) , Rs.getBoolean(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Messages_AdminController.class.getName()).log(Level.SEVERE, null, ex);

        }

        return data_table;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        loadData();
        tableview.setRowFactory(tv -> {
            TableRow<Message_Perso> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    //load the dubmittion
                    Message_Perso Ms = tableview.getSelectionModel().getSelectedItem();
                    // DetailSubmitController.id = Ms.getId_Msg()
                    try {
                        Parent root = null;
                        //String id = Ms.getId_Msg();
                         DetailMsg.Id = Ms.getId_Msg();
                        root = FXMLLoader.load(getClass().getResource("/Admin/fxml/Detail_msg.fxml"));
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

}
