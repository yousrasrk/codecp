package DaoObj;

import Obj.Probleme;
import Obj.Competition;
import Obj.*;
import Database.*;
import java.io.File;
import static java.lang.Integer.max;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amal
 */
public class DaoCompetition extends DAO<Competition> {
    Database db;
    public DaoCompetition(Database db) {
        this.db = db;
    }

    @Override
    public ResultSet find(String id) {
        PreparedStatement PS = null;
        ResultSet Rs = null;
        try {

            PS = db.con.prepareStatement("select * from codecp.competition where Id_Competition=?;");
            PS.setObject(1, id);
            Rs = PS.executeQuery();
            Rs.next();
            //System.out.println(Rs.getString(1));
//            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2)+"---->"+Rs.getString(3)+"---->"+Rs.getString(4)
//                +"---->"+Rs.getString(5)+"---->"+Rs.getString(6));
//            }
        } catch (SQLException ex) {
            System.out.println("error find daoCompt");
        }
        return Rs;
    }
    
    
    
    
    public Boolean StillOn(String id) {
//        PreparedStatement PS = null;
//        ResultSet Rs = null;
//        try {
//
//            PS = db.con.prepareStatement("select Date from codecp.competition where Id_Competition=?;");
//            PS.setObject(1, id);
//            Rs = PS.executeQuery();
//            Rs.next();
//            //System.out.println(Rs.getString(1));
////            while(Rs.next()){
////                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2)+"---->"+Rs.getString(3)+"---->"+Rs.getString(4)
////                +"---->"+Rs.getString(5)+"---->"+Rs.getString(6));
////            }
//            Date D = new Date();
//            ZoneId defaultZoneId = ZoneId.systemDefault();
//            // Date DD = Date.from(D.atStartOfDay(defaultZoneId).toInstant());
//                   // Rs.getDate(1);
//           // if(DCompt.compareTo(D)){
//                
//            }
//        } catch (SQLException ex) {
//            System.out.println("error find daoCompt");
//        }
        //return Rs;
        return true;
    }
    
    
    
    

