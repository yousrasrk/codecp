package Admin.Controller;

import Connect.LoginController;
import DaoObj.daoMessage;
import DaoObj.daoMessagePerso;
import DaoObj.daoUser;
import DaoObj.daoMessagePerso;
import Database.Database;
import Obj.Message_Perso;
import User.Controller.Listesubmissions;
import User.Controller.Profile;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author Huawei
 */
public class Messages_AdminController implements Initializable {

    @FXML
    private AnchorPane rootPane;

//    public static  Database db;
//
//    public static String Id;
    private daoMessagePerso daoM = new daoMessagePerso(Layout_adminController.db);
    @FXML
    private TextArea Msg;
    @FXML
    private TextField Recep;
    @FXML
    private TextField Obj;
    @FXML
    private Button Annuler;
    @FXML
    private Button Enregistrer;
    public static String id;

    //yousra
    @FXML
    private TableView<Message_Perso> tableView;

    @FXML
    private TableColumn<Message_Perso, String> Contenu;

    @FXML
    private TableColumn<Message_Perso, Date> Date;

//    @FXML
//    private TableColumn<Message_Perso, String> Vu;///////////khaas tkon bolllean makiyakhdhach
    @FXML
    private TableColumn<Message_Perso, String> Objet;
    @FXML
    private TableColumn<Message_Perso, String> Emeteur;

    private daoMessagePerso DaoM = new daoMessagePerso(Layout_adminController.db);

    private void initTable() {
        initcol();

    }

    private void initcol() {

        Emeteur.setCellValueFactory(new PropertyValueFactory<Message_Perso, String>("Username_eme"));
        Contenu.setCellValueFactory(new PropertyValueFactory<Message_Perso, String>("Contenu"));
        Date.setCellValueFactory(new PropertyValueFactory<Message_Perso, Date>("date"));
        // Vu.setCellValueFactory(new PropertyValueFactory<Message_Perso,String>("Lu"));
        Objet.setCellValueFactory(new PropertyValueFactory<Message_Perso, String>("Objet"));
        customiseFactory(Contenu);
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
        ResultSet Rs = daoM.allRec("admin");
        try {

            while (Rs.next()) {
                data_table.add(new Message_Perso(Rs.getString(3), Rs.getString(1), Rs.getString(2), Rs.getString(4), Rs.getString(7), Rs.getBoolean(6)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Messages_AdminController.class.getName()).log(Level.SEVERE, null, ex);

        }

        return data_table;
    }

    //yousra
    @FXML
    public void afficherClarification(ActionEvent event) {

    }

    @FXML
    void EnvoyerMsg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Admin/fxml/Envoi_message.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
//     @FXML
//    
//    void EnvoyerDef(ActionEvent event){
//         if(Recep.getText() != null){
//             if(createMsg()){
//                 //mesage sent
//                 
//             }else{
//                 //msg Non envoye erreur
//             }
//         } 
//    }
//    private boolean createMsg(){
//        daoUser U = new daoUser(Layout_adminController.db);
//        String Id_Recep = U.findId(Recep.getText());
//        if(Id_Recep.equals("")){
//            //username incorrect
//            return false;
//        }
//        Message_Perso Ms = new Message_Perso(Layout_adminController.Id,Id_Recep , Msg.getText(), Obj.getText());
//        //System.out.println(Layout_adminController.Id+" "+Recep.getText()+" "+Msg.getText()+" "+Obj.getText());
//        if(DaoM.create(Ms)){
//             //System.out.println("Msg envoyer");
//             return true;
//         }
//             //System.out.println("Msg Non envoye");
//             return false;
//   }

    @FXML
    void showmsgenvoye(ActionEvent event) {
        //voir les msg envoye
        System.out.println("herer");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Admin/fxml/MsgRecu.fxml"));
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
    void delete(ActionEvent event) {

        Message_Perso Ms = tableView.getSelectionModel().getSelectedItem();
        //System.out.println(Comp.getIdCompetition());
        //ResultSet idi = daoM.find(Ms.getId_Msg());
        //idi.next();
        this.id = Ms.getId_Msg();
        tableView.getItems().remove(Ms);
        if (DaoM.delete(id)) {
            System.out.println("msg supprimer");
        } else {
            System.out.println("msg non supprimer");
        }
    }

//    @FXML
//    void Detail(ActionEvent event) throws IOException {
//        Message_Perso Ms = tableView.getSelectionModel().getSelectedItem();
//        //System.out.println(Comp.getIdCompetition());
//        //ResultSet idi = daoM.find(Ms.getId_Msg());
//        //idi.next();
//        this.id = Ms.getId_Msg();
//        //System.out.println("hna"+id);
////        //System.out.println(id);
//        Parent root = FXMLLoader.load(getClass().getResource("/Admin/fxml/Detail_msg.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.setResizable(false);
//
//        stage.setScene(scene);
//        stage.show();
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        loadData();
        customiseFactory(Contenu);
        // TODO
        tableView.setRowFactory(tv -> {
            TableRow<Message_Perso> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    //load the dubmittion
                    Message_Perso Ms = tableView.getSelectionModel().getSelectedItem();
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
