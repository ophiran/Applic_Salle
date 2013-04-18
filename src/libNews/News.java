/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libNews;
import Applic_Salle.Journaliste;
import constantes.Categories;
/**
 *
 * @author ekimd_000
 */

public class News {
    
    private String contenu;
    private Journaliste auteur;
    private boolean importance;
    private String type;
  
        
    public News(String contenu, Journaliste auteur, boolean importance, String type){
        this.contenu = contenu;
        this.auteur = auteur;
        this.importance = importance;
        if(type.equals(Categories.Internationale) || type.equals(Categories.People) ||
                type.equals(Categories.Sport) ||type.equals(Categories.Politique))
            this.type = type;
        else
            this.type = Categories.Internationale;
        
    }


}
