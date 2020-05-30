/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.Controller;

import DaoObj.daoSolution;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author khalf
 */
public class SolutionController implements Initializable {

    @FXML
    private TextArea  Solution;
    public static String Id;
    daoSolution daoS = new daoSolution(layoutController.db);

    /**
     * Initializes the controller class.
     */
    private String ShowSol(String path) throws IOException {
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
            ResultSet Rs = daoS.find(Id);
            Solution.setText(ShowSol(Rs.getString("path")));
            //Solution.setDisable(true);
            Solution.setEditable(false);
        } catch (SQLException ex) {
            Logger.getLogger(ShowProblemeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ShowProblemeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
