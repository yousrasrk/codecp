package Obj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import Obj.Message;
import java.sql.SQLException;
import DaoObj.*;


/**
 *
 * @author Amal
 */
public class clarification extends Message{
    String Id_Msg;
    String Username;
    String Adm_Username;
    String Id_Probleme;
    
    public clarification(String User, String adm, String cont, String obj,String Id_prob){
        super(cont,obj);
        Integer id=0;
        try {
            id = daoClarification.getCount();
        } catch (SQLException ex) {
            System.out.println("error constructor clarification");
        }
        Id_Msg = "Cl"+(id++).toString();
        Username = User;
        Adm_Username = adm;
        Id_Probleme = Id_prob;
    }

    public void setId_Probleme(String Id_Probleme) {
        this.Id_Probleme = Id_Probleme;
    }

    public String getId_Probleme() {
        return Id_Probleme;
    }

     public String getId_Msg() {
        return Id_Msg;
    }
    public String getUsername() {
        return Username;
    }

    public String getAdm_Username() {
        return Adm_Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setAdm_Username(String Adm_Username) {
        this.Adm_Username = Adm_Username;
    }
}
