/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libNews;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;

/**
 *
 * @author Ophiran
 */
public class NewsCounterBean implements PropertyChangeListener{
    
    private int counter = 0;
    private JLabel label;
    
    /**
     *
     */
    public NewsCounterBean() {
        setCounterLabel(null);
        setNewsNumber(counter);
    }
    
    /**
     *
     * @param label
     */
    public NewsCounterBean(JLabel label){
        setCounterLabel(label);
        setNewsNumber(counter);
    }
    
    /**
     *
     * @return
     */
    public int getNewsNumber() {
        return counter;
    }
    
    /**
     *
     * @param counter
     */
    public void setNewsNumber(int counter) {
        this.counter = counter;
        if(label != null)
            label.setText(String.valueOf(counter));
    }
    
    /**
     *
     * @param label
     */
    public void setCounterLabel(JLabel label){
        this.label = label;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    	if(evt.getPropertyName().equals("ChargeNews"))
    		setNewsNumber((int)evt.getNewValue());
    	else if(evt.getPropertyName().equals("AjoutNews"))
    		setNewsNumber(getNewsNumber() + 1);
    	else if(evt.getPropertyName().equals("SupNews"))
    		setNewsNumber(getNewsNumber() - 1);
    }
    
    
}
