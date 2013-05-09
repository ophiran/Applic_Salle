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
    protected String news;
    
    public NotifyNewsEvent(Object o, String news){
        super(o);
        this.news = news;
    }
}
