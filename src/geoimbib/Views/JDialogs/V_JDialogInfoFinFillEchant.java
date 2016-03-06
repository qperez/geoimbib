package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;
import geoimbib.Controlers.C_ControlDialogTouch;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ravier on 24/01/2016.
 * Classe &eacute;tendue de JDialog, l'utilisateur choisie si il veut activer la mesure rapide ou non. Et transition avec l'acquisition des mesures
 */
public class V_JDialogInfoFinFillEchant extends JDialog {

    private JFrame parent = null;

    private C_ControlDialogSerie c_controlDialogSerie;
    private C_ControlDialogTouch c_controlDialogTouch;
    private ActionListener aL;
    private JPanel jPanelComposants;
    private JPanel jpanelButtons;
    private JButton jButtonnext;
    private JButton jButtonCancel;
    private JButton jButtonRecap;
    //Jcheckbox
    private JCheckBox jCheckBoxSpeedMesure = null;


    /**
     * Constructeur de la classe V_JDialogInfoFinFillEchant
     * @param parent
     * @param title
     * @param modal
     * @param aL
     */
    public V_JDialogInfoFinFillEchant(JFrame parent, String title, boolean modal, ActionListener aL) {
        super(parent, title, modal);

        this.parent = parent;

        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.aL =aL;
        if (aL instanceof C_ControlDialogSerie){
            this.c_controlDialogSerie = (C_ControlDialogSerie)aL;
            this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);
        }else{
            this.c_controlDialogTouch = (C_ControlDialogTouch) aL;
            this.c_controlDialogTouch.setV_jDialogInfoFillEchant(this);
        }


        initComposants();
        this.setVisible(true);
    }

    private void initComposants() {
        jPanelComposants = new JPanel(new GridLayout(3,1));
        jPanelComposants.add(new JLabel("<html>La saisie des informations concernant les <br> échantillons de la série en cours est terminée.</html>"));
        jPanelComposants.add(new JLabel("Que faire maintenant ?"));

        jCheckBoxSpeedMesure = new JCheckBox("Activer la mesure rapide");
        jPanelComposants.add(jCheckBoxSpeedMesure);

        Border paddingJpanelCompos = BorderFactory.createEmptyBorder(0,40,0,40);
        jPanelComposants.setBorder(paddingJpanelCompos);

        jpanelButtons = new JPanel(new BorderLayout());

        jButtonCancel = new JButton("Annuler");

        jpanelButtons.add(jButtonCancel, BorderLayout.WEST);
        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                V_JDialogConfirmation v_jDialogConfirmation = new V_JDialogConfirmation(parent,
                        "Confirmation",
                        true,
                        getThis()
                        );
            }
        });

        jButtonRecap = new JButton("Modifier série");
        jButtonRecap.setVisible(false);
        jpanelButtons.add(jButtonRecap, BorderLayout.CENTER);

        jButtonnext = new JButton("Commencer l'acquisition balance");
        if (aL instanceof C_ControlDialogSerie)
            jButtonnext.addActionListener(c_controlDialogSerie);
        else
            jButtonnext.addActionListener(c_controlDialogTouch);


        jpanelButtons.add(jButtonnext, BorderLayout.EAST);
        Border paddingJpanelBottom = BorderFactory.createEmptyBorder(0,20,0,20);
        jpanelButtons.setBorder(paddingJpanelBottom);

        this.getContentPane().add(jpanelButtons, BorderLayout.SOUTH);
        this.getContentPane().add(jPanelComposants, BorderLayout.CENTER);
    }

    public JDialog getThis() {return this;}

    public JButton getJButtonNext() {
        return jButtonnext;
    }

    public boolean getStateJCheckBoxFastMesure() {
        return jCheckBoxSpeedMesure.isSelected();
    }
}
