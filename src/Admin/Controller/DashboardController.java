/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin.Controller;

import DaoObj.daoProbleme;
import DaoObj.daoSubmit;
import DaoObj.daoUser;
import Obj.Submit;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Huawei
 */
public class DashboardController implements Initializable {

    @FXML
    private Label NbreUsers;

    @FXML
    private Label name4;

    @FXML
    private Label NbreProb;

    @FXML
    private Label name3;

    @FXML
    private Label name2;

    @FXML
    private Label name1;
    @FXML
    private Label SubUser1;
    @FXML
    private Label SubUser2;
    @FXML
    private Label SubUser3;
    @FXML
    private Label SubUser4;
    daoUser daoU = new daoUser(Layout_adminController.db);
    daoProbleme daoP = new daoProbleme(Layout_adminController.db);

    public void TheLast() throws SQLException {
        Integer I;
        ResultSet Rs = daoU.LastUsers();
        Rs.next();
        name1.setText(Rs.getString(2));
        I = daoSubmit.getCountByUser(Rs.getString(1));
        SubUser1.setText(I.toString());
        Rs.next();
        name2.setText(Rs.getString(2));
        I = daoSubmit.getCountByUser(Rs.getString(1));
        SubUser2.setText(I.toString());
        Rs.next();
        name3.setText(Rs.getString(2));
        I = daoSubmit.getCountByUser(Rs.getString(1));
        SubUser3.setText(I.toString());
        Rs.next();
        name4.setText(Rs.getString(2));
        I = daoSubmit.getCountByUser(Rs.getString(1));
        SubUser4.setText(I.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            NbreUsers.setText(daoUser.getCount().toString());
//            NbreProb.setText(daoProbleme.getCount().toString());
            initTable();
            loadData();
            TheLast();

            //NbreUsersActive.setText(null);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Tablee=====================
    private daoSubmit daoM = new daoSubmit(Layout_adminController.db);

    @FXML
    private TableView<Submit> tableView;

    @FXML
    private TableColumn<Submit, String> Username;
    @FXML
    private TableColumn<Submit, String> Probleme;
    @FXML
    private TableColumn<Submit, String> Resultat;
    @FXML
    private TableColumn<Submit, Date> Date;

    private void initTable() {
        //System.out.println("table");
        initcol();
        try {
            loadGraph();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initcol() {
        // System.out.println("col");
        Username.setCellValueFactory(new PropertyValueFactory<Submit, String>("Username"));
        Probleme.setCellValueFactory(new PropertyValueFactory<Submit, String>("Id_Probleme"));
        // Vu.setCellValueFactory(new PropertyValueFactory<Message_Perso,String>("Lu"));
        Resultat.setCellValueFactory(new PropertyValueFactory<Submit, String>("Verdict"));
        Date.setCellValueFactory(new PropertyValueFactory<Submit, Date>("Date"));
        tableView.getItems().addAll(loadData());
    }

    private ObservableList<Submit> loadData() {
        ObservableList<Submit> data_table = FXCollections.observableArrayList();
        ResultSet Rs = daoM.all();
        System.out.println("hii");
        try {
            while (Rs.next()) {
                data_table.add(new Submit(Rs.getString(3), Rs.getString(1), Rs.getString(2), Rs.getDate(4), Rs.getString(5), Rs.getString(6), Rs.getString(7)));
                //System.out.println(Rs.getString(3) + " " + Rs.getString(1) + " " + Rs.getString(2) + " " + Rs.getDate(4) + " " + Rs.getString(5) + " " + Rs.getString(6) + " " + Rs.getString(7));

            }
        } catch (SQLException ex) {
            System.out.println("Error");

        }

        return data_table;
    }

    //Graph =============================
    @FXML
    private CategoryAxis XAxis = new CategoryAxis();

    @FXML
    private NumberAxis YAxis = new NumberAxis();
    @FXML
    private BarChart<String, Number> Graph = new BarChart<>(XAxis, YAxis);

    void loadGraph() throws SQLException {
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.getData().add(new XYChart.Data<String, Number>("EASY", daoP.allLevel("EASY")));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("MEDIUM", daoP.allLevel("MEDIUM")));
        dataSeries1.getData().add(new XYChart.Data<String, Number>("HARD", daoP.allLevel("HARD")));
        Graph.getData().add(dataSeries1);
    }

}
