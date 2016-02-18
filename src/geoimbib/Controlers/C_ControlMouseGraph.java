package geoimbib.Controlers;

import geoimbib.Views.JDialogs.V_JDialogGraph;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.plot.XYPlot;

import javax.swing.*;


public class C_ControlMouseGraph implements ChartMouseListener {

        private V_JDialogGraph v_jDialogGraph = null;

    /**
     * Constructeur de controlleur du graph avec la souris
     * @param v_jDialogGraph le graph en question
     */
    public C_ControlMouseGraph(V_JDialogGraph v_jDialogGraph) {
            this.v_jDialogGraph = v_jDialogGraph;
        }

    /**
     * Méthode de gestion de l'évenement clique sur un point du graph
     * @param e
     */
    public void chartMouseClicked(ChartMouseEvent e){
        //on gère les threads
        Runnable run = new Runnable() {
            public void run(){
                XYPlot xyPlot2 = v_jDialogGraph.getChartPanel().getChart().getXYPlot();
                System.out.println("Abscisse : " + xyPlot2.getDomainCrosshairValue() + " / Ordonnée : " + xyPlot2.getRangeCrosshairValue());
            }
        };
        SwingUtilities.invokeLater(run);
    }

    @Override
    public void chartMouseMoved (ChartMouseEvent arg0){}
}
