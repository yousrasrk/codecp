/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amal
 */
public class Database {
    public final static String username = "root";
    public final static String password = "";
    public final static String DRIVER="com.mysql.jdbc.Driver";
    public final static String URL = "jdbc:mysql://localhost:3306/mysql";
    
    public static Connection con;
    public static Statement st;
    public static ResultSet rs;
    
    public  Database() {
        try {
            Class.forName(DRIVER);
               con = DriverManager.getConnection(URL, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    } // cstr par defaut
    
    //cnx avec bd
  
    
    //select
    public static ResultSet query(String q){
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(q);
        } 
        catch (Exception ex) {
            System.out.println("Error query");
  
        }
        return rs;
    }
    
    //insert, update, delete
    public static int dmlQuery(String q){
        try {
          
            st = con.createStatement();
            return st.executeUpdate(q);
        } 
        catch (Exception ex) {
            System.out.println("Error execute st");
        }
        return 0;
    }
     public static int dmlQuery(PreparedStatement P){
        try {
          
            return P.executeUpdate();
        } 
        catch (Exception ex) {
            System.out.println("Error prepared statement");
        }
        return 0;
    }
    
}
