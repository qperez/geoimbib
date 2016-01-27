package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogGraph;
import geoimbib.Controlers.C_ControlDialogSerie;
import geoimbib.Views.JPanels.V_JPanelMainLeft;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Zachizac on 27/01/2016.
 */
public class V_JDialogNewGraph extends JDialog{

    private final JFrame parent;

    //JPanels
    JPanel jPanelButton = null;
    V_JPanelMainLeft v_jPanelMainLeft = null;

    //JButton
    JButton jButtonSerie = null;
    JButton jButtonEchantillon = null;

    //Controler
    C_ControlDialogGraph c_controlDialogGraph = null;

    /**
     * Constructeur de jdialog de sélection des données à afficher en graphique
     * @param parent la jframe dans laquel la jdialog va s'afficher
     * @param title le titre de la fenetre
     * @param modal le modal de la fenetre
     * @param c_controlDialogGraph le controler affilié à cette fenêtre
     */
    public V_JDialogNewGraph(JFrame parent, String title, boolean modal, C_ControlDialogGraph c_controlDialogGraph, V_JPanelMainLeft v_jPanelMainLeft){
        super(parent, title, modal);

        this.parent = parent;
        this.v_jPanelMainLeft = v_jPanelMainLeft;

        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.c_controlDialogGraph = c_controlDialogGraph;
        this.c_controlDialogGraph.setV_jDialogNewGraph(this);

        JPanel jPanelTitre = new JPanel(new GridLayout(3,2));
        JLabel selection = new JLabel("Sélectionnez le type de donnée à afficher : ", JLabel.CENTER);
        Border paddingJpanel = BorderFactory.createEmptyBorder(10,0,0,0);
        selection.setBorder(paddingJpanel);

        jPanelTitre.add(selection);

        jPanelButton = new JPanel(new BorderLayout());
        Dimension d = new Dimension(150,30);
        jButtonSerie = new JButton("Série");
        jButtonSerie.addActionListener(c_controlDialogGraph);
        jButtonSerie.setPreferredSize(d);
        jButtonEchantillon = new JButton("Échantillon");
        jButtonEchantillon.addActionListener(c_controlDialogGraph);
        jButtonEchantillon.setPreferredSize(d);
        jPanelButton.add(jButtonSerie, BorderLayout.WEST);
        jPanelButton.add(jButtonEchantillon, BorderLayout.EAST);
        paddingJpanel = BorderFactory.createEmptyBorder(0,50,70,50);
        jPanelButton.setBorder(paddingJpanel);

        this.getContentPane().add(jPanelTitre, BorderLayout.CENTER);
        this.getContentPane().add(jPanelButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    /*
    * Getters / Setters
    * */

    /**
     * Getter du jButton Série
     * @return le JButton série
     */
    public JButton getjButtonSerie(){return jButtonSerie;}

    /**
     * Getter du jButton Echantillon
     * @return le JButton Echantillon
     */
    public JButton getjButtonEchantillon(){return jButtonEchantillon;}

    /**
     * Getter du JPanelLeft qui va permettre de récupérer les données des jlist
     * @return JPanel Main Left de l'appli
     */
    public V_JPanelMainLeft getV_jPanelMainLeft() {
        return v_jPanelMainLeft;
    }

    /**
     * getter du JDialog
     * @return le jDialog
     */
    public JDialog getThis() {
        return this;
    }

}
