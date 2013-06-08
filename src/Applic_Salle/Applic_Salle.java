/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Applic_Salle;

import libNews.DialTraitement;
import constantes.Categories;
import libNews.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.DefaultListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import process.ThreadNews;

/**
 *
 * @author Ophiran
 */
public class Applic_Salle extends javax.swing.JFrame implements ActionListener, NotifyNewsListener{

    /**
     * Creates new form Applic_Salle
     */
    public static Journaliste journalisteConnecte;
    ImplVerificateur mappingJournaliste = new ImplVerificateur();
    public DefaultComboBoxModel listeNewsATraiter = new DefaultComboBoxModel();
    public DefaultListModel listeNewsInter = new DefaultListModel();
    public DefaultListModel listeNewsSport = new DefaultListModel();
    public DefaultListModel listeNewsPolitique = new DefaultListModel();
    public Vector<News> listeNewsPeople = new Vector<News>();
    public Date date = new Date();
    protected Vector<ThreadNews> poolThreads = new Vector<ThreadNews>();
    protected ListeNewsBean detectNewNews = new ListeNewsBean();
    private Properties propriete;
    
    private Vector<StoreNewsListener> mailinglistStoreNews = new Vector<>();
    private StoringNewsBean listeNews = new StoringNewsBean();
    
