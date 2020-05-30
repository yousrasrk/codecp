/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import Admin.Controller.Alert;
import Admin.Controller.Layout_adminController;
import static Connect.LoginController.db;
import DaoObj.daoUser;
import Database.Database;
import Obj.User;
import static User.Controller.ModifierProfileController.Id;
import User.Controller.layoutController;
import com.sun.media.sound.AlawCodec;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

/**
 *
 * @author Amal
 */
public class FirstConnectProfileController implements Initializable {

    @FXML
    private TextField Prenom;

    @FXML
    private Button AjoutPic;

    @FXML
    private TextField Usename;

    @FXML
    private MenuButton LanguageFv;

    @FXML
    private TextField Tel;

    @FXML
    private TextField Nom;
    @FXML
    private MenuItem Python;
    @FXML
    private MenuItem Cplusplus;
    @FXML
    private MenuItem Java;
    private File ProPic, dest;

    public static String Id;
    public static Database db;
    private daoUser daoU = new daoUser(db);
    Alert al = new Alert();

    @FXML
    void AnnulerModification(ActionEvent event) {

    }

    @FXML
    void EnregistrerModification(ActionEvent event) throws IOException {
        if (daoU.ifexist(Usename.getText())) {
            //exist
            al.AlerErreur("The username exist alredy", event);

        } else {
            //update
            User U = new User(Usename.getText(), Tel.getText(), dest.getPath(), Nom.getText(), Prenom.getText(), "C++");
            if (daoU.updateFirst(U, Id)) {
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
            } else {
                System.out.println("Eror");
            }
        }
    }

    private File OpenChooser() {
        JFileChooser fs = new JFileChooser(new File("c:\\"));
        fs.setDialogTitle("Save a File");
        int result = fs.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File source = fs.getSelectedFile();
            return source;
        }
        return null;
    }

    @FXML
    public void AjoutPic(ActionEvent event) {
        System.out.println("hnaya Id"+ Id);
        //System.out.println(Id);
        ProPic = OpenChooser();
        dest = new File("./UserProfile/" + Id + "" + ".jpg");
        try {
            Files.copy(ProPic.toPath(), dest.toPath(), REPLACE_EXISTING);
        } catch (IOException ex) {
            System.out.println("error upload");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
