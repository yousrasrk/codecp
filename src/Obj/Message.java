package Obj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Date;
/**
 *
 * @author Amal
 */
public abstract class Message {
    String Contenu;
    Date date;
    boolean Lu;
    String Objet;
    
   
    
    public Message(String cont, String obj){
       date = new Date();
       Contenu = cont;
       Objet = obj;
       Lu= false;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLu(boolean Lu) {
        this.Lu = Lu;
    }

    public void setObjet(String Objet) {
        this.Objet = Objet;
    }

   
    public String getContenu() {
        return Contenu;
    }

    public Date getDate() {
        return date;
    }

    public boolean getLu() {
        return Lu;
    }

    public String getObjet() {
        return Objet;
    }

}
