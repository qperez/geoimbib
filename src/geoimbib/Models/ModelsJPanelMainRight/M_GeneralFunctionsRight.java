package geoimbib.Models.ModelsJPanelMainRight;

import geoimbib.Models.M_Carotte;
import geoimbib.Models.M_Mesure;
import geoimbib.Models.M_Serie;
import geoimbib.Views.JPanels.V_JPanelMainLeft;
import geoimbib.Views.JPanels.V_JPanelMainRight;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
/*import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;*/

import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Created by Zachizac on 27/01/2016.
 */
public class M_GeneralFunctionsRight {

    V_JPanelMainLeft v_jPanelMainLeft = null;
    V_JPanelMainRight v_jPanelMainRight = null;

    public M_GeneralFunctionsRight(V_JPanelMainLeft v_jPanelMainLeft, V_JPanelMainRight v_jPanelMainRight) {
        this.v_jPanelMainLeft = v_jPanelMainLeft;
        this.v_jPanelMainRight = v_jPanelMainRight;
    }

    /**
     * Methode qui génère la serie en fonction de la série sélectionnée dans le panelMainLeft de gauche
     * @param serieSelected le nom du dossier contenant les carottes
     */
    public M_Serie generationSerie(String serieSelected){
        M_Serie serie = null;
        M_Carotte carotte = null;
        M_Mesure mesure = null;
        ArrayList<M_Mesure> listMesures;
        ArrayList<M_Carotte> listCarotte = new ArrayList<M_Carotte>();
        String nomSerie, temp [], mesuresCarotte[], datePremCarotte, nomCarotte, ligne;
        Double diametre, surface, longueur, hauteurFange, masse;
        Calendar dateHeure = null;
        int nbrMesuresCarottes = 0;

        temp = serieSelected.split("_");
        nomSerie = temp[1];

        File directory = new File(Paths.get(v_jPanelMainLeft.getJtextfieldFolder().getText()).toString() + File.separator + serieSelected);
        File[] files = directory.listFiles();
        for(int i = 0 ; i < files.length ; i++) {
            if (files[i].toString().endsWith(".csv")) {
                try {
                    // on reference le fichier dans lequel il y a les données d'une carotte
                    nomCarotte = files[i].getName();
                    InputStream ips = new FileInputStream(files[i]);
                    InputStreamReader ipsr = new InputStreamReader(ips);
                    BufferedReader br = new BufferedReader(ipsr);
                    //on gère d'abord la première ligne sensée contenir les intitulés des colonnes et le diametre/surface et longueur de la carotte
                    if ((ligne = br.readLine()) != null) {
                        temp = ligne.split(";");
                        diametre = Double.parseDouble(temp[0]);
                        surface = Double.parseDouble(temp[1]);
                        longueur = Double.parseDouble(temp[2]);
                    } else {
                        System.err.println("un des échantillons de la série ne contient aucune donnée");
                        return null;
                    }
                    listMesures = new ArrayList<M_Mesure>();
                    nbrMesuresCarottes = 0;
                    while ((ligne = br.readLine()) != null) {
                        try {
                            temp = ligne.split(";");
                            dateHeure = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                            dateHeure.setTime(sdf.parse(temp[4].concat(" " + temp[5])));
                            hauteurFange = Double.parseDouble(temp[7]);
                            masse = Double.parseDouble(temp[6]);
                            mesure = new M_Mesure(dateHeure, hauteurFange, masse);
                            listMesures.add(mesure);
                            nbrMesuresCarottes++;
                        } catch (ParseException e) {
                            System.err.println("Problème de format de date");
                            e.printStackTrace();
                        }
                    }
                    carotte = new M_Carotte(nomCarotte, diametre, longueur, listMesures);
                    listCarotte.add(carotte);
                    br.close();
                    ipsr.close();
                    ips.close();

                } catch (NumberFormatException e) {
                    System.err.println("Problème dans le fichier : un argument n'est pas du bon format");
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    System.err.println("Fichier introuvable");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.err.println("Problème lors de la lecture du fichier");
                    e.printStackTrace();
                }
            }
        }

        //Je récupère la date de la mesure la plus ancienne des .csv afin d'établir la date de la série (logiquement égale a la date de la première expérience)
        try{
            dateHeure = Calendar.getInstance();
            //pour stocker la date de la liste (sinon j'utilisais deux fois next() avec le if... et donc j'avançais de deux mesures à chaque fois)
            Calendar tempCalendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            dateHeure.setTime(sdf.parse("01/01/2042 00:00"));

            Iterator<M_Carotte> iter = listCarotte.iterator();
            while(iter.hasNext()){
                Iterator<M_Mesure> iter2 = iter.next().getListMesures().iterator();
                while(iter2.hasNext()){
                    tempCalendar = iter2.next().getDateHeure();
                    if(tempCalendar.before(dateHeure)){
                        dateHeure = tempCalendar;
                    }
                }
            }
        }catch (ParseException e) {
            System.err.println("Problème de format de date");
            e.printStackTrace();
        }

        serie = new M_Serie(nomSerie, nbrMesuresCarottes, listCarotte, dateHeure);
        System.out.println(serie.toString());
        return serie;
    }