    public Applic_Salle() {
        initComponents();
        propriete = new Properties();
        try
        {
            FileReader file = new FileReader(System.getProperty("user.home") + System.getProperty("file.separator") 
                            + "ApplicSalle" + System.getProperty("file.separator") + "PropertiesSalle.properties");
            propriete.load(file);
            file.close();
        	
        }
        catch(FileNotFoundException e)
        {
            try {
                File tmpFile = new File(System.getProperty("user.home") + System.getProperty("file.separator") 
                                        + "ApplicSalle");
                tmpFile.mkdirs();

                FileWriter file = new FileWriter(System.getProperty("user.home") + System.getProperty("file.separator") 
                                + "ApplicSalle" + System.getProperty("file.separator") + "PropertiesSalle.properties");

                propriete.put("propertiesName", "");
                propriete.put("SerializationName", "journalistes");
                propriete.put("RefNumbers", "");
                propriete.put("LogFile",System.getProperty("user.home") + System.getProperty("file.separator") 
                                + "ApplicSalle" + System.getProperty("file.separator") + "ApplicLog.log");
                propriete.put("NewsFile",System.getProperty("user.home") + System.getProperty("file.separator") 
                                + "ApplicSalle" + System.getProperty("file.separator") + "News.dat");
                propriete.store(file, "");

            } catch (IOException e1) {
                    e1.printStackTrace();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        mappingJournaliste.SetPath(System.getProperty("user.home") + System.getProperty("file.separator") 
                                + "ApplicSalle" + System.getProperty("file.separator") + propriete.getProperty("SerializationName"));
        mappingJournaliste.Deserialize();
        
        for(int i = 0 ; i < 5 ; i++){
            poolThreads.add(new ThreadNews(1000, 25678+i));
        }
        for(ThreadNews t:poolThreads){
            t.start();
        }
        ThreadNews.AddNewsListener(detectNewNews);
        
        detectNewNews.AddNotifyNewsListener(this);
        
        rButtonGroup.add(inter_rButton);
        rButtonGroup.add(pol_rButton);
        rButtonGroup.add(sport_rButton);
        
        login_item.addActionListener(this);
        logout_item.addActionListener(this);
        ajouter_button.addActionListener(this);
        traiter_button.addActionListener(this);
        date_item.addActionListener(this);
        about_item.addActionListener(this);
        sup_button.addActionListener(this);
        edit_button.addActionListener(this);
        dRecept_item.addActionListener(this);
        aRecept_item.addActionListener(this);
        nouveau_item.addActionListener(this);
        liste_item.addActionListener(this);
        cat_item.addActionListener(this);
        labelJournaliste.setText("Deconnected");
        date_label.setText("Deconnected");

        
        listInter.setModel(listeNewsInter);
        listPolitique.setModel(listeNewsPolitique);
        listSport.setModel(listeNewsSport);
        
        news_comboBox.setModel(listeNewsATraiter);
        
        listeNews.setPath(propriete.getProperty("NewsFile"));
        mailinglistStoreNews.add(listeNews);
        
        for(News item : listeNews.getNews()) {
        	if(item.isValid()) {
	        	if(item.getType().equals(Categories.Internationale))
	                listeNewsInter.addElement(item);
	            if(item.getType().equals(Categories.Politique))
	                listeNewsPolitique.addElement(item);
	            if(item.getType().equals(Categories.Sport))
	                listeNewsSport.addElement(item);
	            if(item.getType().equals(Categories.People))
	                listeNewsPeople.addElement(item);
        	}
        	else
        		listeNewsATraiter.addElement(item);
        }
    }
    @Override
    public void notifyNewsDetected(NotifyNewsEvent e){
        System.out.println("A news has been received");
        JOptionPane.showMessageDialog(this, "A news has been received", "News received", JOptionPane.INFORMATION_MESSAGE);
        String news[] = e.getNews().split("~");
        String contenu = news[0];
        
        boolean importance;
        if (news[2].equals("OUI"))
            importance = true;
        else
            importance = false;
        
        String type = news[1];
        
        Journaliste auteur = mappingJournaliste.getJournaliste(news[3]);
        
        try{
        	
        	News tmpNews = new News(contenu, auteur, importance, type, "");
            listeNewsATraiter.addElement(tmpNews);
            for(StoreNewsListener item : mailinglistStoreNews) {
            	item.StoreNewsDetected(new StoreNewsEvent(this, tmpNews,true));
            }
            
        }
        catch(NullPointerException npe){
            System.out.println("Le journaliste " + news[3] + " n'est pas enregistre");
        }
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(login_item)) {
            new DialLogin(this,rootPaneCheckingEnabled,mappingJournaliste).setVisible(true);
            if (journalisteConnecte != null) {
                labelJournaliste.setText(journalisteConnecte.getLogin());
                date_label.setText(journalisteConnecte.getFormat().format(date));
                
                
            }
        }
        if(e.getSource().equals(about_item)){
            new DialAbout(this,rootPaneCheckingEnabled).setVisible(true);
        }
        if(journalisteConnecte != null){
            if(e.getSource() == logout_item) {
                journalisteConnecte = null;
                labelJournaliste.setText("Déconnecté");
                date_label.setText("Déconnecté");
            }
            if(e.getSource().equals(ajouter_button)){
            	News tmpNews = new News(addNews_txtField.getText(), journalisteConnecte, false, Categories.Internationale, "");
                listeNewsATraiter.addElement(tmpNews);
                for(StoreNewsListener item : mailinglistStoreNews) {
                	item.StoreNewsDetected(new StoreNewsEvent(this, tmpNews,true));
                }
            }
            if(e.getSource().equals(traiter_button)){
                News previousNews = (News)news_comboBox.getSelectedItem();
                DialTraitement dialTrait = new DialTraitement(this, rootPaneCheckingEnabled,(News)news_comboBox.getSelectedItem(), journalisteConnecte);
                dialTrait.setVisible(true);
                if (dialTrait.isValidate)
                {
                	previousNews.setValidation(true);
                    if(dialTrait.newsEdited.getType().equals(Categories.Internationale))
                        listeNewsInter.addElement(dialTrait.newsEdited);
                    if(dialTrait.newsEdited.getType().equals(Categories.Politique))
                        listeNewsPolitique.addElement(dialTrait.newsEdited);
                    if(dialTrait.newsEdited.getType().equals(Categories.Sport))
                        listeNewsSport.addElement(dialTrait.newsEdited);
                    if(dialTrait.newsEdited.getType().equals(Categories.People))
                        listeNewsPeople.addElement(dialTrait.newsEdited);
                    
                    for(StoreNewsListener item : mailinglistStoreNews) {
                    	item.StoreNewsDetected(new StoreNewsEvent(this, previousNews,false));
                    	item.StoreNewsDetected(new StoreNewsEvent(this, previousNews,true));
                    }
                    listeNewsATraiter.removeElement(previousNews);
                }
            }
            if(e.getSource().equals(sup_button)){
            	
            	for(StoreNewsListener item : mailinglistStoreNews) {
                	item.StoreNewsDetected(new StoreNewsEvent(this, (News)news_comboBox.getSelectedItem() ,false));
                }
            	
                listeNewsATraiter.removeElementAt(news_comboBox.getSelectedIndex());
                
            }
            if(e.getSource().equals(edit_button)){
                News previousNews = new News("", journalisteConnecte, false, Categories.Internationale, "");
                int indexNews = 0;
                String type = Categories.Internationale;
                try
                {
                    if (inter_rButton.isSelected())
                    {
                        indexNews = listInter.getSelectedIndex();
                        type = Categories.Internationale;
                        previousNews = (News)listeNewsInter.elementAt(indexNews);
                    }
                    if (sport_rButton.isSelected())
                    {
                        indexNews = listSport.getSelectedIndex();
                        type = Categories.Sport;
                        previousNews = (News)listeNewsSport.elementAt(indexNews);
                    }
                    if (pol_rButton.isSelected())
                    {
                        indexNews = listPolitique.getSelectedIndex();
                        type = Categories.Politique;
                        previousNews = (News)listeNewsPolitique.elementAt(indexNews);
                    }
                }
                catch(ArrayIndexOutOfBoundsException except)
                {
                    
                }
                
                if (!previousNews.getContenu().equals(""))
                {
                    DialTraitement dialTrait = new DialTraitement(this, rootPaneCheckingEnabled, previousNews, journalisteConnecte);
                    dialTrait.setVisible(true);
                    if (dialTrait.isValidate)
                    {
                        if(dialTrait.newsEdited.getType().equals(Categories.Internationale))
                            listeNewsInter.addElement(dialTrait.newsEdited);
                        if(dialTrait.newsEdited.getType().equals(Categories.Politique))
                            listeNewsPolitique.addElement(dialTrait.newsEdited);
                        if(dialTrait.newsEdited.getType().equals(Categories.Sport))
                            listeNewsSport.addElement(dialTrait.newsEdited);
                        if(dialTrait.newsEdited.getType().equals(Categories.People))
                            listeNewsPeople.addElement(dialTrait.newsEdited);

                        for(StoreNewsListener item : mailinglistStoreNews) {
                        	item.StoreNewsDetected(new StoreNewsEvent(this, previousNews,false));
                        	item.StoreNewsDetected(new StoreNewsEvent(this, previousNews,true));
                        }
                        
                        if(type.equals(Categories.Internationale))
                            listeNewsInter.removeElementAt(indexNews);
                        if(type.equals(Categories.Politique))
                            listeNewsPolitique.removeElementAt(indexNews);
                        if(type.equals(Categories.Sport))
                            listeNewsSport.removeElementAt(indexNews);

                    }
                }
            }
            if(e.getSource().equals(date_item)){
                new DialDate(this,rootPaneCheckingEnabled,journalisteConnecte).setVisible(true);
                date_label.setText(journalisteConnecte.getFormat().format(date));
            }
            if(e.getSource().equals(dRecept_item)){
                for(ThreadNews tn: poolThreads){
                    tn.mustWait = false;
                }
            }
            if(e.getSource().equals(aRecept_item)){
                
                for(ThreadNews tn: poolThreads){
                    tn.mustWait = true;
                }
            }
            
            if(e.getSource().equals(nouveau_item) && journalisteConnecte.getId().equals("Administrateur")){
                DialNewJournaliste dialJourn = new DialNewJournaliste(this, rootPaneCheckingEnabled);
                dialJourn.setVisible(true);
                if(dialJourn.isValidated){
                    mappingJournaliste.AddJournaliste(dialJourn.newJournaliste);
                    mappingJournaliste.Serialize();
                }
            }
            
            if(e.getSource().equals(liste_item)){
                DialListeJournalistes d = new DialListeJournalistes(this, rootPaneCheckingEnabled, mappingJournaliste.JournalisteListe);
                d.setVisible(true);
            }
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

        rButtonGroup = new javax.swing.ButtonGroup();
        news_comboBox = new javax.swing.JComboBox();
        addNews_txtField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jName_label = new javax.swing.JLabel();
        traiter_button = new javax.swing.JButton();
        ajouter_button = new javax.swing.JButton();
        sup_button = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        date_label = new javax.swing.JLabel();
        inter_rButton = new javax.swing.JRadioButton();
        pol_rButton = new javax.swing.JRadioButton();
        sport_rButton = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listInter = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        listPolitique = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        listSport = new javax.swing.JList();
        edit_button = new javax.swing.JButton();
        labelJournaliste = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        util_menu = new javax.swing.JMenu();
        login_item = new javax.swing.JMenuItem();
        logout_item = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        nouveau_item = new javax.swing.JMenuItem();
        liste_item = new javax.swing.JMenuItem();
        connexions_menu = new javax.swing.JMenu();
        dRecept_item = new javax.swing.JMenuItem();
        aRecept_item = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        ports_item = new javax.swing.JMenuItem();
        msg_item = new javax.swing.JMenuItem();
        rech_menu = new javax.swing.JMenu();
        cat_item = new javax.swing.JMenuItem();
        cle_item = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        nPeople_item = new javax.swing.JMenuItem();
        aide_menu = new javax.swing.JMenu();
        date_item = new javax.swing.JMenuItem();
        log_item = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        about_item = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        news_comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        addNews_txtField.setText("Nouvelle news");

        jLabel1.setText("News recues:");

        jLabel2.setText("Ajouter news:");

        jLabel3.setText("Journaliste:");

        traiter_button.setText("Traiter");

        ajouter_button.setText("Ajouter");

        sup_button.setText("Supprimer");

        jLabel4.setText("Nous sommes le:");

        inter_rButton.setText("Internationales");

        pol_rButton.setText("Vie politique");

        sport_rButton.setText("Infos sports");

        listInter.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listInter);

        listPolitique.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(listPolitique);

        listSport.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(listSport);

        edit_button.setText("Editer");

        util_menu.setText("Utilisateurs");

        login_item.setText("Login");
        util_menu.add(login_item);

        logout_item.setText("Logout");
        util_menu.add(logout_item);
        util_menu.add(jSeparator1);

        nouveau_item.setText("Nouveau");
        util_menu.add(nouveau_item);

        liste_item.setText("Liste");
        util_menu.add(liste_item);

        jMenuBar1.add(util_menu);

        connexions_menu.setText("Connexions");

        dRecept_item.setText("Démarrer réceptions");
        connexions_menu.add(dRecept_item);

        aRecept_item.setText("Arrêter réceptions");
        connexions_menu.add(aRecept_item);
        connexions_menu.add(jSeparator2);

        ports_item.setText("Liste des ports");
        connexions_menu.add(ports_item);

        msg_item.setText("Liste des messages recus");
        connexions_menu.add(msg_item);

        jMenuBar1.add(connexions_menu);

        rech_menu.setText("Recherches");

        cat_item.setText("Par catégorie");
        rech_menu.add(cat_item);

        cle_item.setText("par mot clé");
        rech_menu.add(cle_item);
        rech_menu.add(jSeparator3);

        nPeople_item.setText("News people");
        rech_menu.add(nPeople_item);

        jMenuBar1.add(rech_menu);

        aide_menu.setText("Aide");

        date_item.setText("Paramètres date");
        aide_menu.add(date_item);

        log_item.setText("Afficher le log");
        aide_menu.add(log_item);
        aide_menu.add(jSeparator4);

        about_item.setText("A propos");
        aide_menu.add(about_item);

        jMenuBar1.add(aide_menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(news_comboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(addNews_txtField, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(labelJournaliste, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jName_label, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(124, 124, 124)
                                                .addComponent(sport_rButton))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(ajouter_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(traiter_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(55, 55, 55)
                                                .addComponent(sup_button))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(date_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inter_rButton)
                                .addGap(131, 131, 131)
                                .addComponent(pol_rButton)))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(edit_button)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jName_label)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(labelJournaliste, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(date_label, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(news_comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(traiter_button)
                    .addComponent(sup_button))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addNews_txtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(ajouter_button))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inter_rButton)
                    .addComponent(pol_rButton)
                    .addComponent(sport_rButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(edit_button)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(Applic_Salle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Applic_Salle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Applic_Salle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Applic_Salle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Applic_Salle().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aRecept_item;
    private javax.swing.JMenuItem about_item;
    private javax.swing.JTextField addNews_txtField;
    private javax.swing.JMenu aide_menu;
    private javax.swing.JButton ajouter_button;
    private javax.swing.JMenuItem cat_item;
    private javax.swing.JMenuItem cle_item;
    private javax.swing.JMenu connexions_menu;
    private javax.swing.JMenuItem dRecept_item;
    private javax.swing.JMenuItem date_item;
    private javax.swing.JLabel date_label;
    private javax.swing.JButton edit_button;
    private javax.swing.JRadioButton inter_rButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel jName_label;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JLabel labelJournaliste;
    private javax.swing.JList listInter;
    private javax.swing.JList listPolitique;
    private javax.swing.JList listSport;
    private javax.swing.JMenuItem liste_item;
    private javax.swing.JMenuItem log_item;
    private javax.swing.JMenuItem login_item;
    private javax.swing.JMenuItem logout_item;
    private javax.swing.JMenuItem msg_item;
    private javax.swing.JMenuItem nPeople_item;
    private javax.swing.JComboBox news_comboBox;
    private javax.swing.JMenuItem nouveau_item;
    private javax.swing.JRadioButton pol_rButton;
    private javax.swing.JMenuItem ports_item;
    private javax.swing.ButtonGroup rButtonGroup;
    private javax.swing.JMenu rech_menu;
    private javax.swing.JRadioButton sport_rButton;
    private javax.swing.JButton sup_button;
    private javax.swing.JButton traiter_button;
    private javax.swing.JMenu util_menu;
    // End of variables declaration//GEN-END:variables

    
}
