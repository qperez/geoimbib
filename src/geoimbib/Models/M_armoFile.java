package geoimbib.Models;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * Created by ravier on 26/02/2016.
 * <p>Classe singleton contenant des fonctions appelées lors de la modification de série</p>
 */
public class M_armoFile {

    private static M_armoFile INSTANCE = null;

    private M_armoFile(){

    }

    /**
     * Retourne une instance de la classe M_armoFile et la cr&eacute;e si ce n'est pas d&eacute;j&agrave; fait
     * @return M_armoFile
     */
    public static M_armoFile getINSTANCE(){
        if (INSTANCE == null){
            INSTANCE = new M_armoFile();
        }
        return INSTANCE;
    }

    /**
     * Cette m&eacute;thode renvoie la valeur du temps de la derni&egrave;re mesure d'un &eacute;chantillon.
     * @param path
     * @return
     */
    public Date getLastDateEchant(String path){
        Reader reader = null;
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        CSVReader csvMesures = CSVReaderBuilder.newDefaultReader(reader);
        List<String[]> mesures = null;
        try {
            mesures = csvMesures.readAll();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dateInString = mesures.get(mesures.size()-1)[4]+" "+mesures.get(mesures.size()-1)[5];
            Date date = null;
            sdf.setLenient(false);
            date = sdf.parse(dateInString);
            return date;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public double getLastDeltaMesureEchant(String path) {
        Reader reader = null;
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        CSVReader csvMesures = CSVReaderBuilder.newDefaultReader(reader);
        List<String[]> mesures = null;
        try {
            mesures = csvMesures.readAll();
            return Double.parseDouble(mesures.get(mesures.size()-1)[11]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retourne la premi&egrave;re variable "masse" de l'&eacute;chantillon
     * @param path
     * @return double
     */
    public double getFirstMasseOfEchant(String path) {
        Reader reader = null;
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        CSVReader csvMesures = CSVReaderBuilder.newDefaultReader(reader);
        List<String[]> mesures = null;
        try {
            mesures = csvMesures.readAll();
            return Double.parseDouble(mesures.get(1)[6]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Renvoie la variable "temps" de la derni&egrave;re mesure d'un &eacute;chantillon avant la modification
     * @param path
     * @return la variable "temps" de la derni&egrave;re mesure d'un &eacute;chantillon avant la modification
     */
    public double getLastTimeEchant(String path) {
        Reader reader = null;
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        CSVReader csvMesures = CSVReaderBuilder.newDefaultReader(reader);
        List<String[]> mesures = null;
        try {
            mesures = csvMesures.readAll();
            return Double.parseDouble(mesures.get(mesures.size()-1)[8]);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Retourne le diam&egrave;tre de l'&eacute;chantillon
     * @param path
     * @return double
     */
    public double getDiamCarotte(String path) {
        Reader reader = null;
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        CSVReader csvMesures = CSVReaderBuilder.newDefaultReader(reader);
        List<String[]> mesures = null;
        try {
            mesures = csvMesures.readAll();
            return Double.parseDouble(mesures.get(0)[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Fusion de plusieurs séries
     * @param listNomSerie
     * @param path
     * @param newNameSet
     */
    public void fusionSet(List<String> listNomSerie, String path, String newNameSet) throws IOException {
        //Création du dossier = série
        File destination = new File(path+ File.separator + newNameSet);
        if (destination.exists()) {
            System.out.println("Le dossier existe déjà : " + destination.getAbsolutePath());
        } else {
            destination.mkdir();
        }
        File sourceRepertory;
        File sourceFile;
        for (int i=0; i<listNomSerie.size(); i++){

            sourceRepertory = new File(path+ File.separator + listNomSerie.get(i));
            Vector<String> listeFichiersCSV = listNameCsv(sourceRepertory);

            //déplacement pour tous les fichiers
            for (int y=0; y < listeFichiersCSV.size(); ++y){
                sourceFile = new File(sourceRepertory+File.separator+listeFichiersCSV.get(y));
                destination = new File(path+File.separator+newNameSet+File.separator+listeFichiersCSV.get(y));
                if (!deplacer(sourceFile,destination))
                    throw new IOException("deplacement");
            }
            if (!sourceRepertory.delete())
                throw new IOException();
        }
    }

    /**
     * Fonction de d&eacute;placement d'un fichier dans un autre r&eacute;pertoire
     * @param source
     * @param destination
     * @return bool&eacute;en
     */
    public static boolean deplacer(File source,File destination) {
        if( !destination.exists() ) {
            boolean result = source.renameTo(destination);
            return result;
        } else {
            System.out.println("déplacement pas ok");
            return false;
        }
    }

    /**
     * R&eacute;cup&egrave;re la liste des fichiers csv (&eacute;chantillon) d'une s&eacute;rie
     * @param fileSerie s&eacute;rie
     * @return
     */
    public Vector<String> listNameCsv(File fileSerie) {
        String [] listefichiers2;
        Vector<String> arrayListeCsv = new Vector<String>();
        listefichiers2=fileSerie.list();

        for(int i=0;i<listefichiers2.length;i++){
            if(listefichiers2[i].endsWith(".csv")){
                arrayListeCsv.add(listefichiers2[i].substring(0,listefichiers2[i].length()));
            }
        }
        return arrayListeCsv;
    }
}
