/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import Admin.Controller.Layout_adminController;
import DaoObj.daoUser;
import Database.Database;
import User.Controller.Profile;
import User.Controller.layoutController;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author khalf
 */
public class LoginController implements Initializable {

    static public Database db;
    static public String id;
    daoUser daoU;
    boolean connect;
    @FXML
    private Button SignIn;

    @FXML
    private TextField Email;

    @FXML
    private PasswordField Password;

    public LoginController() {
        db = new Database();
        daoU = new daoUser(db);
    }

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException, SQLException {
        String E = Email.getText();
        String Ps = Password.getText();
        String Id = daoU.VerifieConnection(E, Ps);
        this.id = Id;
        this.db = db;

        if (Id != null) {
            if (Id.equals("admin")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/fxml/layout_admin.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                //get the stage information
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                // Layout_adminController LA = new Layout_adminController();
                //LA.initData(Id , db);
                Layout_adminController.Id = Id;
                Layout_adminController.db = db;
                window.setScene(scene);
                window.setFullScreen(true);
                window.setResizable(true);
                window.show();
            } else {
                ResultSet U = daoU.find(Id);
                if (U.getString(2).equals("")) {
                    //first connection
                    FirstConnectProfileController.Id = Id;
                    FirstConnectProfileController.db = db;
                    //System.out.println("User");
                    //System.out.println("Hta hna walu " +LoginController.getId());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect/FirstConnectProfile.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    window.setScene(scene);
                    window.setFullScreen(true);
                    window.setResizable(true);
                    window.show();
                } else {
                    layoutController.Id = Id;
                    layoutController.db = db;
                    //System.out.println("User");
                    //System.out.println("Hta hna walu " +LoginController.getId());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/fxml/userLayout.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    window.setScene(scene);
                    window.setFullScreen(true);
                    window.setResizable(true);
                    window.show();
                }

            }
        } else {
            System.out.println("you can't pass");
            connect = false;
        }

    }

    public static String getId() {
        return id;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
//    public void initData(String Id) {
//        System.out.println(Id);
//  }

}
