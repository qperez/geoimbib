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
public class V_JDialogConfirmation extends JDialog {

    private JDialog jDialogParent = null;

    public V_JDialogConfirmation(JFrame parent, String title, boolean modal, JDialog jDialogParent) {
        super(parent, title, modal);

        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        this.jDialogParent = jDialogParent;

        initComposants();
        this.setVisible(true);
    }

    private void initComposants() {
        JPanel jPanelComposants = new JPanel(new BorderLayout());
        jPanelComposants.add(new JLabel("Arrêter la série en cours ?"), BorderLayout.CENTER);

        Border paddingJpanelCompos = BorderFactory.createEmptyBorder(0,50,20,50);
        jPanelComposants.setBorder(paddingJpanelCompos);

        JPanel jpanelButtons = new JPanel(new BorderLayout());
        JButton jButtonYes = new JButton("Oui");
        jButtonYes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jDialogParent.dispose();
                dispose();
            }
        });
        JButton jButtonNo = new JButton("Non");
        jButtonNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
