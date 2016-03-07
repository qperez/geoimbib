package geoimbib.Views.JPanels;

import geoimbib.Controlers.C_ControlButtonMainPanelLeft;
import geoimbib.Controlers.C_ControlButtonMainPanelList;
import geoimbib.Models.ModelsJPanelMainLeft.M_GeneralFunctions;
import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Vector;

/**
 * Created by ravier on 10/01/2016.
 * Classe &eacute;tendue de JPanel, JPanel gauche de l'application
 */
public class V_JPanelMainLeft extends JPanel{
    private M_GeneralFunctions m_generalFunctions = null;

    private V_MainWindow v_mainWindow;

    //JList
    private JList<String> jList = null;
    private JList<String> jListEchantillons = null;

    //JPanel changement de répertoire liste de tests
    private JTextField jtextfieldFolder = null;
    private JPanel jpanelFolder = null;

    //Controler
    private C_ControlButtonMainPanelLeft c_controlButtonMainPanelLeft = null;

    JButton jButtonPathFolder = null;

    /**
     * Constructeur de la classe V_JPanelMainLeft
     * @param v_mainWindow
     */
    public V_JPanelMainLeft(V_MainWindow v_mainWindow){
        this.c_controlButtonMainPanelLeft = new C_ControlButtonMainPanelLeft(this);
        m_generalFunctions = new M_GeneralFunctions(this);
        this.v_mainWindow = v_mainWindow;
        initView();
    }

    public void initView(){
        //this.setBackground(Color.cyan);
        this.setLayout(null);
        this.setSize(new Dimension(v_mainWindow.getWidth()/2, v_mainWindow.getHeight()));
        this.setLocation(0,0);

        this.setBorder(new BevelBorder(BevelBorder.RAISED));


        /*
        * JTextfield changement de r&eacute;pertoire
        * */
            String pathLoaded = m_generalFunctions.loadPathFolderSeries();

            jpanelFolder = new JPanel();

            jpanelFolder.setLayout(new BoxLayout(jpanelFolder, BoxLayout.X_AXIS));

            Box boxFolder = Box.createVerticalBox();

            Box boxTitle = Box.createHorizontalBox();
            boxTitle.add(new JLabel("Dossier de travail : "));

            Box boxFolderField = Box.createHorizontalBox();

            jtextfieldFolder = new JTextField(pathLoaded);

            jButtonPathFolder = new JButton("...");
            jButtonPathFolder.addActionListener(new C_ControlButtonMainPanelLeft(this));

            boxFolderField.add(jtextfieldFolder);
            boxFolderField.add(jButtonPathFolder);

            boxFolder.add(boxTitle);
            boxFolder.add(boxFolderField);

            jpanelFolder.add(boxFolder);

            jpanelFolder.setBounds(
                this.getWidth()/2 - 125,
                this.getHeight()/5,
                250,
                43
            );

            this.add(jpanelFolder);



        /*
        * JList 1 qui r&eacute;pertorie toute les exp&eacute;riences d'un r&eacute;pertoire
        * */

            //tableau de Strings pour exemple jlist
            String [] tabStringExemple = new String[50];
            for (int i = 0; i<tabStringExemple.length; ++i){
                tabStringExemple[i] = "Série "+i;
            }

            File file = new File(Paths.get(jtextfieldFolder.getText()).toString());

            if (file.exists()) {
                Vector<String> listNameSerie = m_generalFunctions.listNameFolder(file);
                jList = new JList<String>(listNameSerie);
            }
            else
                jList = new JList<String>();

            jList.addListSelectionListener(new C_ControlButtonMainPanelList(this));

            jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            jList.setVisibleRowCount(-1);
            JScrollPane listScroller = new JScrollPane(jList);
            listScroller.setPreferredSize(new Dimension(150, 250));


            this.add(listScroller);

            listScroller.setBounds(getWidth()/4 - (int)listScroller.getPreferredSize().getWidth()/2,
                    getHeight()/2 - (int)listScroller.getPreferredSize().getHeight()/2,
                    150,
                    250);

        /*
        * JList 2 qui affiche tous les &eacute;chantillons d'une exp&eacute;rience
        * */

            jListEchantillons = new JList<String>(); //data has type Object[]

            jListEchantillons.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            //jList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            jListEchantillons.setVisibleRowCount(-1);
            JScrollPane listScroller2 = new JScrollPane(jListEchantillons);
            listScroller2.setPreferredSize(new Dimension(150, 250));

            this.add(listScroller2);

            listScroller2.setBounds(getWidth()/2 + (int)listScroller2.getPreferredSize().getWidth()/2,
                    getHeight()/2 - (int)listScroller2.getPreferredSize().getHeight()/2,
                    150,
                    250);
    }


    /*
    * Affiche le choice folder et met &agrave; jour le jtextfield en fonction du dossier s&eacute;lectionn&eacute;
    * (R&eacute;cup&egrave;re les donn&eacute;es dans la classe model "M_GeneralFunctions.java"
    * */
    public void displayChoiceFolder() {
        JFileChooser chooser = new JFileChooser();

        if (jtextfieldFolder.getText().equals("")) {
            chooser.setCurrentDirectory(new File(Paths.get("/").toString()));
        } else{
            chooser.setCurrentDirectory(new File(Paths.get(jtextfieldFolder.getText()).toString()));
        }
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            jtextfieldFolder.setText(chooser.getSelectedFile().getPath());
            m_generalFunctions.savePathPreferenceInFile(chooser.getSelectedFile().getPath());      //on enregiste les pr&eacute;f&eacute;rences dans un fichier pour le r&eacute;cup&eacute;rer
            File file = new File(chooser.getSelectedFile().getPath());                              //Cr&eacute;er un fichier qui sera notre dossier cible
            Vector<String> listNameSerie = m_generalFunctions.listNameFolder(file);                //R&eacute;cup&egrave;re le vecteur de s&eacute;ries du dossier
            jList.setListData(listNameSerie);                                                       //On actualise la JList avec le nouveau contenu
        }
    }

    /*
    * Affiche les csv de la s&eacute;rie s&eacute;lectionn&eacute;e
     */
    public void displayChoiceCsv(){
        File file2 = new File(Paths.get(jtextfieldFolder.getText()).toString() +File.separator+ Paths.get(jList.getSelectedValue()).toString());
        Vector<String> listNameCsv = m_generalFunctions.listNameCsv(file2);
        jListEchantillons.setListData(listNameCsv);
    }


    /*
    * Getters / Setters
    * */
    public JButton getButtonFolder() {
        return jButtonPathFolder;
    }

    public JTextField getJtextfieldFolder() {
        return jtextfieldFolder;
    }

    public M_GeneralFunctions getM_generalFunctions() {
        return m_generalFunctions;
    }

    public JList<String> getjList() {
        return jList;
    }

    /**
     * Getter de la jList d'échantillon
     * @return la jlist d'échantillon du panneau gauche
     */
    public JList<String> getjListEchantillons() {
        return jListEchantillons;
    }

    public void getJDialogNoEchant() {
        JOptionPane.showMessageDialog(this.getParent(),
                    "Aucune série séléctionnée",
                    "Attention",
                    JOptionPane.WARNING_MESSAGE);
    }

    public void updateJList() {
        Vector<String> listNameSerie = m_generalFunctions.listNameFolder(new File(getJtextfieldFolder().getText()));                //Récupère le vecteur de séries du dossier
        jList.setListData(listNameSerie);
    }

    public String getPathCurrentSet() {
        return getJtextfieldFolder().getText() + File.separator + getjList().getSelectedValue();
    }
}
