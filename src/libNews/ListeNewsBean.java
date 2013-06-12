/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libNews;

import java.util.Vector;

/**
 *
 * @author Ophiran
 */
public class ListeNewsBean implements NewsListener {
    Vector<NotifyNewsListener> mailingList;
    
    /**
     *
     */
    public ListeNewsBean(){
        mailingList = new Vector<NotifyNewsListener>();
    }
    
    /**
     *
     * @param e
     */
    @Override
    public void newsDetected(NewsEvent e) {
        for (NotifyNewsListener n: mailingList){
            n.notifyNewsDetected(new NotifyNewsEvent(this, e.getLocalisation() + ";" + e.getNews()));
        }
    }
    
    /**
     *
     * @param n
     */
    public void AddNotifyNewsListener(NotifyNewsListener n){
        mailingList.add(n);
    }
}
