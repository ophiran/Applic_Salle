/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libNews;


import java.beans.PropertyChangeSupport;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 *
 * @author Ekym
 * @author Ophiran
 * 
 */
public class StoringNewsBean implements StoreNewsListener{
	
	private String path;
    private Vector<News> newsList = new Vector<News>();
    protected PropertyChangeSupport propertyHandling = new PropertyChangeSupport(this);
    
    public StoringNewsBean() {
        
    }
    

    @Override
    public void StoreNewsDetected(StoreNewsEvent e) {
    	if(e.getOperation())
    		newsList.add(e.getNews());
    	else
    		newsList.remove(e.getNews());
    	writeNews();
    }
    
    public void setPath(String path) {
    	this.path = path;
    }
    
    public void writeNews() {
    	if(path != null) {
	    	try {
	    		FileOutputStream fos = new FileOutputStream(path);
	    		ObjectOutputStream stream = new ObjectOutputStream(fos);
	    		stream.writeObject(newsList);
	    		stream.close();
	    	}
	    	catch (IOException e) {
	    		e.printStackTrace();
	    	}
    	}
    }
    
    public void readNews() {
    	
    }
    
}
