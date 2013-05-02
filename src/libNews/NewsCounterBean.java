/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libNews;

import javax.swing.JLabel;

/**
 *
 * @author Ophiran
 */
public class NewsCounterBean {
    
    private int counter = 0;
    private JLabel label;
    
    public NewsCounterBean(JLabel label) {
        this.label = label;
        setNewsNumber(counter);
    }
    
    public int getNewsNumber() {
        return counter;
    }
    
    public void setNewsNumber(int counter) {
        this.counter = counter;
        label.setText(String.valueOf(counter));
    }
    
    
}
