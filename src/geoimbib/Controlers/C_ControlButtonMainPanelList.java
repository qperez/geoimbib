package geoimbib.Controlers;

import geoimbib.Views.JPanels.V_JPanelMainLeft;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;
import java.nio.file.Paths;

/**
 * Created by Zachizac on 20/01/2016.
 */
public class C_ControlButtonMainPanelList implements ListSelectionListener{

    V_JPanelMainLeft v_jPanelMainLeft = null;

    public C_ControlButtonMainPanelList(V_JPanelMainLeft v_jPanelMainLeft) {
        this.v_jPanelMainLeft = v_jPanelMainLeft;
    }

    public void valueChanged(ListSelectionEvent e){
        if(this.v_jPanelMainLeft.getjList().getSelectedValue()!=null){
            this.v_jPanelMainLeft.displayChoiceCsv();
            this.v_jPanelMainLeft.getM_generalFunctions().generationSerie(this.v_jPanelMainLeft.getjList().getSelectedValue());
        }
    }
}
