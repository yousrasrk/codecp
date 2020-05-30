package DaoObj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Obj.Message_Perso;
import Obj.Message;
import java.sql.PreparedStatement;

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
public class daoMessagePerso extends daoMessage {

    public daoMessagePerso(Database db) {
        super(db);
    }

    @Override
    public ResultSet find(String iD) {
        ResultSet Rs = null;
        //System.out.println("the id is " + iD);
        String sql = "select U.Username , M.Id_User , M.Use_Id_User , M.Id_Msg , M.Contenu , M.Date , M.Lu , M.Objet\n"
                + "from codecp.message_perso M , codecp.user U\n"
                + "where M.Use_Id_User = U.Id_User\n"
                + "AND M.Id_Msg = '"+iD+"';";
        //String sql = "Select * FROM codecp.message_perso WHERE Id_Msg='" + iD + "';";
        Statement St;

        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(sql);
            Rs.next();
//            while(Rs.next()){
//                  System.out.println(Rs.getString(1)+"---->"+Rs.getString(2)+"---->"+Rs.getString(3)+"---->"+Rs.getString(4)+"---->"+Rs.getString(5)+"---->"+Rs.getString(6)+"---->"+Rs.getString(7)+"---->"+Rs.getString(8));
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
        req = "select * from codecp.message_perso;";
        Statement St;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
//            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2)+"---->"+Rs.getString(3));
//            }

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return Rs;
    }

    public ResultSet allEmet(String id) {
        ResultSet Rs = null;
        String req;
        req = "select * from codecp.message_perso where Id_User ='" + id + "';";
        Statement St;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
//            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2)+"---->"+Rs.getString(3));
//            }

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return Rs;
    }

    public ResultSet allRec(String id) {
        ResultSet Rs = null;
        String req;
        req = "select * from codecp.message_perso where Use_Id_User ='" + id + "';";
        Statement St;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
//            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2)+"---->"+Rs.getString(3));
//            }

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return Rs;
    }

    public boolean create(Message M) {
        PreparedStatement Pst = null;
        PreparedStatement Ps = null;
        try {
//            System.out.println("The is is "+ ((Message_Perso) M).getId_Msg());
//            System.out.println("emeteur is "+ ((Message_Perso) M).getUsername_eme());
//            System.out.println("The recepteru is is "+ ((Message_Perso) M).getUsername_rece());
            Ps = db.con.prepareStatement("INSERT INTO codecp.message_perso VALUES(?,?,?,?,?,?,?);");
            Ps.setObject(1, ((Message_Perso) M).getUsername_eme());
            Ps.setObject(2, ((Message_Perso) M).getUsername_rece());
            Ps.setObject(3, ((Message_Perso) M).getId_Msg());
            Ps.setObject(4, M.getContenu());
            Ps.setObject(5, M.getDate());
            Ps.setObject(6, M.getLu());
            Ps.setObject(7, M.getObjet());

        } catch (SQLException ex) {
            Logger.getLogger(daoMessagePerso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (db.dmlQuery(Ps) == 0) ? false : true;
    }

    @Override
    public boolean update(Message obj, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        boolean rs = false;
        String sql = "DELETE FROM codecp.message_perso WHERE Id_Msg=?;";
        try {
            PreparedStatement stmt = db.con.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.println("error delete msg");
        }

        return rs;
    }
    
    
    public boolean updateLu(String Id) {
        boolean rs = false;
        
        try {
            PreparedStatement Pst;
            Pst = db.con.prepareStatement("UPDATE codecp.message_perso set  Lu ='1' WHERE Id_Msg=?;");

            Pst.setString(1, Id);
            

            if (db.dmlQuery(Pst) == 1) {
                System.out.println("id done OMG");
                rs = true;
            }
        } catch (SQLException ex) {
            System.out.println("error Update");
        }

        return rs;

    }
    
    
    public static int getCount() throws SQLException {
        Database db1 = new Database();
        ResultSet Rs = null;
        String req;
        req = "select count(*) from codecp.message_perso;";
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
