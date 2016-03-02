package geoimbib.Controlers;

import geoimbib.Views.JDialogs.V_JDialogNomFusionSerie;
import geoimbib.Views.JPanels.V_JPanelMainLeft;
import geoimbib.Views.JPanels.V_JPanelMainRight;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by ravier on 12/01/2016.
 */
public class C_ControlButtonMainPanelRight implements ActionListener {

    V_JPanelMainRight v_jPanelMainRight = null;
    V_JPanelMainLeft v_jPanelMainLeft = null;

    public C_ControlButtonMainPanelRight(V_JPanelMainRight v_jPanelMainRight) {
        this.v_jPanelMainRight = v_jPanelMainRight;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == v_jPanelMainRight.getButtonLeave()) {
            v_jPanelMainRight.leave();
        }

        else if (e.getSource() == v_jPanelMainRight.getButton1()) {
            if (v_jPanelMainRight.getV_jPanelMainLeft().getJtextfieldFolder().getText().equals(""))
                v_jPanelMainRight.displayWarnBoxPref();
            else
                v_jPanelMainRight.displayJDialogNewSerie();
        }

        else if (e.getSource() == v_jPanelMainRight.getButton2()) {
            if (v_jPanelMainRight.getV_jPanelMainLeft().getJtextfieldFolder().getText().equals(""))
                v_jPanelMainRight.displayWarnBoxPref();
            //Fusion de série

            else{
                List<String> list = v_jPanelMainLeft.getjList().getSelectedValuesList();
                if (list.size()<2){
                    v_jPanelMainRight.displayWarnBoxSizeSelectedItemList();
                }else {
                    V_JDialogNomFusionSerie v_jDialogNomFusionSerie = new V_JDialogNomFusionSerie(v_jPanelMainRight.getV_mainWindow(), "Nom fusion", true, list);
                }
            }


        }

        else if (e.getSource() == v_jPanelMainRight.getButton3()) {
            if (v_jPanelMainRight.getV_jPanelMainLeft().getJtextfieldFolder().getText().equals(""))
                v_jPanelMainRight.displayWarnBoxPref();
            else
                v_jPanelMainRight.displayJDialogNewGraph();
        }

        else if (e.getSource() == v_jPanelMainRight.getButton5()) {
            if (v_jPanelMainRight.getV_jPanelMainLeft().getJtextfieldFolder().getText().equals(""))
                v_jPanelMainRight.displayWarnBoxPref();
            else
                v_jPanelMainRight.displayTouchSet();

        }

    }

    public void setVJPanelLeft(V_JPanelMainLeft VJPanelLeft) {
        this.v_jPanelMainLeft = VJPanelLeft;
    }
}
