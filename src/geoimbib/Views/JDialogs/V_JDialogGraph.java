package geoimbib.Views.JDialogs;

import geoimbib.Models.M_Carotte;
import geoimbib.Models.M_Serie;
import geoimbib.Views.JPanels.V_JPanelMainLeft;
import geoimbib.Views.JPanels.V_JPanelMainRight;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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
    XYDataset dataset2 = null;

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

        dataset = v_jPanelMainRight.getM_generalFunctionsRight().createDatasetMasse(carotte);
        dataset2 = v_jPanelMainRight.getM_generalFunctionsRight().createDatasetHauteur(carotte);
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

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

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
        String xAxisLabel = "Temps (h)";
        String yAxisLabel = "Masse";

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset);

        XYPlot plot = chart.getXYPlot();

        NumberAxis axis1 = new NumberAxis("Masse (g)");
        axis1.setFixedDimension(100);
        axis1.setAutoRangeIncludesZero(false);
        axis1.setLabelPaint(Color.black);
        axis1.setTickLabelPaint(Color.black);
        plot.setRangeAxis(0, axis1);
        plot.setRangeAxisLocation(0, AxisLocation.BOTTOM_OR_LEFT);
        plot.setDataset(0, dataset);
        plot.mapDatasetToRangeAxis(0, 0);

        NumberAxis axis2 = new NumberAxis("Hauteur Fange (cm)");
        axis2.setFixedDimension(0);
        axis2.setAutoRangeIncludesZero(true);
        plot.setRangeAxis(1, axis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);

        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        renderer1.setSeriesPaint(0, Color.BLUE);
        renderer2.setSeriesPaint(0, Color.RED);
        plot.setRenderer(0, renderer1);
        plot.setRenderer(1, renderer2);


        ChartPanel chartPanel = new ChartPanel(chart);
        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        //setContentPane(chartPanel);

        this.getContentPane().add(chartPanel, BorderLayout.CENTER);
    }
}