package Obj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.SQLException;
import Enumeration.*;
import DaoObj.*;
import java.util.logging.Logger;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Amal
 */
public class Probleme {

    String Id_Probleme;

    String pathPb;
    String Titre;
    Level Level;
    String pathTest;
    private CheckBox select;
    boolean ifchecked;

    public Probleme(String Id_Probleme, String pathPb, String Titre, Level Level, String pathTest) {
        this.Id_Probleme = Id_Probleme;
        this.pathPb = pathPb;
        this.Titre = Titre;
        this.Level = Level;
        this.pathTest = pathTest;
        this.select = new CheckBox();
    }

//    public Probleme(String Id_Probleme, String pathPb, String Titre, Level Level, String pathTest) {
//        this.Id_Probleme = Id_Probleme;
//        this.pathPb = pathPb;
//        this.Titre = Titre;
//        this.Level = Level;
//        this.pathTest = pathTest;
//    }
    public Probleme(String titre, Level lev, String prbext, String testext) {
        Integer id = 0;
        try {
            id = daoProbleme.getCount();
        } catch (SQLException ex) {
            Logger.getLogger(Probleme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.Id_Probleme = "P" + id.toString();

        this.Level = lev;
        this.Titre = titre;
        this.pathPb = "Problemes/" + this.Id_Probleme + "" + prbext;
        this.pathTest = "Tests/" + this.Id_Probleme + "" + testext;
    }

    public String getId_Probleme() {
        return Id_Probleme;
    }

    public String getPathPb() {
        return pathPb;
    }

    public String getTitre() {
        return Titre;
    }

    public Level getLevel() {
        return Level;
    }

    public String getPathTest() {
        return pathTest;
    }

//    public void setId_Solution(String Id_Solution) {
//        this.Id_Solution = Id_Solution;
//    }
    public void setPathPb(String pathPb) {
        this.pathPb = pathPb;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public void setLevel(Level Level) {
        this.Level = Level;
    }

    public void setPathTest(String pathTest) {
        this.pathTest = pathTest;
    }

    Object getIdProbleme() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }

}
