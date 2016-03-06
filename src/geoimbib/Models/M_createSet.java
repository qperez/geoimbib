package geoimbib.Models;


import com.googlecode.jcsv.writer.CSVWriter;
import com.googlecode.jcsv.writer.internal.CSVWriterBuilder;

import java.io.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * <b>Created by ravier on 31/01/2016.</b>
 * <b>Classe permettant de transformer une s&eacute;rie en un dossier et plusieurs fichiers csv.</b>
 */


public class M_createSet {


    private ArrayList<M_Carotte> arrayCarottes;
    private String jtextfolder;
    private M_Serie m_serie= null;


    public M_createSet(M_Serie m_serie, String jtextfolder) {
        this.m_serie = m_serie;
        this.jtextfolder = jtextfolder;

        assertValueToMesure();
        try {
            createFiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * M&eacute;thode de modification des s&eacute;rie, ajout de mesure(s)
     */
    public M_createSet(ArrayList<M_Carotte> arrayCarottes, String s, String text, ArrayList<String> arrayNameUpdate) {
        try {
            this.arrayCarottes = arrayCarottes;
            assertValueToMesureModif(s, text, arrayNameUpdate);
            addMesureSet(s, text, this.arrayCarottes, arrayNameUpdate);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Ajoute les mesures dans les &eacute;chantillons (fichiers .csv)
     * @param path
     * @param seriename
     * @param arrayCarottes
     * @param arrayNameUpdate
     * @throws IOException
     */
    private void addMesureSet(String path, String seriename, ArrayList<M_Carotte> arrayCarottes, ArrayList<String> arrayNameUpdate) throws IOException {
        Writer out;
        CSVWriter csvWriter;

        for (int i =0; i<arrayCarottes.size(); ++i){
            //on insère les nouvelles mesures dans les échantillons
            out = new FileWriter(path+ File.separator + seriename +File.separator +arrayNameUpdate.get(i), true);
            csvWriter = new CSVWriterBuilder(out).entryConverter(new M_conceptionCSVConverterListeMesure()).build();
            csvWriter.writeAll(arrayCarottes.get(i).getListMesures());
            csvWriter.flush();
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
     * M&eacute;thode de creation de s&eacute;rie
     * <p>
     * M&eacute;thode qui regroupe toute les informations recoltes pendant le protocole et cr&eacute;e les series.
     * </p>
     */
    private void assertValueToMesure() {
        for (int i = 0; i<m_serie.getListCarotte().size(); ++i){
            for (int y=0; y<m_serie.getListCarotte().get(i).getListMesures().size(); ++y){
                if (y==0)
                    m_serie.getListCarotte().get(i).getListMesures().get(y).setTemps(0);
                else{
                    Date h1 = m_serie.getListCarotte().get(i).getListMesures().get(y).getDateHeure().getTime();
                    Date h2 = m_serie.getListCarotte().get(i).getListMesures().get(y-1).getDateHeure().getTime();
                    double diff = getDiffTimeTwoEchant(h1, h2);
                    m_serie.getListCarotte().get(i).getListMesures().get(y).setTemps(diff+m_serie.getListCarotte().get(i).getListMesures().get(y-1).getTemps());
                }
            }
            m_serie.getListCarotte().get(i).assertVarMasseSurface();
            m_serie.getListCarotte().get(i).assignCalulDeltaHauteurMesures();
        }
    }

    /**
     * M&eacute;thode d'armonisation des temps entre les anciennes mesures et les nouvelles.
     * @param path
     * @param seriename
     * @param arrayNameUpdate
     */
    private void assertValueToMesureModif(String path, String seriename, ArrayList<String> arrayNameUpdate) {
        Date first;
        Date second;
        for (int i = 0; i<arrayCarottes.size(); ++i){
            for (int y=0; y<arrayCarottes.get(i).getListMesures().size(); ++y){

                if (y==0) {
                    first = M_armoFile.getINSTANCE().getLastDateEchant(path + File.separator + seriename + File.separator + arrayNameUpdate.get(i));
                    second = arrayCarottes.get(i).getListMesures().get(y).getDateHeure().getTime();
                    double diff = getDiffTimeTwoEchant(second, first);
                    arrayCarottes.get(i).getListMesures().get(y).setTemps(diff+M_armoFile.getINSTANCE().getLastTimeEchant(path + File.separator + seriename + File.separator + arrayNameUpdate.get(i)));
                }
                else{
                    Date h1 = arrayCarottes.get(i).getListMesures().get(y).getDateHeure().getTime();
                    Date h2 = arrayCarottes.get(i).getListMesures().get(y-1).getDateHeure().getTime();
                    double diff = getDiffTimeTwoEchant(h1, h2);
                    arrayCarottes.get(i).getListMesures().get(y).setTemps(diff+arrayCarottes.get(i).getListMesures().get(y-1).getTemps());
                }
            }

            arrayCarottes.get(i).assertVarMasseSurfaceModif(M_armoFile.getINSTANCE().getFirstMasseOfEchant(path + File.separator + seriename + File.separator + arrayNameUpdate.get(i)), path + File.separator + seriename + File.separator + arrayNameUpdate.get(i));
            arrayCarottes.get(i).calulDeltaHauteurMesuresModif(M_armoFile.getINSTANCE().getLastDeltaMesureEchant(path + File.separator + seriename + File.separator + arrayNameUpdate.get(i)));

        }
    }

    /**
     * Retourne la diff&eacute;rence de date (heure) entre deux dates.
     * @param d1
     * @param d2
     * @return
     */
    private double getDiffTimeTwoEchant(Date d1, Date d2){
        return (d1.getTime() - d2.getTime()) /(1000.0*60.0) /60.0;
    }
}
