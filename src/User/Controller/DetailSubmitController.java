/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import DaoObj.daoSubmit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Amal
 */
public class DetailSubmitController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextArea Solution;
    public static String id;
    daoSubmit daoS = new daoSubmit(layoutController.db);
    
    private String ShowSubm(String path) throws IOException {
        File file = new File(path);
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        //(new FileReader(file));

        String st;
        String Res = new String();
        while ((st = br.readLine()) != null) {
            //System.out.println(st);
            Res += st + "\n";
        }
        return Res;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             Solution.setEditable(false);
            ResultSet Rs = daoS.find(id);
            Solution.setText(ShowSubm(Rs.getString("path")));
        } catch (SQLException ex) {
            Logger.getLogger(ShowProblemeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ShowProblemeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
