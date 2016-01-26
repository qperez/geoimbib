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
public class V_JDialogChoiceHautDiam extends JDialog {

    private final JFrame parent;
    private C_ControlDialogSerie c_controlDialogSerie;

    private JTextField[] jTextFieldHaut_tab;
    private JTextField[] jTextFieldDiam_tab;

    private JPanel jPanelComposants;
    private JPanel jpanelButtons;
        private JButton jButtonCancel;
        private JButton jButtonnext;
        private JButton jButtonClearJtextfields;

    public V_JDialogChoiceHautDiam(JFrame parent, String title, boolean modal, C_ControlDialogSerie c_controlDialogSerie) {
        super(parent, title, modal);

        this.parent = parent;

        this.setSize(450, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        this.c_controlDialogSerie = c_controlDialogSerie;
        this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);

        initComposants();
        this.setVisible(true);
    }

    private void initComposants() {
        final int nbEchant = c_controlDialogSerie.getNbEchant();
        jTextFieldHaut_tab = new JTextField[nbEchant];
        jTextFieldDiam_tab = new JTextField[nbEchant];

        jPanelComposants = new JPanel(new GridLayout(nbEchant+1, 3));

        int paddingJPanel = 210;
        for (int i = 0; i<nbEchant; ++i) {
            paddingJPanel-=15;
            if (paddingJPanel<=0)
                break;
        }

        Border padding = BorderFactory.createEmptyBorder(paddingJPanel, 20, paddingJPanel,20);
        jPanelComposants.setBorder(padding);

        jPanelComposants.add(new JLabel("Echantillon"));
        jPanelComposants.add(new JLabel("Hauteur échantillon"));
        jPanelComposants.add(new JLabel("Diamètre échantillon"));

        for (int i = 0; i<nbEchant; ++i){
            jPanelComposants.add(new JLabel(i+""));

            jTextFieldHaut_tab[i] = new JTextField("0");
            jPanelComposants.add(jTextFieldHaut_tab[i]);

            jTextFieldDiam_tab[i] = new JTextField("0");
            jPanelComposants.add(jTextFieldDiam_tab[i]);
        }

        JScrollPane listScroller = new JScrollPane(jPanelComposants);
        listScroller.setPreferredSize(new Dimension(450, 500));



        jpanelButtons = new JPanel(new BorderLayout());

        jButtonCancel = new JButton("Annuler");
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

        jButtonnext = new JButton("Suiv");
        jButtonnext.addActionListener(c_controlDialogSerie);

        jButtonClearJtextfields = new JButton("Vider les champs");
        jButtonClearJtextfields.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i<nbEchant; ++i){
                    jTextFieldHaut_tab[i].setText("");
                    jTextFieldDiam_tab[i].setText("");
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

    public JButton getjButtonnext() {
        return jButtonnext;
    }

    public JTextField[] getjTextFieldHaut_tab() {
        return jTextFieldHaut_tab;
    }

    public JTextField[] getjTextFieldDiam_tab() {
        return jTextFieldDiam_tab;
    }

    public JDialog getThis() {
        return this;
    }
}
