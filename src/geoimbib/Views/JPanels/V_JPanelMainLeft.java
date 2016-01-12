package geoimbib.Views.JPanels;

import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by ravier on 10/01/2016.
 */
public class V_JPanelMainLeft extends JPanel{

    private V_MainWindow v_mainWindow;

    //JList
    private JList jList = null;
    private JList jListEchantillons = null;

    //JPanel changement de répertoire liste de tests
    private JTextField jtextfieldFolder = null;
    private JPanel jpanelFolder = null;

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
            jpanelFolder = new JPanel();
            jpanelFolder.setLayout(new BoxLayout(jpanelFolder, BoxLayout.X_AXIS));
            jtextfieldFolder = new JTextField(); //rechercher la préférence lors de l'initialisation de la vue

            JButton jButtonPathFolder = new JButton("...");


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

            jList = new JList(tabStringExemple); //data has type Object[]
            jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            //jList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
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

            jListEchantillons = new JList(tabStringExemple2); //data has type Object[]
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


}
