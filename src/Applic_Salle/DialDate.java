/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applic_Salle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.util.*;

/**
 *
 * @author Ophiran
 */
public class DialDate extends javax.swing.JDialog implements ActionListener{

    private Journaliste journalisteCourant;
    private Vector<LocaleCustom> localeList = new Vector<LocaleCustom>();
    
    
    /**
     * Creates new form DialDate
     * @param parent 
     * @param modal
     * @param journaliste  
     */
    public DialDate(java.awt.Frame parent, boolean modal,Journaliste journaliste) {
        super(parent, modal);
        initComponents();
        
        journalisteCourant = journaliste;
        accepter_Button.addActionListener(this);
        annuler_Button.addActionListener(this);
        
        pays_cBox.removeAllItems();
        fDate_cBox.removeAllItems();
        fTemps_cBox.removeAllItems();
        
        for(Locale item: SimpleDateFormat.getAvailableLocales()){
            if(!item.getDisplayCountry().isEmpty())
            {
                localeList.add(new LocaleCustom(item));
                pays_cBox.addItem(localeList.lastElement());
            }
        }
        
        fDate_cBox.addItem("Full");
        fDate_cBox.addItem("Long");
        fDate_cBox.addItem("Medium");
        fDate_cBox.addItem("Short");
        
        fTemps_cBox.addItem("Full");
        fTemps_cBox.addItem("Long");
        fTemps_cBox.addItem("Medium");
        fTemps_cBox.addItem("Short");
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(accepter_Button)) {
            journalisteCourant.setFormat(SimpleDateFormat.getDateTimeInstance(fDate_cBox.getSelectedIndex(), 
                    fTemps_cBox.getSelectedIndex(), ((LocaleCustom)pays_cBox.getSelectedItem()).locale));
            this.dispose();
        }
        if(e.getSource().equals(annuler_Button))
            this.dispose();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pays_cBox = new javax.swing.JComboBox();
        fDate_cBox = new javax.swing.JComboBox();
        fTemps_cBox = new javax.swing.JComboBox();
        accepter_Button = new javax.swing.JButton();
        annuler_Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pays_cBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        fDate_cBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        fTemps_cBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        accepter_Button.setText("Accepter");

        annuler_Button.setText("Annuler");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pays_cBox, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fDate_cBox, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fTemps_cBox, 0, 91, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(accepter_Button)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(annuler_Button)
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pays_cBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fDate_cBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fTemps_cBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accepter_Button)
                    .addComponent(annuler_Button))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accepter_Button;
    private javax.swing.JButton annuler_Button;
    private javax.swing.JComboBox fDate_cBox;
    private javax.swing.JComboBox fTemps_cBox;
    private javax.swing.JComboBox pays_cBox;
    // End of variables declaration//GEN-END:variables

    
}
