/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libNews;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
    	if(e.getOperation()){
    		newsList.add(e.getNews());
    		propertyHandling.firePropertyChange("AjoutNews", null, e.getNews());
    	}
    	else {
    		newsList.remove(e.getNews());
    		propertyHandling.firePropertyChange("SupNews", e.getNews(), null);
    	}
    	writeNews();
    }
    
    public void setPath(String path) {
    	this.path = path;
    	if(path != null)
    		readNews();
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
    	if(path != null) {
    		try {
    			FileInputStream fis = new FileInputStream(path);
    			ObjectInputStream stream = new ObjectInputStream(fis);
    			newsList = (Vector<News>) stream.readObject();
    			stream.close();
    		}
    		catch (IOException e){
    			
    		}
    		catch (ClassNotFoundException e){
    			
    		}
    	}
    }
    
    public Vector<News> getNews() {
    	return newsList;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
    	propertyHandling.addPropertyChangeListener(l);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener l) {
    	propertyHandling.removePropertyChangeListener(l);
    }
}
