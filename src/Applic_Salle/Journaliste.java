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
public class Journaliste extends Personne implements Utilisateur, Identifiable, Cloneable, Serializable{

    private String login;
    private String password;
    private String id;
    private DateFormat dateFormat;
    
    /**
     *
     * @param nom
     * @param prenom
     * @param login
     * @param password
     */
    public Journaliste(String nom,String prenom,String login,String password)
    {
        this(nom,prenom,login,password,"Normal");
    }
    
    /**
     *
     * @param nom
     * @param prenom
     * @param login
     * @param password
     * @param id
     */
    public Journaliste(String nom,String prenom,String login,String password,String id)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.id = id;
        dateFormat = DateFormat.getDateInstance();
    }
    
    public String toString()
    {
        return login;
    }
    /**
     *
     * @param dateFormat
     */
    public void setFormat(DateFormat dateFormat)
    {
        this.dateFormat = dateFormat;
    }
    
    /**
     *
     * @return
     */
    public DateFormat getFormat()
    {
        return dateFormat;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     */
    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    @Override
    public void setPass(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
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
    
    /**
     *
     * @return
     */
    public Journaliste copy(){
        return new Journaliste(this.nom, this.prenom, this.login, this.password, this.id);
    }
     


    
    
}
