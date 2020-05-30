package DaoObj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DaoObj.daoUser;
import Obj.Submit_In_Comp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Database.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amal
 */
public class daoSubmit_Comp extends DAO<Submit_In_Comp>{
     Database db ;
    public daoSubmit_Comp(Database db){
        this.db = db;
    }

    @Override
    public ResultSet find(String id) {
        ResultSet Rs = null;
        String sql = "Select * FROM codecp.submit_in_comp WHERE Id_Submit="+ id+";";
        Statement St;
             try {
                 St=db.con.createStatement();
                 Rs= St.executeQuery(sql);
            while(Rs.next()){
                  System.out.println(Rs.getString(1)+"---->"+Rs.getString("Username"));
            }
        
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
      return Rs;
    }

    @Override
    public ResultSet all() {
        ResultSet Rs =null ;
        String req;
            req = "select * from codecp.submit_in_comp;";
            Statement St;
             try {
                 St=db.con.createStatement();
                 Rs= St.executeQuery(req);
            while(Rs.next()){
                System.out.println(Rs.getString(1));
            }
        
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
      return Rs;
          
    }
    
    
    
    
  
    

    @Override
    public boolean create(Submit_In_Comp obj) {
        PreparedStatement Pst = null;
        try {   
            Pst = db.con.prepareStatement("INSERT INTO codecp.submit_in_comp VALUES(?,?,?,?,?,?,?,?);");
            //System.out.println((p).getUsername());
            Pst.setObject(1, obj.getUsername());
            Pst.setObject(2, obj.getId_Probleme());
            Pst.setObject(8, obj.getId_Submit());
            Pst.setObject(3, obj.getId_Competition());
            Pst.setObject(4, obj.getDate());
            Pst.setObject(5, obj.getVerdict());
            Pst.setObject(6, obj.getLanguage());
            Pst.setObject(7, obj.getPath()); 
          
        } catch (SQLException ex) {
            Logger.getLogger(daoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
         return (db.dmlQuery(Pst) == 0) ? false : true;  
    }

    @Override
    public boolean update(Submit_In_Comp obj, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public ResultSet AfficherUser(String idUser , String idCompt){
    ResultSet Rs =null ;
            String req;
                req = "select * from codecp.submit_in_comp where Id_User='"+idUser+"' and Id_Competition = '"+idCompt+"';";
                Statement St;
                 try {
                     St=db.con.createStatement();
                     Rs= St.executeQuery(req);
//                while(Rs.next()){
//                    System.out.println(Rs.getString(1));
//                }

            } catch (SQLException ex) {
                System.out.println("PB dans la requete select");
            }
          return Rs;
        
    }
    public ResultSet AfficherProbleme(String idProblm){
        ResultSet Rs =null ;
        String req;
            req = "select * from codecp.submit_in_comp where Id_Probleme="+idProblm+";";
            Statement St;
             try {
                 St=db.con.createStatement();
                 Rs= St.executeQuery(req);
            while(Rs.next()){
                System.out.println(Rs.getString(1));
            }
        
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
      return Rs;
    }
    
    public static int getCount() throws SQLException{
        Database db1 = new Database();
        ResultSet Rs =null ;
        String req;
            req = "select count(*) from codecp.submit_in_comp;";
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
