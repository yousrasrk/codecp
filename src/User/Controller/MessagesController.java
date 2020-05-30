/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import Admin.Controller.Layout_adminController;
import Admin.Controller.Messages_AdminController;
import DaoObj.daoMessage;
import DaoObj.daoMessagePerso;
import Obj.Message_Perso;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
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
public class MessagesController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    private daoMessagePerso daoM = new daoMessagePerso(Layout_adminController.db);

    @FXML
    private TableView<Message_Perso> tableView;

    @FXML
    private TableColumn<Message_Perso, Date> Date;

    @FXML
    private TableColumn<Message_Perso, String> Objet;
    @FXML
    private TableColumn<Message_Perso, String> Emeteur;
    static String id;
    @FXML
    private Button Supp;

    private void initTable() {
        initcol();
    }

    private void initcol() {

        Emeteur.setCellValueFactory(new PropertyValueFactory<Message_Perso, String>("Username_eme"));

        Date.setCellValueFactory(new PropertyValueFactory<Message_Perso, Date>("date"));
        // Vu.setCellValueFactory(new PropertyValueFactory<Message_Perso,String>("Lu"));
        Objet.setCellValueFactory(new PropertyValueFactory<Message_Perso, String>("Objet"));
        //customiseFactory(Contenu);
        //customiseFactory(Date);
        customiseFactory(Objet);
        customiseFactory(Emeteur);
        Date.setCellFactory(column -> {
            TableCell<Message_Perso, Date> cell = new TableCell<Message_Perso, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty); //This is mandatory

                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty

                        setText(item.toString()); //Put the String data in the cell

                        //We get here all the info of the Person of this row
                        Message_Perso auxPerson = getTableView().getItems().get(getIndex());

                        // Style all persons wich name is "Edgard"
                        if (!auxPerson.getLu()) {
                            //setTextFill(Color.RED); //The text in red
                            setStyle("-fx-background-color: #D3D3D3"); //The background of the cell in yellow
                        } else {
                            //Here I see if the row of this cell is selected or not
                            if (getTableView().getSelectionModel().getSelectedItems().contains(auxPerson)) {
                                //setTextFill(Color.WHITE);
                            } else {
                                //setTextFill(Color.BLACK);
                            }
                        }
                    }
                }
            };

            return cell;
        });
        tableView.getItems().addAll(loadData());
    }

    private ObservableList<Message_Perso> loadData() {
        ObservableList<Message_Perso> data_table = FXCollections.observableArrayList();
        ResultSet Rs = daoM.allRec(layoutController.Id);
        try {

            while (Rs.next()) {
                data_table.add(new Message_Perso(Rs.getString(3), Rs.getString(2), Rs.getString(1), Rs.getString(4), Rs.getString(7), Rs.getBoolean(6)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Messages_AdminController.class.getName()).log(Level.SEVERE, null, ex);

        }

        return data_table;
    }

    @FXML
    private void newmsg(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/User/fxml/sendmessage.fxml"));
        } catch (IOException ex) {
            System.out.println("Bouton new msg : probleme Load()");
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Nouveau message");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void showmsgenvoye(ActionEvent event) {
        //voir les msg envoye
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/User/fxml/MsgRecu.fxml"));
        } catch (IOException ex) {
            System.out.println("Bouton new msg : probleme Load()");
            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Nouveau message");
        stage.setScene(scene);
        stage.show();

    }

//    @FXML
//    private void showmsg(ActionEvent event) {
//        Parent root = null;
//        try {
//            Message_Perso Ms = tableView.getSelectionModel().getSelectedItem();
//            id = Ms.getId_Msg();
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
//    }
    @FXML
    void Delet(ActionEvent event) {
        Message_Perso Ms = tableView.getSelectionModel().getSelectedItem();
        id = Ms.getId_Msg();
        if (daoM.delete(id)) {
            //ALERT MSG SUPPRIMER
            tableView.getItems().remove(Ms);
            System.out.println("Ra2i3");
        } else {
            //error
            System.out.println("Not ra2i3");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        loadData();
        tableView.setRowFactory(tv -> {
            TableRow<Message_Perso> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    //load the dubmittion
                    Message_Perso M = tableView.getSelectionModel().getSelectedItem();
                    // DetailSubmitController.id = M.getId_Msg();
                    try {
                        Parent root = null;
                        DetailMsg.Id = M.getId_Msg();
                        root = FXMLLoader.load(getClass().getResource("/User/fxml/Detail_msg.fxml"));
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

    private void customiseFactory(TableColumn<Message_Perso, String> calltypel) {
        calltypel.setCellFactory(column -> {
            return new TableCell<Message_Perso, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty); //This is mandatory

                    if (item == null || empty) { //If the cell is empty
                        setText(null);
                        setStyle("");
                    } else { //If the cell is not empty

                        setText(item); //Put the String data in the cell

                        //We get here all the info of the Person of this row
                        Message_Perso auxPerson = getTableView().getItems().get(getIndex());

                        // Style all persons wich name is "Edgard"
                        if (!auxPerson.getLu()) {
                           // setTextFill(Color.RED); //The text in red
                            setStyle("-fx-background-color: #D3D3D3"); //The background of the cell in yellow
                        } else {
                            //Here I see if the row of this cell is selected or not
                            if (getTableView().getSelectionModel().getSelectedItems().contains(auxPerson)) {
                                //setTextFill(Color.WHITE);
                            } else {
                                //setTextFill(Color.BLACK);
                            }
                        }
                    }
                }
            };

        });
    }
}
