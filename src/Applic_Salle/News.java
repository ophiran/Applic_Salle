/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applic_Salle;

/**
 *
 * @author ekimd_000
 */

public class News {
    
    public static enum TypeNews {
        POLITIQUE, INTER, SPORT, PEOPLE
    }
    
    private String contenu;
    private Journaliste auteur;
    private boolean importance;
    private TypeNews type;
   
        
    public News(String contenu, Journaliste auteur, boolean importance, TypeNews type){
        this.contenu = contenu;
        this.auteur = auteur;
        this.importance = importance;
        this.type = type;
    }


}
