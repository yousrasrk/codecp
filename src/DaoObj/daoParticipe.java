package DaoObj;

import static Admin.Controller.Layout_adminController.db;

import Database.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amal
 */
public class daoParticipe {
    
    
    Database db;
       public daoParticipe(Database db){
    this.db = db;
}
 
    public static void  UpdateScore(String idUser, String IdComp , int add ,Database db){
        PreparedStatement PS=null;
         ResultSet Rs=null;
         int score = 0;
         try {
                PS = db.con.prepareStatement("select Score from codecp.participe where Id_User=? and Id_Competition=?;");
                PS.setObject(1,idUser); 
                PS.setObject(2,IdComp); 
                Rs= PS.executeQuery();
                Rs.next();
                score = Rs.getInt(1);
                System.out.println("Score is " + score);
            }catch (SQLException ex) {
                System.out.println("error find");
            }
           score+= add;  
           System.out.println("Score is " + score);
        try {
            PS = db.con.prepareStatement("UPDATE codecp.participe SET Score = ? where Id_User=? and Id_Competition=?;");
            
            PS.setObject(1,score); 
            PS.setObject(2,idUser); 
            PS.setObject(3,IdComp); 
            db.dmlQuery(PS);
        }catch (SQLException ex) {
            System.out.println("error update score");
        }
        }
           
        
          //si un user participe dans une competition
    public static boolean existUser(String user, String competition) {
        Database db1 = new Database();
        String req;
        int tot = 0;
        req = " select count(Id_User) from codecp.participe where Id_User = '" + user + "' and Id_Competition ='" + competition + "';";
        Statement St;
        ResultSet Rs = null;
        try {
            St = db1.con.createStatement();
            Rs = St.executeQuery(req);

            while (Rs.next()) {
                tot = Integer.parseInt(Rs.getString(1));
                // System.out.println(tot);
            }
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        //true eda mparticiper 
        System.out.println("tot is " + tot);
        if (tot == 1) {
            return true;
        } else {
            return false;
        }
    }

    
            //lister
    public ResultSet all(String IdC){
       // System.out.println("the id is " + IdC);
           ResultSet Rs =null ;
        String req;
            req = "select * from codecp.participe where Id_Competition = '"+IdC +"' order by Score DESC;";
            
            Statement St;
             try {
                 St=db.con.createStatement();
                 Rs= St.executeQuery(req);
//                 while(Rs.next()){
//                     System.out.println(Rs.getString(1));
//                 }

        
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
      return Rs;
    
          
      
      
    }
        
        
       
        
        
        
    
}
