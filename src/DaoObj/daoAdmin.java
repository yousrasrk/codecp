package DaoObj;



import Obj.Admin;
import java.sql.PreparedStatement;
//import obj.*;
import Database.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Amal
 */
public class daoAdmin extends daoUser {
    Database db;
    public daoAdmin(Database db){
         super(db);
    }

    @Override
    public boolean delete(String id) {
        return super.delete(id); //To change body of generated methods, choose Tools | Templates.
    }

   
    public boolean update(Admin p, String id) {
        boolean rs = false;
        super.update(p, id);
        try {
            PreparedStatement Pst;
            Pst = db.con.prepareStatement("UPDATE codecp.admin set  Password =? WHERE Id_User=?;");
            Pst.setString(1, (p).getId_User());
            Pst.setString(2, ((Admin)p).getMdp());
            Pst.setString(3, id);
            
           if(db.dmlQuery(Pst) == 1){
               rs=true;
           }
        } catch (SQLException ex) {
            Logger.getLogger(daoAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
   
       return rs;
                  
    }

    
    public boolean create(Admin p) {
        PreparedStatement Pst = null;
           super.create(p);
        try {   
            Pst = db.con.prepareStatement( "INSERT INTO codecp.admin VALUES(?,?);");
            Pst.setObject(1, (p).getId_User());
            Pst.setObject(3, ((Admin)p).getMdp());
          
        } catch (SQLException ex) {
            Logger.getLogger(daoAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
         return (db.dmlQuery(Pst) == 0) ? false : true;  
    }

    @Override
    public ResultSet all() {
        ResultSet Rs =null ;
        String req;
            req = "select * from codecp.admin;";
            Statement St;
             try {
                 St=db.con.createStatement();
                 Rs= St.executeQuery(req);
//            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString("login")+"---->"+Rs.getString("Password"));
//            }
        
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
      return Rs;

    }

    @Override
    public ResultSet find(String id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }
}
