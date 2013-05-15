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
public class NotifyNewsEvent extends EventObject{
    protected String localisationAndNews;
    
    public String getNews(){
        String news[] = localisationAndNews.split(";");
        return news[1];
    }
    
    public String getLocalisation(){
        String news[] = localisationAndNews.split(";");
        return news[0];
    }
    
    public NotifyNewsEvent(Object o, String localisationAndNews){
        super(o);
        this.localisationAndNews = localisationAndNews;
    }
}
