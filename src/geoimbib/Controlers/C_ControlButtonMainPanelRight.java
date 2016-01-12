package geoimbib.Controlers;

import geoimbib.Views.JPanels.V_JPanelMainRight;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ravier on 12/01/2016.
 */
public class C_ControlButtonMainPanelRight implements ActionListener {

    V_JPanelMainRight v_jPanelMainRight = null;

    public C_ControlButtonMainPanelRight(V_JPanelMainRight v_jPanelMainRight) {
        this.v_jPanelMainRight = v_jPanelMainRight;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == v_jPanelMainRight.getButtonLeave()) {
            v_jPanelMainRight.leave();
        }
    }
}
