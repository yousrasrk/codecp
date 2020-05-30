/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

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
public class DetailMsg  implements Initializable{
     @FXML
    private Text Message;

    @FXML
    private Label Obj;

    @FXML
    private Label Date;

    @FXML
    private Label Emetteur;
    private daoMessagePerso daoM = new daoMessagePerso(layoutController.db);
    public static String Id ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("I "+ Id);
        ResultSet Msg = daoM.find(Id);
        daoM.updateLu(Id);
         try {
            // Msg.next();
                Message.setText(Msg.getString(5));
                Obj.setText(Msg.getString(8));
                Date.setText(Msg.getDate(6).toString());
                Emetteur.setText(Msg.getString(1));
         } catch (SQLException ex) {
             Logger.getLogger(DetailMsg.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
    
}