    @Override
    public ResultSet all() {
        String req;
        req = "select * from codecp.competition";
        Statement St;
        ResultSet Rs = null;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
//            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2)+"---->"+Rs.getString(3)+"---->"+Rs.getString(4)
//                +"---->"+Rs.getString(5)+"---->"+Rs.getString(6));
//            }

        } catch (SQLException ex) {
            System.out.println("PB dans la requete selectall DaoComp");
        }
        return Rs;
    }

    @Override
    public boolean create(Competition obj) {
        PreparedStatement PS = null;
        try {
            PS = db.con.prepareStatement("INSERT INTO codecp.competition VALUES (?,?,?,?,?,?);");
            PS.setObject(1, obj.getIdCompetition());
            PS.setObject(2, obj.getLevelComp().toString());
            PS.setObject(3, obj.getStatut());
            PS.setObject(4, obj.getDatedebut());
            PS.setObject(5, obj.getDatefin());
            PS.setObject(6, obj.getTitre());
        } catch (SQLException ex) {
            System.out.println("error create daoComp");
        }

        return (db.dmlQuery(PS) == 0) ? false : true;
    }

    @Override
    public boolean update(Competition obj, String id) {
        PreparedStatement PS = null;
        try {
            PS = db.con.prepareStatement("UPDATE codecp.competition SET Level_Comp = ?, Statut = ?, Date_debut = ?, Date_fin = ?, Titre = ? WHERE Id_Competition = ?;");

            PS.setObject(1, obj.getLevelComp());
            PS.setObject(2, obj.getStatut());
            PS.setObject(3, obj.getDatedebut());
            PS.setObject(4, obj.getDatefin());
            PS.setObject(5, obj.getTitre());
            PS.setObject(6, id);
        } catch (SQLException ex) {
            System.out.println("error update daoCompt");
        }

        return (db.dmlQuery(PS) == 0) ? false : true;
    }

    @Override
    public boolean delete(String id) {
        PreparedStatement PS = null;
        try {
            PS = db.con.prepareStatement("DELETE FROM codecp.competition WHERE codecp.competition.Id_Competition = ?;");
            PS.setObject(1, id);
        } catch (SQLException ex) {
            System.out.println("error create delete");
        }

        return (db.dmlQuery(PS) == 0) ? false : true;
    }

    public boolean AddProbleme(String id, String p) {

        PreparedStatement PS = null;
        try {
            PS = db.con.prepareStatement("insert into codecp.compose values (? ,?);");
            PS.setObject(2, id);
            PS.setObject(1, p);

        } catch (SQLException ex) {
            System.out.println("error createAddProbleme");
        }

        return (db.dmlQuery(PS) == 0) ? false : true;

    }

    public boolean deleteProbleme(String id_Compet, String id_Prob) {
        PreparedStatement PS = null;
        try {
            PS = db.con.prepareStatement("delete from codecp.compose where codecp.compose.Id_Probleme =? AND codecp.compose.Id_Competition =?;");
            PS.setObject(1, id_Prob);
            PS.setObject(2, id_Compet);
        } catch (SQLException ex) {
            System.out.println("error create deleteProbleme");
        }

        return (db.dmlQuery(PS) == 0) ? false : true;
    }

    public ResultSet AffcherListeProbleme(String id) {

        String req;
        req = " select P.Id_Probleme , P.Titre , P.Level from codecp.probleme P , codecp.compose C where P.Id_Probleme=C.Id_Probleme AND C.Id_Competition ='" + id + "';";
        Statement St;
        ResultSet Rs = null;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
//            while(Rs.next()){
//                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2)+"---->"+Rs.getString(3));
//            }

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select AffcherListeProbleme");
        }
        return Rs;

    }

    public static int getCount() throws SQLException {
        Database db1 = new Database();
        ResultSet Rs = null;
        String req;
        req = "select count(*) from codecp.competition;";
        Statement St;
        try {
            St = db1.con.createStatement();
            Rs = St.executeQuery(req);
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select getCount");
        }
        Rs.next();
        return (Rs.getInt(1));
    }

    public boolean AddUser(String Id_User, String Id_Compt) {

        PreparedStatement PS = null;
        try {

            PS = db.con.prepareStatement("insert into codecp.participe values (? ,? , ?);");
            PS.setObject(1, Id_User);
            PS.setObject(2, Id_Compt);
            PS.setObject(3, 0);
        } catch (SQLException ex) {
            System.out.println("error addUser");
        }
        String direc = "submissions_In_Comp/" + Id_User;
        File dir = new File(direc);//create the directory
        dir.mkdirs();

        return (db.dmlQuery(PS) == 0) ? false : true;
    }

    public boolean DeleteUser(String Id_U, String Id_Compt) {

        PreparedStatement PS = null;
        try {
            PS = db.con.prepareStatement("delete from codecp.participe where codecp.participe.Id_User =? and codecp.participe.Id_Competition = ?;");
            PS.setObject(1, Id_U);
            PS.setObject(2, Id_Compt);
        } catch (SQLException ex) {
            System.out.println("error DeleteUser  ");
        }

        return (db.dmlQuery(PS) == 0) ? false : true;
    }

    public ResultSet showUsers(String id_comp) {
        String req;
        req = "  select u.Username from codecp.user u , codecp.participe C where u.Id_User=C.Id_User AND C.Id_Competition ='" + id_comp + "';";
        Statement St;
        ResultSet Rs = null;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
//            while(Rs.next()){
//                 System.out.println(Rs.getString(1));
//            }

        } catch (SQLException ex) {
            System.out.println("PB dans la requete select showUsers");
        }
        return Rs;

    }

    public String CalculerLevel(String id_Comp) {
        //ResultSet RS = this.AffcherListeProbleme(id_Comp);
        Integer easy = 0;
        Integer medium = 0;
        Integer hard = 0;
        ResultSet Rs = this.AffcherListeProbleme(id_Comp);

        try {
            while (Rs.next()) {
                if (Rs.getString("Level").equals("easy")) {
                    easy++;
                }
                if (Rs.getString("Level").equals("medium")) {
                    medium++;
                }
                if (Rs.getString("Level").equals("hard")) {
                    hard++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCompetition.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (max(max(easy, hard), medium) == easy) {
            return "easy";
        }
        if (max(max(easy, hard), medium) == easy) {
            return "medium";
        }
        return "hard";

    }

//   public ResultSet AffcherSubmissions(String id){
//        
//            String req;
//            req = " select S.Id_Submit , S.Username , S.Id_Probleme from codecp.submit_in_comp S where S.Id_Competition ='"+id+"';";
//            Statement St;
//            ResultSet Rs=null;
//             try {
//                 St=db.con.createStatement();
//                 Rs= St.executeQuery(req);
////            while(Rs.next()){
////                System.out.println(Rs.getString(1)+"---->"+Rs.getString(2)+"---->"+Rs.getString(3));
////            }
//        
//        } catch (SQLException ex) {
//            System.out.println("PB dans la requete select");
//        }
//        return Rs;
//                        
//                        
//    }
    //statistics moy de submissions accepted of a competition
    public static String AverageLevel(String id) {
        Database db1 = new Database();
        Integer resultat = 0;
        String req;
        int acc = 0;
        int tot = 1;
        // nbre de submissions of a competition
        req = " select count(S.Id_Submit) from codecp.submit_in_comp S where S.Id_Competition ='" + id + "';";
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
            System.out.println("PB dans la requete select AverageLevel");
        }
        //nbre de submissions accepted
        String sql = " select count(SC.Id_Submit) from codecp.submit_in_comp SC  where SC.verdict = 'accepted' and SC.Id_Competition ='" + id + "';";
        Statement Smt;
        ResultSet Res = null;
        try {
            St = db1.con.createStatement();
            Res = St.executeQuery(sql);
            while (Res.next()) {
                acc = Integer.parseInt(Res.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println("PB dans la requete select AverageLevel");
        }
        if (tot != 0) {
            resultat = (acc * 100) / tot;
        }
        return resultat.toString();
    }

    //nbre de participant dans une comp
    public static int NbreParticipant() {
        Database db1 = new Database();
        String req;
        int tot = 0;
        req = " select count(Username) from codecp.participe";
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
            System.out.println("PB dans la requete select NbreParticipant daoCompetition");
        }
        return tot;
    }

    //Nbre totale des competitions 
    public static int NbreTotalCompetition() {
        Database db1 = new Database();
        String req;
        int tot = 0;
        req = " select count(Id_Competition) from codecp.competition";
        Statement St;
        ResultSet Rs = null;
        try {
            St = db1.con.createStatement();
            Rs = St.executeQuery(req);

            while (Rs.next()) {
                tot = Integer.parseInt(Rs.getString(1));
                //System.out.println(tot);
            }
        } catch (SQLException ex) {
            System.out.println("PB dans la requete NbreTotalCompetition DaoCompetition");
        }
        return tot;
    }

    //nbre de participant dans une competition
    public static Integer NbreTotalParticipantInCompeti(String id) {
        Database db1 = new Database();
        String req;
        int tot = 0;
        req = " select count(Id_User) from codecp.participe where Id_Competition ='" + id + "';";
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
            System.out.println("PB dans la requete select NbreTotalParticipantInCompeti");
        }
        return tot;
    }

  
    
    //les competition ou le prbm est
     public  Vector<String> existPr(String idP) {
//        Database db1 = new Database();
         Vector<String> TableCompet = new Vector<>();
        String req;
        int tot = 0;
        req = " select Id_Competition from codecp.compose where Id_Probleme = '"+idP+"';";
        Statement St;
        ResultSet Rs = null;
        try {
            St = db.con.createStatement();
            Rs = St.executeQuery(req);
            while (Rs.next()) {                
                TableCompet.add(Rs.getString(1));
            }

        } catch (SQLException ex) {
            System.out.println("PB dans la requete existPr DAOCompetition");
        }
        return TableCompet;
        
    }
    
}
