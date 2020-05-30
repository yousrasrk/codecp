package DaoObj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DaoObj.daoUser;
import Obj.Submit;
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
public class daoSubmit extends DAO<Submit> {

    Database db;

    public daoSubmit(Database db) {
        this.db = db;
    }

    @Override
    public ResultSet find(String id) {
        ResultSet Rs = null;
        String sql = "Select * FROM codecp.submit WHERE Id_Submit='" + id + "';";
        Statement St;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(sql);
            Rs.next();
//            while(Rs.next()){
//                  System.out.println(Rs.getString(1)+"---->"+Rs.getString("Username"));
//            }

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return Rs;
    }

    @Override
    public ResultSet all() {
        ResultSet Rs = null;
        String req;
        req = "select * from codecp.submit;";
        Statement St;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
//            while(Rs.next()){
//                System.out.println(Rs.getString(1));
//            }

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return Rs;

    }
    
    public ResultSet all(String IdU) {
        ResultSet Rs = null;
        String req;
        req = "select * from codecp.submit where Id_User='"+IdU+"';";
        Statement St;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
//            while(Rs.next()){
//                System.out.println(Rs.getString(1));
//            }

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return Rs;

    }

    @Override
    public boolean create(Submit obj) {
        PreparedStatement Pst = null;
        try {
            Pst = db.con.prepareStatement("INSERT INTO codecp.submit VALUES(?,?,?,?,?,?,?);");
            //System.out.println((p).getUsername());
            Pst.setObject(1, obj.getUsername());
            Pst.setObject(2, obj.getId_Probleme());
            Pst.setObject(3, obj.getId_Submit());
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
    public boolean update(Submit obj, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ResultSet AfficherUser(String idUser) {
        ResultSet Rs = null;
        String req;
        req = "select * from codecp.submit where Id_User='" + idUser + "';";
        Statement St;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
//            while (Rs.next()) {
//                System.out.println(Rs.getString(1));
//            }

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return Rs;

    }

    public ResultSet AfficherProbleme(String idProblm) {
        ResultSet Rs = null;
        String req;
        req = "select * from codecp.submit where Id_Probleme=" + idProblm + ";";
        Statement St;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
            while (Rs.next()) {
                System.out.println(Rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return Rs;

    }

    public static int getCount() throws SQLException {
        Database db1 = new Database();
        ResultSet Rs = null;
        String req;
        req = "select count(*) from codecp.submit;";
        Statement St;
        try {
            St = db1.con.createStatement();
            Rs = St.executeQuery(req);

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        Rs.next();
        return (Rs.getInt(1));

    }

    public static Integer getCountByUser(String Id) throws SQLException {
        Database db1 = new Database();
        ResultSet Rs = null;
        String req;
        req = "select count(*) from codecp.submit where Id_User = '" + Id + "';";
        Statement St;
        try {
            St = db1.con.createStatement();
            Rs = St.executeQuery(req);

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        Rs.next();
        return (Rs.getInt(1));

    }

}
