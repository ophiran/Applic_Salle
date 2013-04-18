/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package libNews;

import Applic_Salle.Journaliste;
import constantes.Categories;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ekimd_000
 */
public class DialTraitement extends javax.swing.JDialog implements ActionListener{

    /**
     * Creates new form DialTraitement
     */
    private News newsEdited;
    private Journaliste journalisteConnecte;
    public DialTraitement(java.awt.Frame parent, boolean modal, News news, Journaliste auteur) {
        super(parent, modal);
        initComponents();
        radioGroup.add(radioPolitique);
        radioGroup.add(radioInternationale);
        radioGroup.add(radioSport);
        radioGroup.add(radioPeople);
        validerButton.addActionListener(this);
        annulerButton.addActionListener(this);
        
        if(news != null) {
            newsEdited = news;
            textArea.setText(newsEdited.getContenu());
            checkBox.setSelected(newsEdited.getImportance());
            keyWords.setText(newsEdited.getMotsCles());
            if(newsEdited.getType().equals(Categories.Internationale))
                radioGroup.setSelected(radioInternationale.getModel(), modal);
            if(newsEdited.getType().equals(Categories.Politique))
                radioGroup.setSelected(radioPolitique.getModel(), modal);  
            if(newsEdited.getType().equals(Categories.Sport))
                radioGroup.setSelected(radioSport.getModel(), modal);
            if(newsEdited.getType().equals(Categories.People))
                radioGroup.setSelected(radioPeople.getModel(), modal);    
        }
        else{
            System.out.println("ERROR: no news referenced");//TODO
            this.dispose();
        }
        if(auteur != null) {
            journalisteConnecte = auteur;
        }
        else{
            System.out.println("ERROR: no journalist referenced");//TODO
            this.dispose();
        }
    }
            
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(validerButton)){
            newsEdited.setContenu(textArea.getText());
            newsEdited.setImportance(checkBox.isSelected());
            newsEdited.setType(radioGroup.getSelection().getActionCommand());
            newsEdited.setMotsCles(keyWords.getText());
            newsEdited.setAuteur(journalisteConnecte);
            this.dispose();
                    
        }
        if(ae.getSource().equals(annulerButton)){
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

        radioGroup = new javax.swing.ButtonGroup();
        radioPolitique = new javax.swing.JRadioButton();
        radioInternationale = new javax.swing.JRadioButton();
        radioSport = new javax.swing.JRadioButton();
        radioPeople = new javax.swing.JRadioButton();
        checkBox = new javax.swing.JCheckBox();
        validerButton = new javax.swing.JButton();
        annulerButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        keyWords = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        radioPolitique.setText("Politique");

        radioInternationale.setText("Internationale");

        radioSport.setText("Sport");

        radioPeople.setText("People");

        checkBox.setText("Important");

        validerButton.setText("Valider");

        annulerButton.setText("Annuler");

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(validerButton)
                .addGap(101, 101, 101)
                .addComponent(annulerButton)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(keyWords)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioPeople)
                            .addComponent(radioSport)
                            .addComponent(radioInternationale)
                            .addComponent(radioPolitique, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkBox))
                        .addGap(30, 30, 30))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkBox)
                        .addGap(18, 18, 18)
                        .addComponent(radioPolitique)
                        .addGap(18, 18, 18)
                        .addComponent(radioInternationale)
                        .addGap(18, 18, 18)
                        .addComponent(radioSport)
                        .addGap(18, 18, 18)
                        .addComponent(radioPeople))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addComponent(keyWords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(validerButton)
                    .addComponent(annulerButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annulerButton;
    private javax.swing.JCheckBox checkBox;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField keyWords;
    private javax.swing.ButtonGroup radioGroup;
    private javax.swing.JRadioButton radioInternationale;
    private javax.swing.JRadioButton radioPeople;
    private javax.swing.JRadioButton radioPolitique;
    private javax.swing.JRadioButton radioSport;
    private javax.swing.JTextArea textArea;
    private javax.swing.JButton validerButton;
    // End of variables declaration//GEN-END:variables


}
