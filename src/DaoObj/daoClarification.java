package DaoObj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DaoObj.daoMessage;
import Obj.clarification;
import Obj.Message;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Database.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Amal
 */
public class daoClarification extends daoMessage{
    public daoClarification(Database db) {
        super(db);
    }
   

    @Override
    public boolean delete(String id) {
        boolean rs = false;
        String sql = "DELETE FROM codecp.clarification WHERE Id_Msg=?;";
        try {
            PreparedStatement stmt = db.con.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(daoClarification.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }

    @Override
    public boolean update(Message obj, String id) {
        return super.update(obj, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Message M) {
         PreparedStatement Ps = null;
        try {
        
            Ps = db.con.prepareStatement( "INSERT INTO codecp.clarification VALUES(?,?,?,?,?,?,?,?);");
            Ps.setObject(1, ((clarification)M).getUsername());
            Ps.setObject(2, ((clarification)M).getAdm_Username());
            Ps.setObject(3, ((clarification)M).getId_Msg());  
            Ps.setObject(4, M.getContenu()); 
            Ps.setObject(5, M.getDate()); 
            Ps.setObject(6, M.getLu()); 
            Ps.setObject(7, M.getObjet()); 
            Ps.setObject(8, ((clarification)M).getId_Probleme()); 
            
          
        } catch (SQLException ex) {
            Logger.getLogger(daoClarification.class.getName()).log(Level.SEVERE, null, ex);
        }
         return (db.dmlQuery(Ps) == 0) ? false : true;  
    }

    @Override
    public ResultSet all() {
       ResultSet Rs =null ;
        String req;
            req = "select * from codecp.clarification;";
            Statement St;
             try {
                 St=db.con.createStatement();
                 Rs= St.executeQuery(req);
            while(Rs.next()){
                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2)+"---->"+Rs.getString(3));
            }
        
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
      return Rs;
    }

    @Override
    public ResultSet find(String id) {
        ResultSet Rs = null;
        String sql = "Select * FROM codecp.clarification WHERE Id_Msg='"+id+"';";
        Statement St;
            
             try {
                 St=db.con.createStatement();
                 Rs= St.executeQuery(sql);
            while(Rs.next()){
                  System.out.println(Rs.getString(1)+"---->"+Rs.getString(2)+"---->"+Rs.getString(3));
            }
        
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
      return Rs;
    }
    
    public static int getCount() throws SQLException{
        Database db1 = new Database();
        ResultSet Rs = null;
        String req;
        req="select count(*) from codecp.clarification;";
        Statement St;
        try{
            St=db1.con.createStatement();
            Rs=St.executeQuery(req);
        }
         catch(SQLException ex){
                System.out.println("PB dans la requete select");             
         }
        Rs.next();
        return (Rs.getInt(1));
    }
}
