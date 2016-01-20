package geoimbib.Controlers;

import geoimbib.Views.JPanels.V_JPanelMainLeft;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.Vector;

/**
 * Created by ravier on 13/01/2016.
 */
public class C_ControlButtonMainPanelLeft implements ActionListener {

    V_JPanelMainLeft v_jPanelMainLeft = null;

    public C_ControlButtonMainPanelLeft(V_JPanelMainLeft v_jPanelMainLeft) {
        this.v_jPanelMainLeft = v_jPanelMainLeft;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == v_jPanelMainLeft.getButtonFolder()) {
            v_jPanelMainLeft.displayChoiceFolder();
        }
    }
}