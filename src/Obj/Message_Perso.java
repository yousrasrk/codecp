package Obj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Obj.Message;
import java.sql.SQLException;
import java.util.logging.Level;
import DaoObj.*;
import java.util.logging.Logger;
/**
 *
 * @author Amal
 */
public class Message_Perso extends Message {
    String Id_Msg;
    String Username_rece;
    String Username_eme;

    public Message_Perso(String Id_Msg, String Username_rece, String Username_eme, String cont, String obj , Boolean Lu) {
        super(cont, obj);
        this.Id_Msg = Id_Msg;
        this.Username_rece = Username_rece;
        this.Username_eme = Username_eme;
        this.Lu = Lu;
    }
    
    public boolean isLu() {
        return Lu;
    }
    
    public Message_Perso(String emet, String recep, String cont, String obj){
        super(cont,obj);
        Integer id = 0;
        try {
            id = daoMessagePerso.getCount();
        } catch (SQLException ex) {
            Logger.getLogger(Message_Perso.class.getName()).log(Level.SEVERE, null, ex);
        }
        Id_Msg = "M"+(id++).toString();
        Username_rece = recep;
        Username_eme = emet;
    }

    public void setId_Msg(String Id_Msg) {
        this.Id_Msg = Id_Msg;
    }

    public void setUsername_rece(String Username_rece) {
        this.Username_rece = Username_rece;
    }

    public void setUsername_eme(String Username_eme) {
        this.Username_eme = Username_eme;
    }

    public String getId_Msg() {
        return Id_Msg;
    }

    public String getUsername_rece() {
        return Username_rece;
    }

    public String getUsername_eme() {
        return Username_eme;
    }
    
    
}
