package geoimbib.Controlers;


import geoimbib.Views.JPanels.V_JPanelMainLeft;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * Created by Zachizac on 20/01/2016.
 */
public class C_ControlButtonMainPanelList implements ListSelectionListener{

    V_JPanelMainLeft v_jPanelMainLeft = null;

    public C_ControlButtonMainPanelList(V_JPanelMainLeft v_jPanelMainLeft) {
        this.v_jPanelMainLeft = v_jPanelMainLeft;
    }

    public void valueChanged(ListSelectionEvent e){
        //&eacute;viter le lancement en double de l'event
        if(e.getValueIsAdjusting())
            return;
        if(this.v_jPanelMainLeft.getjList().getSelectedValue()!=null){
            this.v_jPanelMainLeft.displayChoiceCsv();
        }
    }
}
