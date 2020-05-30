/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import DaoObj.daoUser;
import Database.Database;
import Obj.Message_Perso;
import Obj.User;
import User.Controller.Listesubmissions;
import java.io.File;
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
 * @author Huawei
 */
public class ListeUsersController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> col_username;

    @FXML
    private TableColumn<User, String> col_nom;

    @FXML
    private TableColumn<User, String> col_prenom;
    daoUser daoU = new daoUser(Layout_adminController.db);
    Alert Al = new Alert();
    static String id;
// public static Database db;
//
//    public static String Id;

    private void initTable() {
        initcol();
    }

    private void initcol() {
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tableView.setItems(loadData());
    }

    private ObservableList<User> loadData() {
        ObservableList<User> data_table = FXCollections.observableArrayList();
        ResultSet Rs = daoU.all();
        try {
            while (Rs.next()) {
                data_table.add(new User(Rs.getString(2), Rs.getString(7), Rs.getString(8)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data_table;
    }

    @FXML
    void delete(ActionEvent event) {
        String Rep = Al.popupEnregistrer("est ce que vous etes sure de vouloir supprimer ?", event);
        if (Rep == "oui") {
            User user = tableView.getSelectionModel().getSelectedItem();
            String idi = daoU.findId(user.getUsername());
            id = idi;
            tableView.getItems().remove(user);
//       System.out.println(user.getUsername());
//        System.out.println(daoU.findId(user.getUsername()));
            if (daoU.delete(id)) {
                Al.AllGood("All good", event);
                File f = new File("./UserProfile/" + id + ".jpg");           //file to be delete  
                f.delete();
            } else {
                Al.AlerErreur("sorry something went wrong", event);
            }
        }

    }

    @FXML
    void update(ActionEvent event) {

    }

//    @FXML
//    void AfficherDetail(ActionEvent event) throws IOException {
//        User user = tableView.getSelectionModel().getSelectedItem();
//        id = daoU.findId(user.getUsername());
////        //System.out.println(id);
//        Parent root = FXMLLoader.load(getClass().getResource("/Admin/fxml/Profile.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.setResizable(false);
//
//        stage.setScene(scene);
//        stage.show();
//    }
    @FXML
    private void AjouterUser(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Admin/fxml/AjouterUser.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initTable();
        loadData();

        tableView.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    //load the dubmittion
                    User U = tableView.getSelectionModel().getSelectedItem();
                    // DetailSubmitController.id = Ms.getId_Msg()
                    try {
                        Parent root = null;
                        id = daoU.findId(U.getUsername());
                        //System.out.println(id);
                        root = FXMLLoader.load(getClass().getResource("/Admin/fxml/Profile.fxml"));
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

    public static String getId() {
        return id;
    }

    @FXML
    void NonEnrgistrer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Admin/fxml/newUser.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
