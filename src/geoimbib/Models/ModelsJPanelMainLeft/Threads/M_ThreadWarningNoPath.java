package geoimbib.Models.ModelsJPanelMainLeft.Threads;

import geoimbib.Views.JPanels.V_JPanelMainLeft;

/**
 * Created by ravier on 15/01/2016.
 */
public class M_ThreadWarningNoPath extends Thread {
    V_JPanelMainLeft v_jPanelMainLeft = null;

    public M_ThreadWarningNoPath(V_JPanelMainLeft v_jPanelMainLeft) {
        super();
        this.v_jPanelMainLeft = v_jPanelMainLeft;
    }

    @Override
    public void run() {
        super.run();
    }
}
