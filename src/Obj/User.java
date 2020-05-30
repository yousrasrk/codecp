package Obj;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import DaoObj.daoUser;

import java.sql.SQLException;
import java.util.Date;
/**
 *
 * @author Amal
 */
public class User {
    String Id_User;
    String Username;
    String Mdp;
    String E_Mail;
    String Tel;
    String Photo;
    String Nom;
    String Prenom;
    String Language;
    Date Date_Inscription ;
    boolean Actif ;
    int Seuil;
    String path ;

    public User(String E_Mail, Date date) {
        this.E_Mail = E_Mail;
        this.Date_Inscription= date;
    }
    
    
    public User(String Username, String Nom, String Prenom) {
        this.Username = Username;
        this.Nom = Nom;
        this.Prenom = Prenom;
        path = "./UserProfile/"+this.Username+""+".jpg";
    }

    public User( String Username, String Tel, String Photo, String Nom, String Prenom, String Language) {
        this.Username = Username;
        this.Tel = Tel;
        this.Photo = Photo;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Language = Language;
    }

    public User(String Username, String E_Mail, String Tel, String Photo, String Nom, String Prenom, String Language) {
        System.out.println("The photo is  : " + Photo);
        this.Username = Username;
        this.E_Mail = E_Mail;
        this.Tel = Tel;
        this.Photo = Photo;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Language = Language;
    }
    
    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getE_Mail() {
        return E_Mail;
    }

    public void setE_Mail(String E_Mail) {
        this.E_Mail = E_Mail;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }
    
   public User(String U){
       Id_User = U;
   }
    
     public User(String password, String email){
        Integer id=0;
        try {
            id = daoUser.getCount();
            //System.out.println("The id is " + id);
        } catch (SQLException ex) {
            System.out.println("erreur const de Personne");
        }
       
       Id_User = "U"+(id++).toString();
       Mdp = password;
       E_Mail = email;
       Date_Inscription = new Date();
   }
   public User(String Username ,String password, String nom, String pre, String tel, String email, String lang, String image){
       Integer id=0;
        try {
            id = daoUser.getCount();
            //System.out.println("The id is " + id);
        } catch (SQLException ex) {
            System.out.println("erreur const de Personne");
        }
       
       Id_User = "U"+(id++).toString();
       this.Username = Username;
       Mdp = password;
       Nom = nom;
       Prenom = pre;
       Tel = tel;
       E_Mail = email;
       Language = lang;
       Photo = image;
   }
   

    public String getPhoto(){
        return Photo;
    }
    public void setPhoto(String image){
        Photo = image;
    }
    
      
    public String getMdp(){
        return Mdp;
    }
    public void setMdp(String mdp){
        Mdp = mdp;
    }
    public String getEmail(){
        return E_Mail;
    }
    public void setEmail(String mail){
        E_Mail = mail;
    }

    public Date getDate_Inscription() {
        return Date_Inscription;
    }
    public void setDate_Inscription(Date date) {
        Date_Inscription = date;
    }

    public boolean getActif() {
        return Actif;
    }
    public void setActif(boolean act) {
        Actif = act;
    }

    public int getSeuil() {
        return Seuil;
    }
    public void setSeuil(int seuil) {
        this.Seuil = seuil;
    }

    public String getPath() {
        return path;
    }
    public String getTel(){
        return Tel;
    }
    public void setTel(String tele){
        Tel = tele;
    }
    public String getNom(){
        return Nom;
    }
    public void setNom(String name){
        Nom = name;
    }
    public String getPrenom(){
        return Prenom;
    }
    public void setPrenom(String pre){
        Prenom = pre;
    }
    public String getLangage(){
        return Language;
    }
    public void setLangage(String lang){
        Language = lang;
    }
   
   
    public void setPath(String path) {
        this.path = path;
    }

    public String getId_User() {
        return Id_User;
    }

    public void setId_User(String Id_User) {
        this.Id_User = Id_User;
    }

    
    
}
