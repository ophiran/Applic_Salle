/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applic_Salle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ekym
 */
public class DialRechCatNews extends javax.swing.JDialog implements ActionListener {

    /**
     * Creates new form DialRechCatNews
     */
    public DialRechCatNews(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        radioGroup.add(pol_radio);
        radioGroup.add(sport_radio);
        radioGroup.add(inter_radio);
        details_button.addActionListener(this);
        quitter_button.addActionListener(this);
        
        if(pol_radio.isSelected()){
            table.setModel(new DefaultTableModel(
        }
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        details_button = new javax.swing.JButton();
        quitter_button = new javax.swing.JButton();
        pol_radio = new javax.swing.JRadioButton();
        sport_radio = new javax.swing.JRadioButton();
        inter_radio = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        details_button.setText("Details");

        quitter_button.setText("Quitter");

        pol_radio.setText("Politique");

        sport_radio.setText("Sport");

        inter_radio.setText("International");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(details_button)
                            .addComponent(quitter_button))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sport_radio)
                            .addComponent(pol_radio)
                            .addComponent(inter_radio))
                        .addContainerGap(26, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(details_button)
                        .addGap(18, 18, 18)
                        .addComponent(quitter_button)
                        .addGap(26, 26, 26)
                        .addComponent(pol_radio)
                        .addGap(18, 18, 18)
                        .addComponent(sport_radio)
                        .addGap(26, 26, 26)
                        .addComponent(inter_radio)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton details_button;
    private javax.swing.JRadioButton inter_radio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton pol_radio;
    private javax.swing.JButton quitter_button;
    private javax.swing.ButtonGroup radioGroup;
    private javax.swing.JRadioButton sport_radio;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}