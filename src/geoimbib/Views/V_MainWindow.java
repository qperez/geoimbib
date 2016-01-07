package geoimbib.Views;

import javax.swing.*;

/**
 * Created by ravier on 07/01/2016.
 */

/*
* V_MainWindow : Class étendue de JFrame : fenêtre principale de l'application
* */

public class V_MainWindow extends JFrame {

    public V_MainWindow(){

        this.setLayout(null);
        this.setTitle("Geoimbib");
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setLayout(null);



        initView(this);
        this.setVisible(true);
    }

    /*
    * Méthode d'initialisation des composants graphiques (JPanels etc)
    * */
    private void initView(V_MainWindow v_mainWindow) {

    }
}
