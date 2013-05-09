/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libNews;


import java.beans.PropertyChangeSupport;
import java.util.*;

/**
 *
 * @author Ophiran
 */
public class StoringNewsBean implements StoreNewsListener{
    private Vector<News> newsList = new Vector<News>();
    protected PropertyChangeSupport propertyHandling = new PropertyChangeSupport(this);
    
    public StoringNewsBean() {
        
    }
    

    @Override
    public void StoreNewsDetected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
