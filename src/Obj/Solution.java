package Obj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import DaoObj.*;
/**
 *
 * @author Amal
 */
public class Solution {
    String Id_Solution;
    String Id_Probleme;
    String path;
    String Language;
    
    public Solution(String Id_Probleme,  String Lang , String Solexp){
        Integer id = 0;
        try {
            id=daoSolution.getCount();
        } catch (SQLException ex) {
            Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Id_Solution = "S"+id.toString();
        this.Id_Probleme= Id_Probleme;
        this.path = "Solutions/"+this.Id_Solution+""+ Solexp;
        this.Language = Lang;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }
   

    public String getId_Solution() {
        return Id_Solution;
    }

    public String getPath() {
        return path;
    }

    public String getLanguage() {
        return Language;
    }

    public String getId_Probleme() {
        return Id_Probleme;
    }

    public void setId_Probleme(String Id_Probleme) {
        this.Id_Probleme = Id_Probleme;
    }
    
}
