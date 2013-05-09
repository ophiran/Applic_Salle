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
    
    public NewsCounterBean() {
        setCounterLabel(null);
        setNewsNumber(counter);
    }
    
    public int getNewsNumber() {
        return counter;
    }
    
    public void setNewsNumber(int counter) {
        this.counter = counter;
        if(label != null)
            label.setText(String.valueOf(counter));
    }
    
    public void setCounterLabel(JLabel label){
        this.label = label;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setNewsNumber((int)evt.getNewValue());
    }
    
    
}
