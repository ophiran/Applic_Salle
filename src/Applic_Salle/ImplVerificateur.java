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
        this.AddJournaliste(new Journaliste("nom","prenom","login","test"));
    }
    
    public void AddJournaliste(Journaliste journaliste)
    {
        JournalisteListe.put(journaliste.getLogin(), journaliste);
    }
    
    public Journaliste getJournaliste(String login)
    {
        return JournalisteListe.get(login);
    }
    
    @Override
    public String findPassword(String login) {
        Journaliste temp = JournalisteListe.get(login);
        if(temp != null)
            return temp.getPassword();
        return null;
    }
    
}
