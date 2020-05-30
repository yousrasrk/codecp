package DaoObj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Obj.User;
import java.sql.PreparedStatement;
import Database.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class daoUser extends DAO<User> {

    Database db;

    public daoUser(Database db) {
        this.db = db;
    }

    public boolean create(User p) {
        PreparedStatement Pst = null;
        try {
            Pst = db.con.prepareStatement("INSERT INTO codecp.user VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");
            //System.out.println((User)(p).);
            Pst.setObject(1, p.getId_User());
            Pst.setObject(2, "");
            Pst.setObject(3, (p).getMdp());
            Pst.setObject(4, (p).getEmail());
            Pst.setObject(5, "");
            Pst.setObject(6, "");
            Pst.setObject(7, "");
            Pst.setObject(8, "");
            Pst.setObject(9, "");
            Pst.setObject(10, (p).getDate_Inscription());
            Pst.setBoolean(11, false);
            Pst.setInt(12, 0);
            Pst.setObject(13, "");

        } catch (SQLException ex) {
            System.out.println("error const User");
        }
        String direc = "submissions/" + (p).getId_User();
        File dir = new File(direc);//create the directory
        dir.mkdirs();
        return (db.dmlQuery(Pst) == 0) ? false : true;
    }

    public boolean ifexist(String UserN) {
        ResultSet Rs = null;
        String sql = "SELECT count(Username) FROM codecp.user WHERE Username='" + UserN + "';";
        Statement St;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(sql);
            Rs.next();
            if (Rs.next()) {
                return true;

            }
        } catch (SQLException ex) {
            System.out.println("PB dans la requete count");
        }
        return false;
    }

    public boolean ifexistEmail(String Emaili) {
        ResultSet Rs = null;
        String sql = "SELECT count(Username) FROM codecp.user WHERE E_Mail='" + Emaili + "';";
        Statement St;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(sql);
            Rs.next();
            if (Rs.next()) {
                return true;

            }
        } catch (SQLException ex) {
            System.out.println("PB dans la requete count");
        }
        return false;
    }

    public String suggestion(String UserN) {
        String rep = "";
        if (ifexist(UserN)) {
            //suggestions

        }
        //construire objet o kml 3alah
        return rep;

    }

    //ajouter ou modifier infos

    public boolean updateFirst(User p, String Id) {
        boolean rs = false;
        //super.create(p);
        //super.update(p, Id);
        try {
            PreparedStatement Pst;
            Pst = db.con.prepareStatement("UPDATE codecp.user set Username = ?,  Nom =?, Prenom =? , Language=?, Tel=?,  Photo = ?  WHERE Id_User=?;");
            Pst.setString(1, (p).getUsername());

            Pst.setString(2, (p).getNom());
            Pst.setString(3, (p).getPrenom());
            Pst.setString(4, (p).getLangage());
            Pst.setString(5, (p).getTel());

            Pst.setString(6, (p).getPhoto());
            Pst.setString(7, Id);
            if (db.dmlQuery(Pst) == 1) {
                rs = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(daoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        //super.delete(Id);

        return rs;

    }

    public boolean update(User p, String Id) {
        boolean rs = false;
        try {
            PreparedStatement Pst;
            Pst = db.con.prepareStatement("UPDATE codecp.user set  Nom =?, Prenom =? , Language=?, Tel=?, E_Mail=? , Photo = ?  WHERE Id_User=?;");

            Pst.setString(1, (p).getNom());
            Pst.setString(2, (p).getPrenom());
            Pst.setString(3, (p).getLangage());
            Pst.setString(4, (p).getTel());
            Pst.setString(5, (p).getEmail());
            Pst.setString(6, (p).getPhoto());
            Pst.setString(7, Id);

            if (db.dmlQuery(Pst) == 1) {
                rs = true;
            }
        } catch (SQLException ex) {
            System.out.println("error Update");
        }

        return rs;

    }
    
    public boolean updatePasswd(String Id , String NewMdp) {
        boolean rs = false;
        try {
            PreparedStatement Pst;
            Pst = db.con.prepareStatement("UPDATE codecp.user set  Mdp =? WHERE Id_User=?;");
            Pst.setString(1, NewMdp);
            Pst.setString(2, Id);
 
            if (db.dmlQuery(Pst) == 1) {
                rs = true;
            }
        } catch (SQLException ex) {
            System.out.println("error Update");
        }

        return rs;

    }
    

    public String VerifieConnection(String Email, String Mdp) {
        ResultSet Rs = null;
        String sql = "Select Id_User FROM codecp.user WHERE E_mail=? AND Mdp=?;";
        Statement St;
        try {
            PreparedStatement stmt = db.con.prepareStatement(sql);
            stmt.setString(1, Email);
            stmt.setString(2, Mdp);
            Rs = stmt.executeQuery();
            if (Rs.next()) {
                return Rs.getString(1);
            }

        } catch (SQLException ex) {
            System.out.println("PB dans findConnection");
        }

        return null;

    }

    public boolean findEmail(String Email) {
        ResultSet Rs = null;
        String sql = "Select Id_User FROM codecp.user WHERE E_mail=? ;";
        Statement St;
        try {
            PreparedStatement stmt = db.con.prepareStatement(sql);
            stmt.setString(1, Email);

            Rs = stmt.executeQuery();
            if (Rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println("PB dans find Email");
        }

        return false;

    }

    public String findId(String Username) {
        ResultSet Rs = null;
        String sql = "Select Id_User FROM codecp.user WHERE Username=? ;";
        Statement St;
        try {
            PreparedStatement stmt = db.con.prepareStatement(sql);
            stmt.setString(1, Username);

            Rs = stmt.executeQuery();
            if (Rs.next()) {
                //System.out.println(Rs.getString(1));
                return Rs.getString(1);
            }

        } catch (SQLException ex) {
            System.out.println("PB dans find Email");
        }

        return "";

    }
    
    public String findPwd(String Id) {
        ResultSet Rs = null;
        String sql = "Select Mdp FROM codecp.user WHERE Id_User=? ;";
        Statement St;
        try {
            PreparedStatement stmt = db.con.prepareStatement(sql);
            stmt.setString(1, Id);
            Rs = stmt.executeQuery();
            Rs.next();
            //if (Rs.next()) {
                //System.out.println(Rs.getString(1));
            System.out.println(Rs.getString(1));
                return Rs.getString(1);
//            }
//
        } catch (SQLException ex) {
            System.out.println("PB dans find Email");
        }

        return "";

    }
    
    

    //supprimer
    public boolean delete(String id) {
        boolean rs = false;
        //id= "U1";
        String sql = "DELETE FROM codecp.user WHERE Id_User = ? ;";
        try {

            PreparedStatement stmt = db.con.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
            rs = true;
        } catch (SQLException ex) {
            Logger.getLogger(daoUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    //lister
    public ResultSet all() {
        ResultSet Rs = null;
        String req;
        req = "select * from codecp.user where Username !='" + "" + "' and Username != 'admin';";
        Statement St;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
//            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString("Mdp")+"---->"+Rs.getString("Nom")+"---->"
//                        +Rs.getString("Prenom")+"---->"+Rs.getString("Language")+"---->"+Rs.getString("E_Mail")+"---->"+Rs.getString("Tel")+"---->"+Rs.getString("Date_Inscription")
//                         +"---->"+Rs.getObject("Actif")+"---->"+Rs.getObject("Seuil")+"---->"+Rs.getString("path"));
//            }

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return Rs;

    }

    @Override
    public ResultSet find(String id) {
        ResultSet Rs = null;
        String sql = "Select * FROM codecp.user WHERE Id_User = ?;";
        Statement St;
        try {
            // System.out.println(id);
            PreparedStatement stmt = db.con.prepareStatement(sql);
            stmt.setString(1, id);
            Rs = stmt.executeQuery();
            Rs.next();
        } catch (SQLException ex) {
            System.out.println("PB dans find");
        }
        return Rs;

    }

    public static Integer getCount() throws SQLException {
        Database db1 = new Database();
        ResultSet Rs = null;
        String req;
        req = "select count(*) from codecp.user;";
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

    public static ResultSet LastUsers() throws SQLException {
        Database db1 = new Database();
        ResultSet Rs = null;
        String req;
        req = "select * from codecp.user  where Username !='' order by Date_Inscription asc ;";
        Statement St;
        try {
            St = db1.con.createStatement();
            Rs = St.executeQuery(req);

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        Rs.next();
        return (Rs);

    }

    public ResultSet Afficher() {
        ResultSet Rs = null;
        String req;
        req = "select E_Mail , Date_Inscription from codecp.user where username ='';";
        Statement St;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
            //Rs.next();
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select");
        }
        return Rs;
    }

}
