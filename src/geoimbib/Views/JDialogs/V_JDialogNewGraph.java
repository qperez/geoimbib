package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogGraph;
import geoimbib.Controlers.C_ControlDialogSerie;

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
    JButton jButtonSerie = null;
    JButton jButtonCarotte = null;

    //Controler
    C_ControlDialogGraph c_controlDialogGraph = null;

    /**
     * Constructeur de jdialog de sélection des données à afficher en graphique
     * @param parent la jframe dans laquel la jdialog va s'afficher
     * @param title le titre de la fenetre
     * @param modal le modal de la fenetre
     * @param c_controlDialogGraph le controler affilié à cette fenêtre
     */
    public V_JDialogNewGraph(JFrame parent, String title, boolean modal, C_ControlDialogGraph c_controlDialogGraph){
        super(parent, title, modal);

        this.parent = parent;

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
        jButtonSerie = new JButton("Serie");
        jButtonSerie.setPreferredSize(d);
        jButtonCarotte = new JButton("Carotte");
        jButtonCarotte.setPreferredSize(d);
        jPanelButton.add(jButtonSerie, BorderLayout.WEST);
        jPanelButton.add(jButtonCarotte, BorderLayout.EAST);
        paddingJpanel = BorderFactory.createEmptyBorder(0,50,70,50);
        jPanelButton.setBorder(paddingJpanel);

        this.getContentPane().add(jPanelTitre, BorderLayout.CENTER);
        this.getContentPane().add(jPanelButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    /**
     * getter du JDialog
     * @return le jDialog
     */
    public JDialog getThis() {
        return this;
    }

}
