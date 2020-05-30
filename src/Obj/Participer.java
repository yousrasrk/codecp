/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obj;
import DaoObj.daoParticipe;
import java.sql.SQLException;
import java.util.Date;
/**
 *
 * @author Amal
 */
public class Participer {
   String Id_User;
    Integer Score;
    String Id_Competition;
    
    public Participer(String Id_User, String Id_Competition,int Score)
    {
        this.Id_User=Id_User;
        this.Score=Score;
        this.Id_Competition=Id_Competition;
        
        
    }

    public void setId_User(String Id_User) {
        this.Id_User = Id_User;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public void setId_Competition( String Id_Competition) {
        this.Id_Competition = Id_Competition;
    }

    public String getId_User() {
        return Id_User;
    }

    public int getScore() {
        return Score;
    }

    public String getId_Competition() {
        return Id_Competition;
    } 
}
