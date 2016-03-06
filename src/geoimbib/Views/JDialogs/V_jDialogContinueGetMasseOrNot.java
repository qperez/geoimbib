package geoimbib.Views.JDialogs;

import geoimbib.Views.JPanels.V_JPanelMainRight;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ravier on 27/01/2016.
 * Classe &eacute;tendue de JDialog, choix de l'utilisateur pour savoir si il continue la prise de mesure ou non
 */
public class V_jDialogContinueGetMasseOrNot extends JDialog {

    private V_JPanelMainRight jPanelParent = null;


    /**
     * Constructeur de la classe V_jDialogContinueGetMasseOrNot
     * @param parent
     * @param title
     * @param modal
     * @param jPanelParent
     */
    public V_jDialogContinueGetMasseOrNot(JFrame parent, String title, boolean modal, V_JPanelMainRight jPanelParent) {
        super(parent, title, modal);

        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        this.jPanelParent = jPanelParent;

        initComposants();
        this.setVisible(true);
    }

    private void initComposants() {
        JPanel jPanelComposants = new JPanel(new BorderLayout());
        jPanelComposants.add(new JLabel("Voulez vous continuer à prélever la masse pour cette série ?"), BorderLayout.CENTER);

        Border paddingJpanelCompos = BorderFactory.createEmptyBorder(0,45,20,45);
        jPanelComposants.setBorder(paddingJpanelCompos);

        JPanel jpanelButtons = new JPanel(new BorderLayout());
        JButton jButtonYes = new JButton("Oui, recommencer");
        jButtonYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JButton jButtonNo = new JButton("Non, terminer");
        jButtonNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanelParent.setBooleanContinue(false);
                dispose();
            }
        });

        jpanelButtons.add(jButtonNo, BorderLayout.WEST);
        jpanelButtons.add(jButtonYes, BorderLayout.EAST);


        Border paddingJpanelBottom = BorderFactory.createEmptyBorder(0,20,0,20);
        jpanelButtons.setBorder(paddingJpanelBottom);

        this.getContentPane().add(jpanelButtons, BorderLayout.SOUTH);
        this.getContentPane().add(jPanelComposants, BorderLayout.CENTER);

    }
}
