package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ravier on 17/01/2016.
 */
public class V_JDialogNouvelleSerie extends JDialog {

    //Jtextfield
    JTextField jTextFieldNomSerie = null;
    JTextField jTextFieldNombreEchantillons = null;

    //Jcheckbox
    JCheckBox jCheckBoxSpeedMesure = null;

    //JPanels
    JPanel jpanelButtons = null;
        JButton jButtonnext = null;
        JButton jButtonCancel = null;

    //Controler
    C_ControlDialogSerie c_controlDialogSerie = null;





    public V_JDialogNouvelleSerie(JFrame parent, String title, boolean modal, C_ControlDialogSerie c_controlDialogSerie /*,Model model*/){
        super(parent, title, modal);

        //model initialisation

        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.c_controlDialogSerie = c_controlDialogSerie;
        this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);

        initComposants();
        this.setVisible(true);
    }

    private void initComposants() {
        JPanel jPanelJtextField = new JPanel(new GridLayout(3,2));
        jTextFieldNomSerie = new JTextField();
        jTextFieldNombreEchantillons = new JTextField();
        jCheckBoxSpeedMesure = new JCheckBox("Activer la mesure rapide");

        //La série doi obligatoirement commencer par "Serie"
        jPanelJtextField.add(new JLabel("Nom de la série :"));
        jPanelJtextField.add(jTextFieldNomSerie);

        jPanelJtextField.add(new JLabel("Nombre d'échantillons :"));
        jPanelJtextField.add(jTextFieldNombreEchantillons);

        jPanelJtextField.add(jCheckBoxSpeedMesure);


        Border padding = BorderFactory.createEmptyBorder(25,10,25,10);
        jTextFieldNomSerie.setPreferredSize(new Dimension(150,25));
        jPanelJtextField.setBorder(padding);

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
        jpanelButtons.add(jButtonCancel, BorderLayout.WEST);
        jpanelButtons.add(jButtonnext, BorderLayout.EAST);
        Border paddingJpanelBottom = BorderFactory.createEmptyBorder(0,50,0,50);
        jpanelButtons.setBorder(paddingJpanelBottom);

        this.getContentPane().add(jpanelButtons, BorderLayout.SOUTH);
        this.getContentPane().add(jPanelJtextField, BorderLayout.CENTER);
    }

    /*
    * Getters / Setters
    * */
    public JButton getjButtonnext(){return jButtonnext;}

    public JTextField getjTextFieldNomSerie(){return jTextFieldNomSerie;}

    public JTextField getjTextFieldNombreEchantillons() { return jTextFieldNombreEchantillons;}

    public boolean getStateJCheckBoxFastMesure() {
        return jCheckBoxSpeedMesure.isSelected();
    }



}
