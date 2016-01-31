package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.Keymap;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by ravier on 24/01/2016.
 */
public class V_jDialogMasse extends JDialog {

    private final JFrame parent;
    private final C_ControlDialogSerie c_controlDialogSerie;

    private JLabel jlabelsaisieBalance;
    private JLabel jlabelintValeurBalance;
    private JTextField jtextfieldMasseManu;

    private JPanel jpanelButtons;
    private JButton jbuttonOk;
    private JButton jbuttonRetour;

    private int idCar;

    public V_jDialogMasse(JFrame parent, String title, boolean modal, C_ControlDialogSerie c_controlDialogSerie, int idCar) {
        super(parent, title, modal);

        this.parent = parent;

        this.setSize(500, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.c_controlDialogSerie = c_controlDialogSerie;
        this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);

        this.idCar = idCar;

        initComposants();

        this.setVisible(true);
    }

    private void initComposants() {
        JPanel jpcomposants = new JPanel(new GridLayout(3,2));
        Border paddingjpcomposants = BorderFactory.createEmptyBorder(10,20,10,100);
        jpcomposants.setBorder(paddingjpcomposants);



        jlabelintValeurBalance = new JLabel("0");
        jlabelsaisieBalance = new JLabel("\"Espace\" pour valider :");
        jpcomposants.add(jlabelsaisieBalance);
        jpcomposants.add(jlabelintValeurBalance);

        jpcomposants.add(new JLabel("\"m\" pour une saisie manuelle"));
        jpcomposants.add(new JLabel(""));


        jtextfieldMasseManu = new JTextField("0");
        jtextfieldMasseManu.setVisible(false);
        jbuttonOk = new JButton("Ok");
        jbuttonOk.setVisible(false);
        jbuttonOk.addActionListener(this.c_controlDialogSerie);


        jpcomposants.add(jtextfieldMasseManu);
        jpcomposants.add(jbuttonOk);

        jpanelButtons = new JPanel(new BorderLayout());
        jpanelButtons.setVisible(false);
        jbuttonRetour = new JButton("Annuler man");
        jbuttonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanWindow();
            }
        });
        jpanelButtons.add(jbuttonRetour);
        Border paddingjpanelbutton = BorderFactory.createEmptyBorder(20,100,20,100);
        jpanelButtons.setBorder(paddingjpanelbutton);

        this.getContentPane().add(jpanelButtons, BorderLayout.SOUTH);
        this.getContentPane().add(jpcomposants, BorderLayout.CENTER);

        addControler();
    }

    private void cleanWindow() {
        getContentPane().removeAll();
        initComposants();
        validate();
        this.requestFocus();
    }

    private void addControler() {
        this.addKeyListener(this.c_controlDialogSerie);
    }

    public void setManuel() {
        /*
        * On rend visible les boutons manuels
        * */
        jtextfieldMasseManu.setVisible(true);
        jbuttonOk.setVisible(true);
        jpanelButtons.setVisible(true);
        /*
        * On rend invisible les boutons pour la balance
        * */
        jlabelintValeurBalance.setVisible(false);
        jlabelsaisieBalance.setVisible(false);

        this.removeKeyListener(this.c_controlDialogSerie);
    }

    public JButton getJButtonOkManuel() {
        return jbuttonOk;
    }

    public String getJLabelBalance(){
        return jlabelintValeurBalance.getText();
    }

    public String getJtextfieldValMan() {
        return jtextfieldMasseManu.getText();
    }

    public int getIdCar(){
        return idCar;
    }

}
