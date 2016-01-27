package geoimbib.Views.JDialogs;

import geoimbib.Models.M_Carotte;
import geoimbib.Models.M_Serie;
import geoimbib.Views.JPanels.V_JPanelMainLeft;
import geoimbib.Views.JPanels.V_JPanelMainRight;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

/**
 * Created by Zachizac on 27/01/2016.
 */
public class V_JDialogGraph extends JDialog {

    private final JFrame parent;

    XYDataset dataset = null;

    //JPanels
    JPanel jPanelButton = null;
    V_JPanelMainRight v_jPanelMainRight = null;

    ValueMarker valuemarker1 = null;

    /**
     * Constructeur de graphique pour une série
     * @param parent la jframe parente
     * @param title le titre du jdialog
     * @param modal le modal
     * @param serie la série à afficher en graphique
     */
    public V_JDialogGraph(JFrame parent, String title, boolean modal, M_Serie serie, V_JPanelMainRight v_jPanelMainRight){
        super(parent, title, modal);

        this.parent = parent;

        this.setSize(640, 480);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        dataset = v_jPanelMainRight.getM_generalFunctionsRight().createDataset(serie);
        createChartPanelSerie();

        this.setVisible(true);
    }

    /**
     * Constructeur de graphique pour une carotte
     * @param parent la jframe parente
     * @param title le titre du jdialog
     * @param modal le modal
     * @param carotte la carotte à afficher en graphique
     */
    public V_JDialogGraph(JFrame parent, String title, boolean modal, M_Carotte carotte, V_JPanelMainRight v_jPanelMainRight){
        super(parent, title, modal);

        this.parent = parent;

        this.setSize(640, 480);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        dataset = v_jPanelMainRight.getM_generalFunctionsRight().createDataset(carotte);
        createChartPanelCarotte();

        this.setVisible(true);
    }

    /**
     * Methode de création du graphique
     */
    public void createChartPanelSerie() {

        String chartTitle = "Graphique";
        String xAxisLabel = "Racine carré du Temps";
        String yAxisLabel = "Varia W/S (g/cm²)";

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        //setContentPane(chartPanel);

        this.getContentPane().add(chartPanel, BorderLayout.CENTER);
    }

    /**
     * Methode de création du graphique
     */
    public void createChartPanelCarotte() {

        String chartTitle = "Graphique";
        String xAxisLabel = "Temps";
        String yAxisLabel = "Masse";

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        //setContentPane(chartPanel);

        this.getContentPane().add(chartPanel, BorderLayout.CENTER);
    }
}
