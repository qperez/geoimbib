package geoimbib.Views.JPanels;

import geoimbib.Controlers.C_ControlButtonMainPanelLeft;
import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Vector;

/**
 * Created by ravier on 10/01/2016.
 */
public class V_JPanelMainLeft extends JPanel{

    private V_MainWindow v_mainWindow;

    //JList
    private JList<String> jList = null;
    private JList<String> jListEchantillons = null;

    //JPanel changement de répertoire liste de tests
    private JTextField jtextfieldFolder = null;
    private JPanel jpanelFolder = null;

    JButton jButtonPathFolder = null;

    public V_JPanelMainLeft(V_MainWindow v_mainWindow){
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
        * JTextfield changement de répertoire
        * */
            String pathLoaded = loadPathFolderSeries();

            jpanelFolder = new JPanel();
            jpanelFolder.setLayout(new BoxLayout(jpanelFolder, BoxLayout.X_AXIS));
            jtextfieldFolder = new JTextField(pathLoaded);

            jButtonPathFolder = new JButton("...");
            jButtonPathFolder.addActionListener(new C_ControlButtonMainPanelLeft(this));

            this.add(jpanelFolder);
            jpanelFolder.add(jtextfieldFolder);
            jpanelFolder.add(jButtonPathFolder);
            jpanelFolder.setBounds(
                    getWidth()/2 - (int)jpanelFolder.getPreferredSize().getWidth(),
                    getHeight()/4,
                    250,
                    25
            );


        /*
        * JList 1 qui répertorie toute les expériences d'un répertoire
        * */

            //tableau de Strings pour exemple jlist
            String [] tabStringExemple = new String[50];
            for (int i = 0; i<tabStringExemple.length; ++i){
                tabStringExemple[i] = "Série "+i;
            }

            File file = new File(Paths.get(jtextfieldFolder.getText()).toString());
            if (file.exists()) {
                Vector<String> listNameSerie = listNameFolder(file);
                jList = new JList<String>(listNameSerie);
            }
            else
                jList = new JList<String>();
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
        * JList 2 qui affiche tous les échantillons d'une expérience
        * */

            //tableau de Strings pour exemple jlist
            String [] tabStringExemple2 = new String[20];
            for (int i = 0; i<tabStringExemple2.length; ++i){
                tabStringExemple2[i] = "Echantillon "+i;
            }

            jListEchantillons = new JList<String>(tabStringExemple2); //data has type Object[]
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
            savePathPreferenceInFile(chooser.getSelectedFile().getPath());      //on enregiste les préférences dans un fichier pour le récupérer
            File file = new File(chooser.getSelectedFile().getPath());          //Créer un fichier qui sera notre dossier cible
            Vector<String> listNameSerie = listNameFolder(file);                //Récupère le vecteur de séries du dossier
            jList.setListData(listNameSerie);                                   //On actualise la JList avec le nouveau contenu
        }
    }

    private void savePathPreferenceInFile(String pathToSave) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(Paths.get("Res/Preferences/pref.txt").toString())));
            writer.write(pathToSave);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private String loadPathFolderSeries() {
        String path = "";
        String fichier = Paths.get("Res/Preferences/pref.txt").toString();


        try{
            InputStream ips=new FileInputStream(fichier);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            if ((ligne=br.readLine())!=null){
                path=ligne;
            }
            br.close();
        }
        catch (Exception e){
            displayWarnBoxPref();
        }
        return path;
    }


    private Vector<String> listNameFolder(File file) {
        String [] listefichiers;
        Vector<String> arrayListeseries = new Vector<>();
        listefichiers=file.list();

        for(int i=0;i<listefichiers.length;i++){
            if(listefichiers[i].endsWith(".csv")){
                arrayListeseries.add(listefichiers[i].substring(0,listefichiers[i].length()-4));
            }
        }

        return arrayListeseries;
    }

    /*
    * Getters / Setters
    * */
    public JButton getButtonFolder() {
        return jButtonPathFolder;
    }




    /*
    * JDialogs catch exceptions
    * */

    private void displayWarnBoxPref() {
        JOptionPane.showMessageDialog(this.getParent(),
                "Aucun répertoire contenant des séries n'est spécifié",
                "Attention",
                JOptionPane.WARNING_MESSAGE);
    }

}
