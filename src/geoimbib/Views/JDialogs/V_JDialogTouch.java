package geoimbib.Views.JDialogs;

import geoimbib.Controlers.C_ControlDialogSerie;
import geoimbib.Views.JPanels.V_JPanelMainLeft;
import geoimbib.Views.V_MainWindow;

import javax.swing.*;

/**
 * Created by ravier on 20/02/2016.
 */
public class V_JDialogTouch extends JDialog {

    public V_JDialogTouch(V_MainWindow v_mainWindow, String s, boolean b, V_JPanelMainLeft v_jPanelMainLeft){
        super(v_mainWindow, s, b);

        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);


        initComposants();

        this.setVisible(true);
    }

    private void initComposants() {

    }
}
