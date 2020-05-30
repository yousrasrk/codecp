package Obj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.Date;
import DaoObj.*;
import Enumeration.Level;
import java.util.Vector;

import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Amal
 */
public class Competition {
    String Id_Competition;
    Level Level_Comp;
    boolean Statut;
    Date Date_debut;
    Date Date_fin;
    String Titre;
    
    public Competition(Level Level_Comp, boolean Statut, String titre , Date Date_debut ,Date Date_fin ) {
         Integer id=0;
        
        try {
            id=DaoCompetition.getCount();
        } catch (SQLException ex) {
            System.out.println("erreur cst competition");
        }
       
        this.Id_Competition = "C"+id.toString();
        this.Level_Comp = Level_Comp;
        this.Statut = Statut;
        this.Date_debut = Date_debut;
        this.Date_fin = Date_fin;
        this.Titre = titre;
       
    }

    public Competition(Level valueOf, boolean aBoolean, String string, String string0, java.sql.Date date, java.sql.Date date0) {
        this.Level_Comp = valueOf;
        this.Statut= aBoolean;
        this.Id_Competition = string;
        this.Titre= string0;
        Date_debut= date;
        Date_fin= date0;
        }
    

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getTitre() {
        return Titre;
    }
    
  
    public String getIdCompetition() {
        return Id_Competition;
    }

    public Level getLevelComp() {
        return Level_Comp;
    }

    public boolean getStatut() {
        return Statut;
    }

    public Date getDatedebut() {
        return Date_debut;
    }

    public Date getDatefin() {
        return Date_fin;
    }

    public void setLevelComp(Level Level_Comp) {
        this.Level_Comp = Level_Comp;
    }

    public void setStatut(boolean Statut) {
        this.Statut = Statut;
    }

    public void setDatedebut(Date Date_debut) {
        this.Date_debut = Date_debut;
    }

    public void setDatefin(Date Date_fin) {
        this.Date_fin = Date_fin;
    }
}
