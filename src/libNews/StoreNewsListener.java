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
    public void StoreNewsDetected();
}