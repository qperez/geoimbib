package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;
import geoimbib.Controlers.C_ControlDialogTouch;
import geoimbib.Models.ModelsJPanelMainLeft.M_MetAboutListSet;
import geoimbib.Views.JPanels.V_JPanelMainLeft;
import geoimbib.Views.V_MainWindow;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ravier on 20/02/2016.
 */
public class V_JDialogTouch extends JDialog {

    private V_MainWindow parent;

    private JCheckBox[] jCheckBoxex = null;
    private String[] nameEchant;

    private JPanel jPanelComposants = null;


    private JPanel jpanelButtons = null;
    private JButton jButtonnext = null;
    private JButton jButtonCancel = null;
    private JButton jButtonClearJtextfields = null;
    private C_ControlDialogTouch c_controlDialogTouch;

    /**
     * Constructeur de la classe V_JDialogTouch
     * @param v_mainWindow
     * @param s
     * @param b
     * @param c_controlDialogTouch
     */
    public V_JDialogTouch(V_MainWindow v_mainWindow, String s, boolean b, C_ControlDialogTouch c_controlDialogTouch){
        super(v_mainWindow, s, b);

        this.parent = v_mainWindow;
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.parent = v_mainWindow;

        this.c_controlDialogTouch= c_controlDialogTouch;
        this.c_controlDialogTouch.setV_jDialogTouch(this);

        try {
            nameEchant = new M_MetAboutListSet(v_mainWindow.getJPanelMainLeft()).getNameEchant();
            initComposants();

            this.setVisible(true);
        } catch (Exception e) {
            v_mainWindow.getJPanelMainLeft().getJDialogNoEchant();
        }


    }

    private void initComposants() {
        final int nbEchant = nameEchant.length;
        jCheckBoxex = new JCheckBox[nbEchant];

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
            jCheckBoxex[i] = new JCheckBox();
            jCheckBoxex[i].setSelected(true);
            jPanelComposants.add(jCheckBoxex[i]);
            jPanelComposants.add(new JLabel(nameEchant[i]));

        }

        JScrollPane listScroller = new JScrollPane(jPanelComposants);
        listScroller.setPreferredSize(new Dimension(500, 500));



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

        jButtonnext = new JButton("Suivant");
        jButtonnext.addActionListener(c_controlDialogTouch);

        jButtonClearJtextfields = new JButton("Vider les champs");
        jButtonClearJtextfields.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i<nbEchant; ++i){
                    jCheckBoxex[i].setSelected(false);
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

    public JDialog getThis() {
        return this;
    }

    public JButton getButonNext() {
        return jButtonnext;
    }
    public String[] getNameEchant(){return nameEchant;}
    public boolean getJCHeckbokAtIndex(int i){ return jCheckBoxex[i].isSelected();}




    public void displayJDialogErrorUpdateSet() {
        JOptionPane.showMessageDialog(this.getParent(),
                "Erreur lors de la modification de votre série",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
    }
}
