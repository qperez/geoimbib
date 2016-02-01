package geoimbib.Models;


import com.googlecode.jcsv.writer.CSVWriter;
import com.googlecode.jcsv.writer.internal.CSVWriterBuilder;

import java.io.*;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * <b>Created by ravier on 31/01/2016.</b>
 * <b>Classe permettant de transformer une s&eacute;rie en un dossier et plusieurs fichiers csv.</b>
*/


public class M_createSet {

    private final String nomSerie;
    private final int nbEchant;
    private final String[] tabNameEchant;
    private final double[] tabHautEchant;
    private final double[] tabDiamEchant;
    private final Calendar calendarSerie;
    private final String jtextfieldFolder;

    private ArrayList<ArrayList<M_Mesure>> arrayLists = null;

    private M_Serie m_serie= null;

    /**
     * Constructeur M_createSet.
     * <p>
     * Constructeur qui initialise la variable arrayLists.
     * </p>
     * @param arrayLists double liste de M_Mesure qui contiennent les informations des s&eacute;ries.
     * @param nomSerie nom de la s&eacute;rie
     * @param nbEchant nombre de carottes
     * @param tabNameEchant tableau des noms des carottes
     * @param tabHautEchant tableau des hauteurs des carottes
     * @param tabDiamEchant tableau des diametres des carottes
     * @param calendarSerie permet de connaitre l'heure et la date de la creation de la serie
     * @param jtextfieldFolder permet de connaitre le chemin absolu de la creation de la serie
     */
    public M_createSet(ArrayList<ArrayList<M_Mesure>> arrayLists, String nomSerie, int nbEchant, String[] tabNameEchant, double[] tabHautEchant, double[] tabDiamEchant, Calendar calendarSerie, String jtextfieldFolder) {
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
    }

    /**
     * Methode de creation de fichiers csv
     * <p>
     * Methode de creation des fichiers csv qui contiendront les echantillons, 1 fichier = 1 echnatillon
     * Utilisation de la librairie "jcsv" fait par google.
     * </p>
     */
    private void createFiles() throws IOException {
        //Création du dossier = série
        File file = new File(this.jtextfieldFolder+ File.separator + this.nomSerie);
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
            out = new FileWriter(this.jtextfieldFolder+ File.separator + this.nomSerie+File.separator+m_carotte.getNom()+".csv");
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


        ArrayList<M_Carotte> arrayCarotte = new ArrayList<>();

        M_Carotte m_carotte;
        for (int i = 0; i<arrayLists.size(); ++i){
            m_carotte = new M_Carotte(tabNameEchant[i], tabDiamEchant[i], tabHautEchant[i], arrayLists.get(i));
            arrayCarotte.add(m_carotte);
        }

        m_serie = new M_Serie(
                this.nomSerie,
                arrayCarotte.get(0).getListMesures().size(),
                arrayCarotte,
                this.calendarSerie
                );
    }
}
