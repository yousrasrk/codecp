/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;

/**
 *
 * @author Amal
 */
public abstract class DAO <T>{
    protected Database db;
   
   public abstract ResultSet find(String id); //Rechercher
   
   public abstract ResultSet all(); //recuperer tout
   
   public abstract boolean create(T obj);
   
   public abstract boolean update(T obj , String id);
   
   public abstract boolean delete (String id);
}
