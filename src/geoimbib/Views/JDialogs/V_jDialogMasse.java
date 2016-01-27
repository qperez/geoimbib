package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;

import javax.swing.*;

/**
 * Created by ravier on 24/01/2016.
 */
public class V_jDialogMasse extends JDialog {

    private final JFrame parent;
    private final C_ControlDialogSerie c_controlDialogSerie;

    public V_jDialogMasse(JFrame parent, String title, boolean modal, C_ControlDialogSerie c_controlDialogSerie) {
        super(parent, title, modal);

        this.parent = parent;

        this.setSize(500, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.c_controlDialogSerie = c_controlDialogSerie;
       //this.c_controlDialogSerie.setV_jDialogNouvelleSerie(this);

        initComposants();
        this.setVisible(true);
    }

    private void initComposants() {

    }
}
