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
public class StoreNewsEvent extends EventObject{
	
	private News news;
	private Boolean operation;
	
    /**
     *
     * @param o
     * @param news
     * @param operation
     */
    public StoreNewsEvent(Object o,News news,Boolean operation){
        super(o);
        this.news = news;
        this.operation = operation;
    }
    
    /**
     *
     * @return
     */
    public News getNews(){
    	return news;
    }
    
    /**
     * 
     * @return true pour un ajout et false pour un retrait
     */
    public Boolean getOperation() {
    	return operation;
    }
}
