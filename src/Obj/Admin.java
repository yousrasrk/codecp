package Obj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Amal
 */
public class Admin extends User{
   
    String password;
    
    public Admin(String log, String pass){
        super("Admin");
        password=pass;
    }
  
    public String getMdp(){
        return password;
    }
    public void setMdp(String mdp){
        password = mdp;
    }

}
