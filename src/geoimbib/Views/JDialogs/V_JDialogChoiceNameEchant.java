package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ravier on 18/01/2016.
 */
public class V_JDialogChoiceNameEchant extends JDialog {

    private C_ControlDialogSerie c_controlDialogSerie = null;

    private JPanel jPanelComposants = null;


    private JPanel jpanelButtons = null;
        private JButton jButtonnext = null;
        private JButton jButtonCancel = null;
        private JButton jButtonClearJtextfields = null;

    private JTextField[] jTextFieldNom_tab = null;

    public V_JDialogChoiceNameEchant(JFrame parent, String title, boolean modal, C_ControlDialogSerie c_controlDialogSerie) {
        super(parent, title, modal);

        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        this.c_controlDialogSerie = c_controlDialogSerie;
        this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);

        initComposants();
        this.setVisible(true);
    }

    private void initComposants() {
        final int nbEchant = c_controlDialogSerie.getNbEchant();
        jTextFieldNom_tab = new JTextField[nbEchant];

        jPanelComposants = new JPanel(new GridLayout(nbEchant, 2));

        int paddingJPanel = 210;
        for (int i = 0; i<nbEchant; ++i) {
            paddingJPanel-=15;
            if (paddingJPanel<=0)
                break;
        }

        Border padding = BorderFactory.createEmptyBorder(paddingJPanel, 20, paddingJPanel,20);
        jPanelComposants.setBorder(padding);

        for (int i = 0; i<nbEchant; ++i){
            jPanelComposants.add(new JLabel("Nom echantillon"+i));
            jTextFieldNom_tab[i] = new JTextField("Echantillon"+i);
            jPanelComposants.add(jTextFieldNom_tab[i]);
        }

        JScrollPane listScroller = new JScrollPane(jPanelComposants);
        listScroller.setPreferredSize(new Dimension(500, 500));



        jpanelButtons = new JPanel(new BorderLayout());

        jButtonCancel = new JButton("Annuler");
        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        jButtonnext = new JButton("Suivant");
        jButtonnext.addActionListener(c_controlDialogSerie);

        jButtonClearJtextfields = new JButton("Vider les champs");
        jButtonClearJtextfields.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i<nbEchant; ++i){
                    jTextFieldNom_tab[i].setText("");
                }
            }
        });


        jpanelButtons.add(jButtonCancel, BorderLayout.WEST);
        jpanelButtons.add(jButtonnext, BorderLayout.EAST);
        jpanelButtons.add(jButtonClearJtextfields, BorderLayout.CENTER);
        Border paddingJpanelBottom = BorderFactory.createEmptyBorder(0,50,0,50);
        jpanelButtons.setBorder(paddingJpanelBottom);

        this.getContentPane().add(jpanelButtons, BorderLayout.SOUTH);
        this.getContentPane().add(listScroller, BorderLayout.CENTER);
    }

    /*
    * Getters/Setters
    * */

    public JButton getjButtonnext() {
        return jButtonnext;
    }

    public JTextField[] getjTextFieldNom_tab() {
        return jTextFieldNom_tab;
    }
}
