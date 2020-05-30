/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import DaoObj.daoUser;
import Obj.User;
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
public class NewUserController implements Initializable {

daoUser daoU = new daoUser(Layout_adminController.db);
    Alert Al = new Alert();
    static String id;     
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> col_email;

    @FXML
    private TableColumn<User, Date> col_date;
    //private Alert Al = new Alert();
    
     private void initTable() {
        initcol();
    }

     
    private void initcol() {
        col_email.setCellValueFactory(new PropertyValueFactory<User,String>("E_Mail"));
        col_date.setCellValueFactory(new PropertyValueFactory<User,Date>("Date_Inscription"));
       
        tableView.setItems(loadData());
    }

    private ObservableList<User> loadData() {
        ObservableList<User> data_table = FXCollections.observableArrayList();
        ResultSet Rs = daoU.Afficher();
        try {
            while (Rs.next()) {
                data_table.add(new User(Rs.getString(1), Rs.getDate(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeUsersController.class.getName()).log(Level.SEVERE, null, ex);
           
    }
       return data_table;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         initTable();
        loadData();
    }    
    
}
