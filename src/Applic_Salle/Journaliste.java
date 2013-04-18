/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applic_Salle;

import people.*;

/**
 *
 * @author Ophiran
 */
public class Journaliste extends Personne implements Utilisateur,Identifiable{

    private String login;
    private String password;
    private String id;
    
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

    
    
}
