package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;
import geoimbib.Controlers.C_ControlDialogTouch;
import geoimbib.Models.M_Carotte;
import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Zachizac on 02/03/2016.
 */
public class V_JDialogRecap extends JDialog {

    private C_ControlDialogSerie c_controlDialogSerie;
    private M_Carotte carotte;
    private final ActionListener aL;
    private C_ControlDialogTouch c_controlDialogTouch;
    private JButton jButtonVal;
    private JButton jButtonGraph;

    private JTextField jTextfieldMoyenne;

    /**
     * Constructeur du jdialog récapitulant les données que l'on vient d'enregistrer
     * @param v_mainWindow la fenetre d'affichage du jdialog
     * @param s le titre du jdialog
     * @param b boolean pour le jdialog
     * @param aL l'action listener pour les boutons
     * @param c la carotte sur laquelle on travaille
     */
    public V_JDialogRecap(V_MainWindow v_mainWindow, String s, boolean b, ActionListener aL, M_Carotte c) {
        super(v_mainWindow, s, b);

        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.carotte = c;
        this.aL = aL;
        if (aL instanceof C_ControlDialogSerie) {
            this.c_controlDialogSerie = (C_ControlDialogSerie) aL;
            this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);
        }else{
            this.c_controlDialogTouch = (C_ControlDialogTouch) aL;
            this.c_controlDialogTouch.setV_jDialogNouvelleSerie(this);
        }

        initComposants();

        this.setVisible(true);
    }

    /**
     * Methode qui initialise les composants du jDialogue du récapitulatif des mesures enregistrées
     */
    private void initComposants() {

        int nbrMesures = this.carotte.getListMesures().size()-1;
        JPanel jPComposants = new JPanel();

        Border paddingJPComposant = BorderFactory.createEmptyBorder(50,0,0,0);
        jPComposants.setBorder(paddingJPComposant);

        Box boxFolder = Box.createVerticalBox();

        Box boxTitle = Box.createHorizontalBox();
        boxTitle.add(new JLabel("Vous venez de rentrer les mesures suivantes :"));

        Box boxMasse = Box.createHorizontalBox();
        boxMasse.add(new JLabel("Masse : " + this.carotte.getListMesures().get(nbrMesures).getMasse()));

        Box boxFrange = Box.createHorizontalBox();
        boxFrange.add(new JLabel("Hauteur frange : " + this.carotte.getListMesures().get(nbrMesures).getHauteurFrangeHumide()));

        Box boxGraphique = Box.createHorizontalBox();
        jButtonGraph = new JButton("Graphique de la carotte");
        boxGraphique.add(jButtonGraph);
        boxGraphique.setBorder(paddingJPComposant);


        boxFolder.add(boxTitle);
        boxFolder.add(boxMasse);
        boxFolder.add(boxFrange);
        boxFolder.add(boxGraphique);

        jPComposants.add(boxFolder);

        JPanel jPanelButtons = new JPanel(new GridLayout(1,1));
        jButtonVal = new JButton("Passer à la carotte suivante");
        jPanelButtons.add(jButtonVal);

        Border paddingjbutton = BorderFactory.createEmptyBorder(0,20,20,20);
        jPanelButtons.setBorder(paddingjbutton);

        jButtonVal.addActionListener(aL);
        jButtonGraph.addActionListener(aL);

        this.getContentPane().add(jPComposants, BorderLayout.CENTER);
        this.getContentPane().add(jPanelButtons, BorderLayout.SOUTH);

    }

    /**
     * Getter du bouton valider
     * @return le bouton valider
     */
    public JButton getButtonVal(){
        return jButtonVal;
    }

    /**
     * Getter du bouton graphique
     * @return le bouton graphique
     */
    public JButton getButtonGraph(){
        return jButtonGraph;
    }

    /**
     * Getter de la carotte sur laquelle on travaille
     * @return la carotte en question
     */
    public M_Carotte getCarotte(){
        return carotte;
    }
}