/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applic_Salle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import people.Verificateur;
import people.dialogs.DialogErreur;

/**
 *
 * @author Ophiran
 */
public class DialLogin extends javax.swing.JDialog {

    
    private ImplVerificateur userVerif;
    /**
     * Creates new form DialogLogin
     */
    public DialLogin(java.awt.Frame parent, boolean modal,ImplVerificateur userVerif) {
        super(parent, modal);
        initComponents();
        
        this.userVerif = userVerif;
        Annuler_button.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                MouseClickedAnnuler(evt);
            }
        });
        
        Valider_button.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent evt){
               MouseClickedValider(evt);
           } 
        });
    }

    public void MouseClickedAnnuler(MouseEvent evt)
    {
        this.dispose();
    }
    
    public void MouseClickedValider(MouseEvent evt)
    {
        
        boolean veriflogin = userVerif.verifLogin(utilisateur_TxtBox.getText(),
                motDePasse_TxtBox.getText());
        if(veriflogin)
        {
            Applic_Salle.journalisteConnecte = userVerif.getJournaliste(utilisateur_TxtBox.getText());
            this.dispose();
        }
        else
        {
            new DialogErreur().setVisible(true);
            this.dispose();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        motDePasse_TxtBox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Valider_button = new javax.swing.JButton();
        utilisateur_TxtBox = new javax.swing.JTextField();
        Annuler_button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel2.setText("Mot de passe:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Bienvenue !");

        Valider_button.setText("Valider");

        Annuler_button.setText("Annuler");

        jLabel1.setText("Nom d'utilisateur:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Valider_button)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Annuler_button)
                                .addGap(120, 120, 120))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(motDePasse_TxtBox)
                                .addGap(69, 69, 69))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(utilisateur_TxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))))
            .addGroup(layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel3)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(utilisateur_TxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(motDePasse_TxtBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Valider_button)
                    .addComponent(Annuler_button))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Annuler_button;
    private javax.swing.JButton Valider_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField motDePasse_TxtBox;
    private javax.swing.JTextField utilisateur_TxtBox;
    // End of variables declaration//GEN-END:variables
}
