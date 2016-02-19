package geoimbib.Models;


import com.googlecode.jcsv.writer.CSVWriter;
import com.googlecode.jcsv.writer.internal.CSVWriterBuilder;

import java.io.*;

import java.util.Date;

/**
 * <b>Created by ravier on 31/01/2016.</b>
 * <b>Classe permettant de transformer une s&eacute;rie en un dossier et plusieurs fichiers csv.</b>
 */


public class M_createSet {


    private final String jtextfolder;
    private M_Serie m_serie= null;


    /*public M_createSet(ArrayList<ArrayList<M_Mesure>> arrayLists, String nomSerie, int nbEchant, String[] tabNameEchant, double[] tabHautEchant, double[] tabDiamEchant, Calendar calendarSerie, String jtextfieldFolder) {
        this.arrayLists = arrayLists;
        this.nomSerie = nomSerie;
        this.nbEchant = nbEchant;
        this.tabNameEchant = tabNameEchant;
        this.tabHautEchant = tabHautEchant;
        this.tabDiamEchant = tabDiamEchant;
        this.calendarSerie = calendarSerie;
        this.jtextfieldFolder = jtextfieldFolder;

        createSerie();
        try {
            createFiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public M_createSet(M_Serie m_serie, String jtextfolder) {
        this.m_serie = m_serie;
        this.jtextfolder = jtextfolder;

        createSerie();
        try {
            createFiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode de creation de fichiers csv
     * <p>
     * Methode de creation des fichiers csv qui contiendront les echantillons, 1 fichier = 1 echantillon
     * Utilisation de la librairie "jcsv" fait par google.
     * </p>
     */
    private void createFiles() throws IOException {
        //Création du dossier = série
        File file = new File(this.jtextfolder+ File.separator + this.m_serie.getNom());
        if (file.exists()) {
            System.out.println("Le dossier existe déjà : " + file.getAbsolutePath());
        } else {
            if (file.mkdir()) {
                System.out.println("Ajout du dossier : " + file.getAbsolutePath());
            } else {
                System.out.println("Echec sur le dossier : " + file.getAbsolutePath());
            }
        }

        //Pour tous les échantillons : créer un fichier correspondant et le remplir avec les données
        M_Carotte m_carotte;
        CSVWriter csvWriter;
        Writer out;
        for (int i=0; i<m_serie.getListCarotte().size(); ++i){
            m_carotte = m_serie.getListCarotte().get(i);

            //Création du fichier correspondant à la carotte et écriture dedans
            out = new FileWriter(this.jtextfolder+ File.separator + this.m_serie.getNom()+File.separator+m_carotte.getNom()+".csv");
            csvWriter = new CSVWriterBuilder(out).entryConverter(new M_conceptionCSVConverter()).build();
            csvWriter.write(m_carotte);
            csvWriter.flush();

            csvWriter = new CSVWriterBuilder(out).entryConverter(new M_conceptionCSVConverterListeMesure()).build();
            csvWriter.writeAll(m_carotte.getListMesures());
            csvWriter.flush();

        }
    }

    /**
     * Methode de creation de serie
     * <p>
     * Methode qui regroupe toute les informations recoltes pendant le protocole et cree les series.
     * </p>
     */
    private void createSerie() {


        for (int i = 0; i<m_serie.getListCarotte().size(); ++i){
            for (int y=0; y<m_serie.getListCarotte().get(i).getListMesures().size(); ++y){
                if (y==0)
                    m_serie.getListCarotte().get(i).getListMesures().get(y).setTemps(0);
                else{
                    Date h1 = m_serie.getListCarotte().get(i).getListMesures().get(y).getDateHeure().getTime();
                    Date h2 = m_serie.getListCarotte().get(i).getListMesures().get(y-1).getDateHeure().getTime();
                    double diff = (h1.getTime() - h2.getTime()) /(1000.0*60.0) /60.0;
                    System.out.println("h1.getTime(): "+h1.getTime()
                            + " h2.getTime():"+h2.getTime()
                            + " (h1.getTime() - h2.getTime())/60 : "+ (h1.getTime() - h2.getTime())
                            + " (h1.getTime() - h2.getTime())/(1000*60) : " +(h1.getTime() - h2.getTime())/(1000*60));
                    m_serie.getListCarotte().get(i).getListMesures().get(y).setTemps(diff);
                }
            }
        }

        /*m_serie = new M_Serie(
                this.nomSerie,
                arrayCarotte.get(0).getListMesures().size(),
                arrayCarotte,
                this.calendarSerie
        );*/
    }
}
