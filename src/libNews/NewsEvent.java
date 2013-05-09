/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libNews;

import java.util.EventObject;

/**
 *
 * @author Ophiran
 */
public class NewsEvent extends EventObject {
    private String news;
    private String localisation;
    
    public NewsEvent(Object source,String news,String localisation){
        super(source);
    }
    
    public String getNews(){
        return news;
    }
    
    public String getLocalisation(){
        return localisation;
    }
}
