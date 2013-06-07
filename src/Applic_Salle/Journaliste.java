/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applic_Salle;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import people.*;

/**
 *
 * @author Ophiran
 */
public class Journaliste extends Personne implements Utilisateur, Identifiable, Cloneable,Serializable{

    private String login;
    private String password;
    private String id;
    private DateFormat dateFormat;
    
    public Journaliste(String nom,String prenom,String login,String password)
    {
        this(nom,prenom,login,password,"");
    }
    
    public Journaliste(String nom,String prenom,String login,String password,String id)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.id = id;
        dateFormat = DateFormat.getDateInstance();
    }
    
    public void setFormat(DateFormat dateFormat)
    {
        this.dateFormat = dateFormat;
    }
    
    public DateFormat getFormat()
    {
        return dateFormat;
    }
    
    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPass(String password) {
        this.password = password;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Object clone(){
        Object o = null;
        try{
            o = super.clone();
        }
        catch(CloneNotSupportedException cnse){
            cnse.printStackTrace(System.err);
        }
        return o;
    }
    
    public Journaliste copy(){
        return new Journaliste(this.nom, this.prenom, this.login, this.password, this.id);
    }
     


    
    
}
