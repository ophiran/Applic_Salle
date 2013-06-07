/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applic_Salle;

import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import people.*;

/**
 *
 * @author Ophiran
 */
public class ImplVerificateur extends Verificateur implements Serializable{

    public HashMap<String,Journaliste> JournalisteListe = new HashMap();
    private String path;
    
    public ImplVerificateur()
    {
        
    }
    
    public void SetPath(String path)
    {
       this.path = path; 
       
    }
    public void Deserialize()
    {
        try
        {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            JournalisteListe = (HashMap<String, Journaliste>)ois.readObject();
            ois.close();
        	
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(FileNotFoundException e)
        {
            this.AddJournaliste(new Journaliste("login","login","login","test","Administrateur"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void Serialize()
    {
        try{
            File file = new File(path);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(JournalisteListe);
            oos.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
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
