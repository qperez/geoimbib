package geoimbib.Controlers;

import geoimbib.Views.JDialogs.*;
import geoimbib.Views.JPanels.V_JPanelMainRight;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Zachizac on 27/01/2016.
 */
public class C_ControlDialogGraph implements ActionListener {

    private V_JPanelMainRight v_jPanelMainRight = null;

    private V_JDialogNewGraph v_jDialogNewGraph = null;


    public C_ControlDialogGraph(V_JPanelMainRight v_jPanelMainRight) {
        this.v_jPanelMainRight = v_jPanelMainRight;
    }

    public void actionPerformed(ActionEvent e) {}

    public void setV_jDialogNewGraph(V_JDialogNewGraph v_jDialogNewGraph) {
        this.v_jDialogNewGraph = v_jDialogNewGraph;
    }

}
