package Obj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
import java.util.Date;
/**
 *
 * @author Amal
 */
public class Submit_abst {
    String Username;
    String Id_Probleme;
    Date Date;
    String Verdict;
    String Language;
    String path;
    String Id_Submit;
    Submit_abst()
    {
    
    }
    
    Submit_abst(String username , String idProbleme,Date date, String verdict, String language ,String Path)
    {
        Username=username;
        Id_Probleme=idProbleme;
        Date=date;
        Verdict=verdict;
        Language=language;
        path=Path;
         
      
    }
     Submit_abst(String username , String idProbleme,Date date, String verdict, String language )
    {
        Username=username;
        Id_Probleme=idProbleme;
        Date=date;
        Verdict=verdict;
        Language=language;
       
         
      
    }
    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getId_Probleme() {
        return Id_Probleme;
    }

    public void setId_Probleme(String Id_Probleme) {
        this.Id_Probleme = Id_Probleme;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getVerdict() {
        return Verdict;
    }

    public void setVerdict(String Verdict) {
        this.Verdict = Verdict;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId_Submit() {
        return Id_Submit;
    }

    public void setId_Submit(String Id_Submit) {
        this.Id_Submit = Id_Submit;
    }
    
}
