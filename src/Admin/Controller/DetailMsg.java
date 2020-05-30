/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import User.Controller.*;
import DaoObj.daoMessagePerso;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 *
 * @author Amal
 */
public class DetailMsg implements Initializable {

    @FXML
    private Label Objectif;

    @FXML
    private Text Message;

    @FXML
    private Label Emeteur;

    @FXML
    private Label Date;
    private daoMessagePerso daoM = new daoMessagePerso(layoutController.db);
    public static String Id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // System.out.println(Messages_AdminController.id);
        ResultSet Msg = daoM.find(Id);
        daoM.updateLu(Id);
        try {
           
                Message.setText(Msg.getString(5));
                Objectif.setText(Msg.getString(8));
                Date.setText(Msg.getDate(6).toString());
                Emeteur.setText(Msg.getString(1));
            

        } catch (SQLException ex) {
            Logger.getLogger(DetailMsg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
