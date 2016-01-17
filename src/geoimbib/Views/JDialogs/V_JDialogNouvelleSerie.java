package geoimbib.Views.JDialogs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by ravier on 17/01/2016.
 */
public class V_JDialogNouvelleSerie extends JDialog {

    //Jtextfield nom série
    JTextField jTextFieldNomSerie = null;

    //JPanels
    JPanel jpanelButtons = null;
        JButton jButtonnext = null;
        JButton jButtonCancel = null;



    public V_JDialogNouvelleSerie(JFrame parent, String title, boolean modal /*,Model model*/){
        super(parent, title, modal);
        //model initialisation
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        initComposants();
        this.setVisible(true);
    }

    private void initComposants() {
        JPanel jPanelJtextField = new JPanel();
        jTextFieldNomSerie = new JTextField();

        jPanelJtextField.add(new JLabel("Nom de la série :"));
        jPanelJtextField.add(jTextFieldNomSerie, BorderLayout.CENTER);
        Border padding = BorderFactory.createEmptyBorder(50,0,50,0);
        jTextFieldNomSerie.setPreferredSize(new Dimension(150,25));
        jPanelJtextField.setBorder(padding);

        jpanelButtons = new JPanel(new BorderLayout());
            jButtonCancel = new JButton("Annuler");
            jButtonnext = new JButton("Suivant");
        jpanelButtons.add(jButtonCancel, BorderLayout.WEST);
        jpanelButtons.add(jButtonnext, BorderLayout.EAST);

        this.getContentPane().add(jpanelButtons, BorderLayout.SOUTH);
        this.getContentPane().add(jPanelJtextField, BorderLayout.CENTER);
    }

}
