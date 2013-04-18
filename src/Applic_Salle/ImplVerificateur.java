/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applic_Salle;

import java.util.*;
import people.*;

/**
 *
 * @author Ophiran
 */
public class ImplVerificateur extends Verificateur{

    private HashMap<String,Journaliste> JournalisteListe = new HashMap();
    
    public ImplVerificateur()
    {
        
    }
    
    public void AddJournaliste(Journaliste journaliste)
    {
        JournalisteListe.put(journaliste.getLogin(), journaliste);
    }
    
    @Override
    public String findPassword(String login) {
        return JournalisteListe.get(login).getPassword();
    }
    
}
