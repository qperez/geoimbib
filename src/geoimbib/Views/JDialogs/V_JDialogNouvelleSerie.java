package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ravier on 17/01/2016.
 * Classe &eacute;tendue de JDialog, choix du nombre d'&eacute;chantillons et nom de la s&eacute;rie
 */
public class V_JDialogNouvelleSerie extends JDialog {

    private final JFrame parent;
    //Jtextfield
    JTextField jTextFieldNomSerie = null;
    JTextField jTextFieldNombreEchantillons = null;


    //JPanels
    JPanel jpanelButtons = null;
        JButton jButtonnext = null;
        JButton jButtonCancel = null;

    //Controler
    C_ControlDialogSerie c_controlDialogSerie = null;


    /**
     * Constructeur de la classe V_JDialogNouvelleSerie
     * @param parent
     * @param title
     * @param modal
     * @param c_controlDialogSerie
     */
    public V_JDialogNouvelleSerie(JFrame parent, String title, boolean modal, C_ControlDialogSerie c_controlDialogSerie){
        super(parent, title, modal);

        this.parent = parent;

        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.c_controlDialogSerie = c_controlDialogSerie;
        this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);

        initComposants();
        this.setVisible(true);
    }

    private void initComposants() {
        JPanel jPanelJtextField = new JPanel(new GridLayout(2,2));
        jTextFieldNomSerie = new JTextField();
        jTextFieldNombreEchantillons = new JTextField();


        //La s&eacute;rie doi obligatoirement commencer par "Serie"
        jPanelJtextField.add(new JLabel("Nom de la série :"));
        jPanelJtextField.add(jTextFieldNomSerie);

        jPanelJtextField.add(new JLabel("Nombre d'échantillons :"));
        jPanelJtextField.add(jTextFieldNombreEchantillons);




        Border padding = BorderFactory.createEmptyBorder(25,10,25,10);
        jTextFieldNomSerie.setPreferredSize(new Dimension(150,25));
        jPanelJtextField.setBorder(padding);

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
            jButtonnext.addActionListener(c_controlDialogSerie);
        jpanelButtons.add(jButtonCancel, BorderLayout.WEST);
        jpanelButtons.add(jButtonnext, BorderLayout.EAST);
        Border paddingJpanelBottom = BorderFactory.createEmptyBorder(0,50,0,50);
        jpanelButtons.setBorder(paddingJpanelBottom);

        this.getContentPane().add(jpanelButtons, BorderLayout.SOUTH);
        this.getContentPane().add(jPanelJtextField, BorderLayout.CENTER);
    }


    public JButton getjButtonnext(){return jButtonnext;}

    public JTextField getjTextFieldNomSerie(){return jTextFieldNomSerie;}

    public JTextField getjTextFieldNombreEchantillons() { return jTextFieldNombreEchantillons;}


    public JDialog getThis() {
        return this;
    }
}
