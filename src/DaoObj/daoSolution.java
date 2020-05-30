package DaoObj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Obj.Solution;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.*;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Amal
 */
public class daoSolution  extends DAO<Solution> {
     Database db ;
    public daoSolution(Database db){
        this.db = db;
    }
    
    @Override
    public ResultSet find(String idP) {
         PreparedStatement PS=null;
         ResultSet Rs=null;
        try {
            PS = db.con.prepareStatement("select * from codecp.solution where Id_Probleme=?;");
            PS.setObject(1,idP); 
            Rs= PS.executeQuery();
            Rs.next();
//            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2));
//            }
        } catch (SQLException ex) {
            System.out.println("error find");
        }
        return Rs;
    }
    
    
     public String findIdSol(String idP)  {
         PreparedStatement PS=null;
         ResultSet Rs=null;
        try {
            PS = db.con.prepareStatement("select Id_Solution from codecp.solution where Id_Probleme=?;");
            PS.setObject(1,idP); 
            Rs= PS.executeQuery();
            Rs.next();
//            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2));
//            }
        } catch (SQLException ex) {
            System.out.println("error find");
        }
         try {
             return Rs.getString(1);
         } catch (SQLException ex) {
             Logger.getLogger(daoSolution.class.getName()).log(Level.SEVERE, null, ex);
         }
         return "Ghalat";
         
    }
    
    
    

    @Override
    public ResultSet all() {
      String req;
            req = "select * from codecp.solution";
            Statement St;
            ResultSet Rs=null;
             try {
                 St=db.con.createStatement();
                 Rs= St.executeQuery(req);
//            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2));
//            }
        
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return Rs;
    }

    @Override
    public boolean create(Solution obj) {
       PreparedStatement PS=null;
        try {
            PS = db.con.prepareStatement("INSERT INTO codecp.solution VALUES (?,?,?,?);");
            PS.setObject(2,obj.getId_Solution()); 
            PS.setObject(3,obj.getPath()); 
            PS.setObject(4,obj.getLanguage()); 
            PS.setObject(1,obj.getId_Probleme()); 
        } catch (SQLException ex) {
            System.out.println("error create");
        }
           
        
        return (db.dmlQuery(PS) == 0) ? false : true;  
    }

    @Override
    public boolean update(Solution obj, String idP) {
       boolean rs = false;
        try {
            PreparedStatement Pst;
            Pst = db.con.prepareStatement("UPDATE codecp.solution set path = ?, Language =?  WHERE Id_Probleme=?;");
            Pst.setString(1, obj.getPath());
            Pst.setString(2, obj.getLanguage());
            Pst.setString(3, obj.getId_Probleme());
            
           if(db.dmlQuery(Pst) == 1){
               rs=true;
           }
        } catch (SQLException ex) {
            Logger.getLogger(daoSolution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
   
       return rs;
    }

    @Override
    public boolean delete(String idP) {
         boolean rs = false;
        String sql = "DELETE FROM codecp.solution WHERE Id_Probleme=?;";
        try {
            PreparedStatement stmt = db.con.prepareStatement(sql);
            stmt.setString(1, idP);
            stmt.executeUpdate();
            rs = true;
        } catch (SQLException ex) {
            Logger.getLogger(daoSolution.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
        
public static int getCount() throws SQLException{
        Database db1 = new Database();
        ResultSet Rs =null ;
        String req;
            req = "select count(*) from codecp.solution;";
            Statement St;
             try {
                 St=db1.con.createStatement();
                 Rs= St.executeQuery(req);
        
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
         Rs.next();
            return (Rs.getInt(1));
        
    }
    
}
