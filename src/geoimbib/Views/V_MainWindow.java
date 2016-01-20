package geoimbib.Views;

import geoimbib.Views.JPanels.V_JPanelMain;
import geoimbib.Views.JPanels.V_JPanelMainLeft;
import geoimbib.Views.JPanels.V_JPanelMainRight;

import javax.swing.*;

/**
 * Created by ravier on 07/01/2016.
 */

/*
* V_MainWindow : Class étendue de JFrame : fenêtre principale de l'application
* */

public class V_MainWindow extends JFrame {

    //JPanels
    private V_JPanelMainLeft v_jPanelMainLeft = null;
    private V_JPanelMainRight v_jPanelMainRight = null;

    public V_MainWindow(){

        this.setLayout(null);
        this.setTitle("Geoimbib");
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setLayout(null);



        initView(this);
        this.setVisible(true);
    }

    /*
    * Méthode d'initialisation des composants graphiques (1 jpanel principal puis 2 jpanels (partie gauche, partie droite)
    * */
    private void initView(V_MainWindow v_mainWindow) {
        /*
        * Initialisation du panel principal
        * */
        V_JPanelMain v_jPanelMain = new V_JPanelMain();
        this.setContentPane(v_jPanelMain);

        /*
        * Initialisation du panel de gauche
        * */
        v_jPanelMainLeft = new V_JPanelMainLeft(this);
        v_jPanelMain.add(v_jPanelMainLeft);

        /*
        * Initialisation du panel de droite
        * */
        v_jPanelMainRight = new V_JPanelMainRight(this, v_jPanelMainLeft);
        v_jPanelMain.add(v_jPanelMainRight);
    }

    public V_JPanelMainLeft getJPanelMainLeft() {
        return v_jPanelMainLeft;
    }
    public V_JPanelMainRight getJPanelMainRight() {
        return v_jPanelMainRight;
    }
}
