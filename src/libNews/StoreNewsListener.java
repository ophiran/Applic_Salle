/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libNews;

import java.util.EventListener;

/**
 *
 * @author Ophiran
 */
public interface StoreNewsListener extends EventListener{
    /**
     *
     * @param e
     */
    public void StoreNewsDetected(StoreNewsEvent e);
}
