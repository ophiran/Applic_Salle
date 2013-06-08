/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libNews;
import Applic_Salle.Journaliste;
import constantes.Categories;
import java.io.*;
/**
 *
 * @author ekimd_000
 */

public class News implements Serializable{
    
    private Journaliste auteur;
    private String contenu;
    private boolean importance;
    private String type;
    private String motsCles;
    private String commentaires;
    private boolean valid = false;
    
  
    public String getContenu(){
        return contenu;
    }
    public Journaliste getAuteur(){
        return auteur;
    }
    public boolean getImportance(){
        return importance;
    }
    public String getType(){
        return type;
    }
    public String getMotsCles(){
        return motsCles;
    }
    public String getCommentaires(){
        return commentaires;
    }
    
    public boolean isValid() {
    	return valid;
    }
        
    public void setContenu(String s){
        contenu = s;
    }
    public void setAuteur(Journaliste j){
        auteur = j.copy();
    }
    public void setImportance(boolean b){
        importance = b;
    }
    public void setType(String s){
        type = s;
    }
    public void setMotsCles(String s){
        motsCles = s;
    }
    public void setCommentaires(String s){
        commentaires = s;
    }
    
    public void setValidation(boolean valid){
    	this.valid = valid;
    }
        
    public News(String contenu, Journaliste auteur, boolean importance, String type, String commentaires){
        this.contenu = contenu;
        if (auteur == null)
            throw new NullPointerException();
        this.auteur = auteur;
        this.importance = importance;
        if(type.equals(Categories.Internationale) || type.equals(Categories.People) ||
                type.equals(Categories.Sport) ||type.equals(Categories.Politique))
            this.type = type;
        else
            this.type = Categories.Internationale;
        this.commentaires = commentaires;
        
    }
    public News(News n){
        this(n.contenu, n.auteur, n.importance, n.type, n.commentaires);
        
    }
    
    @Override
    public String toString(){
        if(importance)
            return "I - " + contenu;
        else
            return "N - " + contenu;
    }
    
    
    public String toStringNet(){
        String sImportance;
        if(importance)
            sImportance = "true";
        else
            sImportance = "false";
        return contenu + "~" + type + "~" + sImportance + "~" + auteur.getLogin();
    }
    /*
    public String toStringNetwork(){
        
        ByteArrayOutputStream serByteStream = new ByteArrayOutputStream();
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(serByteStream);
            out.writeObject(this);
            out.close();
        } catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        return serByteStream.toString();
    }
    
    public static News toNewsNetwork(String source){
        return new News("",new Journaliste("","","",""),false,"","");
    }*/


}
