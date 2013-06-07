/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applic_News;

import constantes.Categories;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.table.*;
import javax.swing.JOptionPane;
import libNews.*;
import network.*;
import threadsutils.*;



/**
 *
 * @author Ophiran
 * @author Ekym
 */
public class Applic_News extends javax.swing.JFrame implements ActionListener, UtilisateurNombre {

    private NewsCounterBean compteurNews;
    private NetworkStringSender networkSender;
    private int PortEmission = 25678;
    private Properties propriete;
    /**
     * Creates new form Applic_News
     */
    public Applic_News() {
        initComponents();
        
        ThreadRandomGenerator trg = new ThreadRandomGenerator(this , 0, 50, 17, 4);
        trg.start();

        buttonGroup1.add(inter_rButton);
        buttonGroup1.add(people_rButton);
        buttonGroup1.add(politique_rButton);
        buttonGroup1.add(sport_rButton);
        politique_rButton.setSelected(true);
        
        ((DefaultTableModel) news_table.getModel()).setRowCount(0);
        news_table.getColumnModel().getColumn(0).setHeaderValue("News");
        news_table.getColumnModel().getColumn(1).setHeaderValue("Type");
        news_table.getColumnModel().getColumn(2).setHeaderValue("Important ?");
        news_table.getColumnModel().getColumn(3).setHeaderValue("Journaliste");
     
        
        ville_label.setText(Locale.getDefault().getDisplayCountry());
        
        compteurNews = new NewsCounterBean();
        compteurNews.setCounterLabel(counter_label);
        
        enreg_button.addActionListener(this);
        env_button.addActionListener(this);
        annuler_button.addActionListener(this);
        Connect_button.addActionListener(this);
        Deconnect_button.addActionListener(this);
        
        propriete = new Properties();
        try
        {
        	FileReader file = new FileReader(System.getProperty("user.home") + System.getProperty("file.separator") 
        			+ "ApplicNews" + System.getProperty("file.separator") + "PropertiesNews.properties");
        	propriete.load(file);
        	file.close();
        	
        }
        catch(FileNotFoundException e)
        {
        	try {
        		File tmpFile = new File(System.getProperty("user.home") + System.getProperty("file.separator") 
						+ "ApplicNews");
        		tmpFile.mkdirs();
        		
				FileWriter file = new FileWriter(System.getProperty("user.home") + System.getProperty("file.separator") 
						+ "ApplicNews" + System.getProperty("file.separator") + "PropertiesNews.properties");
				
				propriete.put("propertiesName", "");
				propriete.put("SerializationName", "");
				propriete.put("RefNumbers", "");
				propriete.put("LogFile", "ApplicLog.log");
				propriete.store(file, "");
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
        catch(IOException e)
        {
        	
        	e.printStackTrace();
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(enreg_button)) {
            DefaultTableModel dtm = (DefaultTableModel) news_table.getModel();
            Vector<String> newNews = new Vector<>();
            newNews.add(texte_tBox.getText());
            if(sport_rButton.isSelected())
                newNews.add(Categories.Sport);
            else if(politique_rButton.isSelected())
                newNews.add(Categories.Politique);
            else if(people_rButton.isSelected())
                newNews.add(Categories.People);
            else
                newNews.add(Categories.Internationale);
            if(important_cBox.isSelected())
                newNews.add("OUI");
            else
                newNews.add("NON");
            newNews.add(journaliste_tBox.getText());
            dtm.addRow(newNews);
            //Indiquer via un property change ou un setter pour le NewsCounterBean
            compteurNews.setNewsNumber(compteurNews.getNewsNumber()+1);
            
        }
        if(e.getSource().equals(env_button)) {
            try {
            //if(networkSender != null
               //&& (news_table.getSelectedRowCount() == 1)){
                DefaultTableModel dtm = (DefaultTableModel) news_table.getModel();
                String stringtosend = new String();
                stringtosend += "UneVille;";
                for(int i = 0;i<news_table.getColumnCount();i++){
                    stringtosend += dtm.getValueAt(news_table.getSelectedRow(), i);
                    stringtosend += "~";
                }
                networkSender.sendString(stringtosend);
                dtm.removeRow(news_table.getSelectedRow());
                compteurNews.setNewsNumber(compteurNews.getNewsNumber()-1);
            //}
            } catch(Exception ex) {
                System.out.println("ERREUR ENVOIE");
            }
        }
        if(e.getSource().equals(annuler_button)) {
            
        }
        if(e.getSource().equals(Connect_button)) {
            try {
                networkSender = new NetworkStringSender("127.0.0.1", PortEmission);
            } catch (Exception ex)
            {
                ex.printStackTrace(System.out);
            }
        }
        if(e.getSource().equals(Deconnect_button)) {
            networkSender.endSending();
            networkSender = null;
        }
        
                
    }
    
    public String getIdentifiant(){
        return "IDNews1";
    }
    
    public void traiteNombre(int n){
        JOptionPane.showMessageDialog(this, "Alarme: Veuillez fermer l'application", "Probleme sur une ligne",
            JOptionPane.WARNING_MESSAGE,null);
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Connect_button = new javax.swing.JButton();
        Deconnect_button = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ville_label = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        journaliste_tBox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        texte_tBox = new javax.swing.JTextField();
        counter_label = new javax.swing.JLabel();
        politique_rButton = new javax.swing.JRadioButton();
        inter_rButton = new javax.swing.JRadioButton();
        sport_rButton = new javax.swing.JRadioButton();
        people_rButton = new javax.swing.JRadioButton();
        important_cBox = new javax.swing.JCheckBox();
        enreg_button = new javax.swing.JButton();
        env_button = new javax.swing.JButton();
        annuler_button = new javax.swing.JButton();
        news_scrollPane = new javax.swing.JScrollPane();
        news_table = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        evenement_textArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Connect_button.setText("Se connecter");

        Deconnect_button.setText("Se deconnecter");

        jLabel1.setText("Nombre de news envoyees durant cette session:");

        ville_label.setText("jLabel2");

        jLabel3.setText("Journaliste:");

        journaliste_tBox.setText("jTextField1");

        jLabel4.setText("Texte:");

        texte_tBox.setText("jTextField2");

        counter_label.setText("jLabel5");

        politique_rButton.setText("Politique");

        inter_rButton.setText("International");

        sport_rButton.setText("Sports");

        people_rButton.setText("People et ragots");

        important_cBox.setText("Important");

        enreg_button.setText("Enregistrer");

        env_button.setText("Envoyer");

        annuler_button.setText("Annuler");

        news_table.setModel(new javax.swing.table.DefaultTableModel(
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
        news_scrollPane.setViewportView(news_table);

        jLabel6.setText("Log des evenements:");

        evenement_textArea.setColumns(20);
        evenement_textArea.setRows(5);
        jScrollPane2.setViewportView(evenement_textArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(ville_label, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(Connect_button)
                .addGap(24, 24, 24)
                .addComponent(Deconnect_button, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(counter_label))
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(journaliste_tBox, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texte_tBox, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(news_scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(politique_rButton)
                    .addComponent(inter_rButton)
                    .addComponent(sport_rButton)
                    .addComponent(people_rButton)
                    .addComponent(important_cBox, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enreg_button, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(env_button, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(annuler_button, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Connect_button)
                            .addComponent(Deconnect_button)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(counter_label))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(ville_label)))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(journaliste_tBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(texte_tBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(news_scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(politique_rButton)
                        .addGap(7, 7, 7)
                        .addComponent(inter_rButton)
                        .addGap(7, 7, 7)
                        .addComponent(sport_rButton)
                        .addGap(7, 7, 7)
                        .addComponent(people_rButton)
                        .addGap(7, 7, 7)
                        .addComponent(important_cBox))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(enreg_button)
                                .addGap(17, 17, 17)
                                .addComponent(env_button)
                                .addGap(27, 27, 27)
                                .addComponent(annuler_button))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Applic_News.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Applic_News.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Applic_News.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Applic_News.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Applic_News().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Connect_button;
    private javax.swing.JButton Deconnect_button;
    private javax.swing.JButton annuler_button;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel counter_label;
    private javax.swing.JButton enreg_button;
    private javax.swing.JButton env_button;
    private javax.swing.JTextArea evenement_textArea;
    private javax.swing.JCheckBox important_cBox;
    private javax.swing.JRadioButton inter_rButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField journaliste_tBox;
    private javax.swing.JScrollPane news_scrollPane;
    private javax.swing.JTable news_table;
    private javax.swing.JRadioButton people_rButton;
    private javax.swing.JRadioButton politique_rButton;
    private javax.swing.JRadioButton sport_rButton;
    private javax.swing.JTextField texte_tBox;
    private javax.swing.JLabel ville_label;
    // End of variables declaration//GEN-END:variables

    
}
