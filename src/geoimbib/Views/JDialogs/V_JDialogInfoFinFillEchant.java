package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ravier on 24/01/2016.
 */
public class V_JDialogInfoFinFillEchant extends JDialog {

    private JFrame parent = null;

    private final C_ControlDialogSerie c_controlDialogSerie;
    private JPanel jPanelComposants;
    private JPanel jpanelButtons;
    private JButton jButtonnext;
    private JButton jButtonCancel;
    private JButton jButtonRecap;

    public V_JDialogInfoFinFillEchant(JFrame parent, String title, boolean modal, C_ControlDialogSerie c_controlDialogSerie) {
        super(parent, title, modal);

        this.parent = parent;

        this.setSize(500, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        this.c_controlDialogSerie = c_controlDialogSerie;
        this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);

        initComposants();
        this.setVisible(true);
    }

    private void initComposants() {
        jPanelComposants = new JPanel(new GridLayout(2,1));
        jPanelComposants.add(new JLabel("<html>La saisie des informations concernant les <br> échantillons de la série en cours est terminée.</html>"));
        jPanelComposants.add(new JLabel("Que faire maintenant ?"));

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
        jpanelButtons.add(jButtonRecap, BorderLayout.CENTER);

        jButtonnext = new JButton("Commencer l'acqusition balance");
        jpanelButtons.add(jButtonnext, BorderLayout.EAST);
        Border paddingJpanelBottom = BorderFactory.createEmptyBorder(0,20,0,20);
        jpanelButtons.setBorder(paddingJpanelBottom);

        this.getContentPane().add(jpanelButtons, BorderLayout.SOUTH);
        this.getContentPane().add(jPanelComposants, BorderLayout.CENTER);
    }

    public JDialog getThis() {return this;}
}
