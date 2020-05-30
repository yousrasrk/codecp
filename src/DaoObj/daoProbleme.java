package DaoObj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Obj.Probleme;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Database.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author Amal
 */
public class daoProbleme extends DAO<Probleme>{
      Database db ;
    public daoProbleme(Database db){
        this.db = db;
    }

    @Override
    public ResultSet find(String id) {
       PreparedStatement PS=null;
         ResultSet Rs=null;
        try {
            PS = db.con.prepareStatement("select * from codecp.probleme where Id_Probleme=?;");
            PS.setObject(1,id); 
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

    @Override
    public ResultSet all() {
   String req;
            req = "select * from codecp.probleme";
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

    
    
    
    
    
    
    public Integer allLevel(String Level) throws SQLException {
   String req;
            req = "select count(*) from codecp.probleme where Level ='"+ Level+"';";
            Statement St;
            ResultSet Rs=null;
             try {
                 St=db.con.createStatement();
                 Rs= St.executeQuery(req);
                 Rs.next();
//            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2));
//            }
        
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return  Rs.getInt(1);
    }
    
    
    
    
    
    
    
    
    @Override
    public boolean create(Probleme obj) {
   PreparedStatement PS=null;
        try {
            PS = db.con.prepareStatement("INSERT INTO codecp.probleme VALUES (?,?,?,?,?);");
            //PS.setObject(1,obj.getId_Solution()); 
            PS.setObject(1,obj.getId_Probleme()); 
            PS.setObject(2,obj.getPathPb()); 
            PS.setObject(3,obj.getLevel().toString()); 
            PS.setObject(4,obj.getTitre()); 
            PS.setObject(5,obj.getPathTest()); 
        } catch (SQLException ex) {
            System.out.println("error create");
        }
           
        
        return (db.dmlQuery(PS) == 0) ? false : true;  
    }

    @Override
    public boolean update(Probleme obj, String id) {
       boolean rs = false;
        try {
            PreparedStatement Pst;
            Pst = db.con.prepareStatement("UPDATE codecp.probleme set  pathPB =?, Level=?, Titre=?, pathTest=?  WHERE Id_Probleme=?;");
           // Pst.setObject(1, obj.getId_Solution());
            Pst.setObject(2, obj.getPathPb());
            Pst.setObject(3, obj.getLevel());
            Pst.setObject(4, obj.getTitre());
            Pst.setObject(5, obj.getPathTest());
            Pst.setObject(6, id);
            
           if(db.dmlQuery(Pst) == 1){
               rs=true;
           }
        } catch (SQLException ex) {
            Logger.getLogger(daoProbleme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
   
       return rs;
    }

    @Override
    public boolean delete(String id) {
        boolean rs = false;
        String sql = "DELETE FROM codecp.probleme WHERE Id_Probleme=?;";
        try {
            PreparedStatement stmt = db.con.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
            rs = true;
        } catch (SQLException ex) {
            Logger.getLogger(daoProbleme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        daoSolution daoS = new daoSolution(db);
        daoS.delete(id);
        return rs;
    }
    
    public static Integer getCount() throws SQLException{
        Database db1 = new Database();
        ResultSet Rs =null ;
        String req;
            req = "select count(*) from codecp.probleme;";
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
    
    public static String NbreSubmitionOfPrbm(String id){
         Database db1 = new Database();
       String req;
       int nbre=0;
       req = " select count(Id_Submit) from codecp.submit WHERE Id_Probleme='"+id+"';";
            Statement St;
            ResultSet Rs=null;
             try {
                 St=db1.con.createStatement();
                 Rs= St.executeQuery(req);
                
                  while(Rs.next()){
                 nbre = Integer.parseInt(Rs.getString(1));
                    }
            }
              catch(SQLException ex) {
            System.out.println("PB dans la requete select");
        }
      return String.valueOf(nbre);
    }
            
    public ResultSet AffcherSubmissions(String id){
        
            String req;
            req = " select * from codecp.submit  where Id_Probleme ='"+id+"';";
            Statement St;
            ResultSet Rs=null;
             try {
                 St=db.con.createStatement();
                 Rs= St.executeQuery(req);
            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2)+"---->"+Rs.getString(3));
            }
        
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return Rs;
                        
                        
    }
   
}
