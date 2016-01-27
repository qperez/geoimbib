package geoimbib.Controlers;

import geoimbib.Views.JDialogs.*;
import geoimbib.Views.JPanels.V_JPanelMainLeft;
import geoimbib.Views.JPanels.V_JPanelMainRight;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Zachizac on 27/01/2016.
 */
public class C_ControlDialogGraph implements ActionListener {

    private V_JPanelMainRight v_jPanelMainRight = null;

    private V_JDialogNewGraph v_jDialogNewGraph = null;


    /**
     * Constructeur du controleur du jdialogue de graphique
     * @param v_jPanelMainRight
     */
    public C_ControlDialogGraph(V_JPanelMainRight v_jPanelMainRight) {
        this.v_jPanelMainRight = v_jPanelMainRight;
    }

    /**
     * ActionPerformed afin de gérer la sélection des jbutton du jdialog Graphique
     * @param e l'action
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == v_jDialogNewGraph.getjButtonSerie()){
            if(v_jDialogNewGraph.getV_jPanelMainLeft().getjList().getSelectedValue()!=null){
                v_jDialogNewGraph.getV_jPanelMainLeft().getM_generalFunctions().generationSerie(v_jDialogNewGraph.getV_jPanelMainLeft().getjList().getSelectedValue());
            }
            else{
                v_jPanelMainRight.displayWarnJList();
            }
        }

        if(e.getSource() == v_jDialogNewGraph.getjButtonEchantillon()){
            if(v_jDialogNewGraph.getV_jPanelMainLeft().getjListEchantillons().getSelectedValue()!=null) {
                v_jDialogNewGraph.getV_jPanelMainLeft().getM_generalFunctions().generationEchantillon(v_jDialogNewGraph.getV_jPanelMainLeft().getjList().getSelectedValue(), v_jDialogNewGraph.getV_jPanelMainLeft().getjListEchantillons().getSelectedValue());
            }
            else{
                v_jPanelMainRight.displayWarnJList();
            }
        }
    }

    /**
     * Setter de l'attribut jdialogNewGraph
     * @param v_jDialogNewGraph la nouvelle valeur de l'attribut
     */
    public void setV_jDialogNewGraph(V_JDialogNewGraph v_jDialogNewGraph) {
        this.v_jDialogNewGraph = v_jDialogNewGraph;
    }

}
