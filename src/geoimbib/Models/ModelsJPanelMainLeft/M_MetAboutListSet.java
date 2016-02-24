package geoimbib.Models.ModelsJPanelMainLeft;

import geoimbib.Views.JPanels.V_JPanelMainLeft;

/**
 * Created by ravier on 24/02/2016.
 */
public class M_MetAboutListSet {

    V_JPanelMainLeft v_jPanelMainLeft = null;

    public M_MetAboutListSet(V_JPanelMainLeft v_jPanelMainLeft) {
        this.v_jPanelMainLeft = v_jPanelMainLeft;
    }

    public String[] getNameEchant() throws Exception {
        int nbEchant = v_jPanelMainLeft.getjListEchantillons().getModel().getSize();
        if (nbEchant <= 0)
            throw new Exception();

        String[] nameEchant= new String[nbEchant];
        for (int i =0; i<nbEchant; ++i){
            nameEchant[i] = v_jPanelMainLeft.getjListEchantillons().getModel().getElementAt(i);
        }
        return nameEchant;
    }
}
