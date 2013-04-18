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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
