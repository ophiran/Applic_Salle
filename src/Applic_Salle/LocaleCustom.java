/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applic_Salle;

import java.util.Locale;

/**
 *
 * @author Ophiran
 */
public class LocaleCustom {
    public Locale locale;
    
    public LocaleCustom(Locale locale){
        this.locale = locale;
    }
    
    @Override
    public String toString(){
        return locale.getDisplayCountry();
    }
}
