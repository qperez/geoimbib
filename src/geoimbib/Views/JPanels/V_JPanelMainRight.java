package geoimbib.Views.JPanels;

import geoimbib.Controlers.C_ControlButtonMainPanelRight;
import geoimbib.Controlers.C_ControlDialogGraph;
import geoimbib.Controlers.C_ControlDialogSerie;
import geoimbib.Models.ModelsJPanelMainLeft.M_GeneralFunctions;
import geoimbib.Models.ModelsJPanelMainRight.M_GeneralFunctionsRight;
import geoimbib.Views.JDialogs.*;
import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by ravier on 10/01/2016.
 */
public class V_JPanelMainRight extends JPanel {

    private V_MainWindow v_mainWindow;
    private V_JPanelMainLeft v_jPanelMainLeft;
    private M_GeneralFunctionsRight m_generalFunctionsRight = null;

    //Controlers
    private C_ControlButtonMainPanelRight c_controlButtonMainPanelRight = null;
    private C_ControlDialogSerie c_controlDialogSerie = null;
    private C_ControlDialogGraph c_controlDialogGraph = null;

    //JPanel des boutons
    private JPanel jpanelButtons = null;
    private JPanel jpanelLeave = null;

    //JDialogs
    private V_JDialogNouvelleSerie v_jDialogNouvelleSerie = null;
    private V_JDialogNewGraph v_jDialogNewGraph = null;

    //Boutons
    JButton jbutton1 = null;
    JButton jbutton2 = null;
    JButton jbutton3 = null;
    JButton jbutton4 = null;

    JButton jbuttonLeave = null;

    //bool si true -> on reboucle pour acquérir une nouvelle masse de la balance / sinon = mesure rapide (de base true)
    private boolean cont = true;


    public V_JPanelMainRight(V_MainWindow v_mainWindow, V_JPanelMainLeft v_jPanelMainLeft){
        this.v_mainWindow = v_mainWindow;
        m_generalFunctionsRight = new M_GeneralFunctionsRight(v_jPanelMainLeft, this);
        this.c_controlButtonMainPanelRight = new C_ControlButtonMainPanelRight(this);
        this.v_jPanelMainLeft = v_jPanelMainLeft;

        initView();

        this.c_controlDialogSerie = new C_ControlDialogSerie(this);
        this.c_controlDialogGraph = new C_ControlDialogGraph(this);

        initControlers();
    }

    private void initControlers() {
        jbuttonLeave.addActionListener(new C_ControlButtonMainPanelRight(this));

        jbutton1.addActionListener(c_controlButtonMainPanelRight);
        jbutton2.addActionListener(c_controlButtonMainPanelRight);
        jbutton3.addActionListener(c_controlButtonMainPanelRight);
        jbutton4.addActionListener(c_controlButtonMainPanelRight);
    }

    public void initView(){
        //this.setBackground(Color.ORANGE);
        this.setLayout(null);
        this.setSize(new Dimension(v_mainWindow.getWidth()/2, v_mainWindow.getHeight()));
        this.setLocation(v_mainWindow.getJPanelMainLeft().getWidth(), 0);

        this.setBorder(new BevelBorder(BevelBorder.RAISED));

        jpanelButtons = new JPanel(new GridLayout(5,1));
        this.add(jpanelButtons);

        jbutton1 = new JButton("Nouvelle série");
        jpanelButtons.add(jbutton1);
        jbutton2 = new JButton("Fusion de séries");
        jpanelButtons.add(jbutton2);
        jbutton3 = new JButton("Graphique");
        jpanelButtons.add(jbutton3);
        jbutton4 = new JButton("Supprimer");
        jpanelButtons.add(jbutton4);




        jpanelButtons.setBounds(getWidth()/2 - (int)jpanelButtons.getPreferredSize().getWidth()/2,
                getHeight()/2 - (int)jpanelButtons.getPreferredSize().getHeight(),
                200,
                250);

        jpanelLeave = new JPanel(new GridLayout(1,1));
        jbuttonLeave = new JButton("Quitter");
        jpanelLeave.add(jbuttonLeave);


        jpanelLeave.setBounds(getWidth()/2 - (int)jpanelLeave.getPreferredSize().getWidth(),
                getHeight() - (int)jpanelLeave.getPreferredSize().getHeight()*4,
                200,
                50);
        this.add(jpanelLeave);

    }

    public void leave() {
        v_mainWindow.dispose();
    }