    /**
     * Methode qui génère un échantillon en fonction de l'échantillon sélectionné dans le panelMainLeft de gauche
     * @param echantillonSelected le nom du csv de l'échantillon
     * @param serieSelected le nom de la série qui contient l'échantillon
     */
    public M_Carotte generationEchantillon(String serieSelected, String echantillonSelected){
        M_Carotte carotte = null;
        M_Mesure mesure = null;
        ArrayList<M_Mesure> listMesures;
        String temp [], mesuresCarotte[], nomCarotte, ligne;
        Double diametre, surface, longueur, hauteurFange, masse;
        Calendar dateHeure = null;

        File file = new File(Paths.get(v_jPanelMainLeft.getJtextfieldFolder().getText()).toString() + File.separator + serieSelected + File.separator + echantillonSelected);
        try {
            // on reference le fichier dans lequel il y a les données d'une carotte
            nomCarotte = file.getName();
            InputStream ips = new FileInputStream(file);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            //on gère d'abord la première ligne sensée contenir les intitulés des colonnes et le diametre/surface et longueur de la carotte
            if ((ligne = br.readLine()) != null) {
                temp = ligne.split(";");
                diametre = Double.parseDouble(temp[0]);
                surface = Double.parseDouble(temp[1]);
                longueur = Double.parseDouble(temp[2]);
            } else {
                System.err.println("L'échantillon ne contient aucune donnée");
                return null;
            }
            listMesures = new ArrayList<M_Mesure>();
            while ((ligne = br.readLine()) != null) {
                try {
                    temp = ligne.split(";");
                    dateHeure = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                    dateHeure.setTime(sdf.parse(temp[4].concat(" " + temp[5])));
                    hauteurFange = Double.parseDouble(temp[7]);
                    masse = Double.parseDouble(temp[6]);
                    mesure = new M_Mesure(dateHeure, hauteurFange, masse);
                    listMesures.add(mesure);
                } catch (ParseException e) {
                    System.err.println("Problème de format de date");
                    e.printStackTrace();
                }
            }
            carotte = new M_Carotte(nomCarotte, diametre, longueur, listMesures);
            br.close();
            ipsr.close();
            ips.close();

        } catch (NumberFormatException e) {
            System.err.println("Problème dans le fichier : un argument n'est pas du bon format");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("Fichier introuvable");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Problème lors de la lecture du fichier");
            e.printStackTrace();
        }

        System.out.println(carotte.toString());
        return carotte;

    }

    /**
     * Methode de création des valeurs à afficher sur le graphique
     * @param serie la série à afficher
     * @return la dataset du graphique
     */
    public XYDataset createDataset(M_Serie serie){
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Object 1");
        XYSeries series2 = new XYSeries("Object 2");

        series1.add(1.0, 2.0);
        series1.add(2.0, 3.0);
        series1.add(3.0, 2.5);
        series1.add(3.5, 2.8);
        series1.add(4.2, 6.0);

        series2.add(2.0, 1.0);
        series2.add(2.5, 2.4);
        series2.add(3.2, 1.2);
        series2.add(3.9, 2.8);
        series2.add(4.6, 3.0);

        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    /**
     * Methode de création des valeurs à afficher sur le graphique
     * @param carotte la carotte à afficher
     * @return la dataset du graphique
     */
    public XYDataset createDatasetMasse(M_Carotte carotte){
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries serie = new XYSeries("Masse");
        M_Mesure temp = null;
        double t = 0;

        Iterator<M_Mesure> iter = carotte.getListMesures().iterator();
        while(iter.hasNext()){
            temp = iter.next();
            serie.add(t, temp.getMasse());
            t++;
        }

        serie.add(1.0, 2.0);
        serie.add(2.0, 3.0);
        serie.add(3.0, 2.5);
        serie.add(3.5, 2.8);
        serie.add(4.2, 6.0);

        dataset.addSeries(serie);

        return dataset;
    }

    /**
     * Methode de création des valeurs à afficher sur le graphique
     * @param carotte la carotte à afficher
     * @return la dataset du graphique
     */
    public XYDataset createDatasetHauteur(M_Carotte carotte){
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries serie = new XYSeries("Hauteur de la fange humide");
        M_Mesure temp = null;
        double t = 0;

        Iterator<M_Mesure> iter = carotte.getListMesures().iterator();
        while(iter.hasNext()){
            temp = iter.next();
            serie.add(t, temp.getHauteurFrangeHumide());
            t++;
        }

        /*serie.add(1.0, 2.0);
        serie.add(2.0, 3.0);
        serie.add(3.0, 2.5);
        serie.add(3.5, 2.8);
        serie.add(4.2, 6.0);*/

        dataset.addSeries(serie);

        return dataset;
    }
}