    /*
    * Getters and Setters
    * */
    public JButton getButtonLeave() {
        return jbuttonLeave;
    }

    public JButton getButton1() {
        return jbutton1;
    }
    public JButton getButton2() {
        return jbutton2;
    }
    public JButton getButton3() {
        return jbutton3;
    }
    public JButton getButton4() {
        return jbutton4;
    }

    public V_JPanelMainLeft getV_jPanelMainLeft() {
        return v_jPanelMainLeft;
    }

    public M_GeneralFunctionsRight getM_generalFunctionsRight() {
        return m_generalFunctionsRight;
    }

    /**
     * Getter de l'attribut getV_mainWindow()
     * @return v_mainWindow
     */
    public V_MainWindow getV_mainWindow() {
        return v_mainWindow;
    }

    /*
        * JDialogs
        * */
    public void displayJDialogNewSerie() {
        v_jDialogNouvelleSerie = new V_JDialogNouvelleSerie(
                this.v_mainWindow,
                "Nouvelle série",
                true,
                c_controlDialogSerie);
    }

    public void displayJDialogChoiceNameCar() {
        V_JDialogChoiceNameEchant v_jDialogChoiceNameEchant = new V_JDialogChoiceNameEchant(
                this.v_mainWindow,
                "Choix des noms",
                true,
                c_controlDialogSerie);
    }

    public void displayJDialogHautDiam() {
        V_JDialogChoiceHautDiam v_JDialogChoiceHautDiam = new V_JDialogChoiceHautDiam(
                this.v_mainWindow,
                "Hauteur / Diamètres",
                true,
                c_controlDialogSerie);
    }

    public void displayInfoFinFillEchant() {
        V_JDialogInfoFinFillEchant v_JDialogInfoFinFillEchant = new V_JDialogInfoFinFillEchant(
                this.v_mainWindow,
                "",
                true,
                c_controlDialogSerie);
    }

    /**
     * Methode de création de la jdialog du menu graphique
     */
    public void displayJDialogNewGraph(){
        v_jDialogNewGraph = new V_JDialogNewGraph(
                this.v_mainWindow,
                "Nouveau Graphique",
                true,
                c_controlDialogGraph,
                v_jPanelMainLeft);
    }

    public void displayJDialogErrorinputNewSerie() {
        JOptionPane.showMessageDialog(this.getParent(),
                "Veillez à remplir correctement les champs",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
    }

    public void displayWarnBoxPref() {
        JOptionPane.showMessageDialog(this.getParent(),
                "Aucun répertoire contenant des séries n'est spécifié",
                "Attention",
                JOptionPane.WARNING_MESSAGE);
    }

    public void displayValidateBox() {
        JOptionPane.showMessageDialog(this.getParent(),
                "Votre série a été crée correctement",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Methode qui ouvre un jdialog si aucun objet n'est sélectionnée dans la liste et que l'on veut tout de même y accéder
     */
    public void displayWarnJList(){
        JOptionPane.showMessageDialog(this.getParent(),
                "Aucun objet de ce type sélectionné dans les listes",
                "Attention",
                JOptionPane.WARNING_MESSAGE);
    }


    public void loopAcquisitionMasse(int nbEchant, boolean mRapide, String[] nomEchant) {
        cont = true;
        while (cont) {
            for (int i = 0; i<nbEchant; ++i ){
                V_jDialogMasse v_jDialogMasse = new V_jDialogMasse(
                        this.v_mainWindow,
                        "Acquisition masse : " + nomEchant[i],
                        true,
                        c_controlDialogSerie,
                        i
                );

                V_JDialogFrange v_jDialogFrange = new V_JDialogFrange(
                        this.v_mainWindow,
                        "Frange humide : "+ nomEchant[i],
                        true,
                        c_controlDialogSerie,
                        i
                );

                V_JDialogHeure v_jDialogHeure = new V_JDialogHeure(
                        this.v_mainWindow,
                        "Heure",
                        true,
                        c_controlDialogSerie,
                        i
                );
            }
            System.out.println(c_controlDialogSerie.toString());
            if (!mRapide) {
                V_jDialogContinueGetMasseOrNot v_jDialogContinueGetMasseOrNot = new V_jDialogContinueGetMasseOrNot(
                        this.v_mainWindow,
                        "Continuer ?",
                        true,
                        this
                );
            }
            else
                cont = false;

            displayValidateBox();
        }
    }


    public void setBooleanContinue(boolean cont) {
        this.cont = cont;
    }
}
