package geoimbib.Models.ModelsJPanelMainLeft;

import geoimbib.Models.M_Carotte;
import geoimbib.Models.M_Mesure;
import geoimbib.Models.M_Serie;
import geoimbib.Models.ModelsJPanelMainLeft.Threads.M_ThreadWarningNoPath;
import geoimbib.Views.JPanels.V_JPanelMainLeft;

import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ravier on 15/01/2016.
 */
public class M_GeneralFunctions {

    V_JPanelMainLeft v_jPanelMainLeft = null;

    public M_GeneralFunctions(V_JPanelMainLeft v_jPanelMainLeft) {
        this.v_jPanelMainLeft = v_jPanelMainLeft;
    }

    public String loadPathFolderSeries() {
        String path = "";
        String fichier = Paths.get("Res/Preferences/pref.txt").toString();


        try{
            InputStream ips=new FileInputStream(fichier);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            if ((ligne=br.readLine())!=null){
                path=ligne;
            }
            br.close();

        }
        catch (Exception e){
            M_ThreadWarningNoPath m_threadWarningNoPath = new M_ThreadWarningNoPath(this.v_jPanelMainLeft);
            m_threadWarningNoPath.start();
        }

        return path;
    }

    public void savePathPreferenceInFile(String pathToSave) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(Paths.get("Res/Preferences/pref.txt").toString())));
            writer.write(pathToSave);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    /*
    * Le nom du répertoire doi commencer par "Serie"
    * */
    public Vector<String> listNameFolder(File file) {
        String [] listefichiers;
        Vector<String> arrayListeSeries = new Vector<String>();
        listefichiers=file.list();

        for(int i=0;i<listefichiers.length;i++){
            if(listefichiers[i].startsWith("Serie")){
                arrayListeSeries.add(listefichiers[i].substring(0,listefichiers[i].length()));
            }
        }

        return arrayListeSeries;
    }


    /*
    * Un échantillon  = un fichier csv, on répertorie tous les fichiers csv du dossier
    * */
    public Vector<String> listNameCsv(File fileSerie) {

        System.out.println(fileSerie);
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

    /**
     * Methode qui génère la serie en fonction de la série sélectionnée dans le panelMainLeft de gauche
     * @param serieSelected le nom du dossier contenant les carottes
     */
    public void generationSerie(String serieSelected){
        M_Serie serie = null;
        String nomSerie, temp [], mesuresCarotte[], datePremCarotte, nomCarotte, ligne;
        Double diametre, surface, longueur, hauteurFange;
        Calendar dateHeure = null;
        M_Mesure mesure = null;
        ArrayList<M_Mesure> listMesures;
        M_Carotte carotte = null;
        ArrayList<M_Carotte> listCarotte = new ArrayList<M_Carotte>();
        int nbrMesuresCarottes = 0;

        temp = serieSelected.split("_");
        nomSerie = temp[1];

        File directory = new File(Paths.get(v_jPanelMainLeft.getJtextfieldFolder().getText()).toString() + File.separator + serieSelected);
        File[] files = directory.listFiles();
        for(int i = 0 ; i < files.length ; i++){
            if(files[i].toString().endsWith(".csv")) {
                try{
                    // on reference le fichier dans lequel il y a les données d'une carotte
                    nomCarotte = files[i].getName();
                    InputStream ips = new FileInputStream(files[i]);
                    InputStreamReader ipsr = new InputStreamReader(ips);
                    BufferedReader br = new BufferedReader(ipsr);
                    //on gère d'abord la première ligne sensée contenir les intitulés des colonnes et le diametre/surface et longueur de la carotte
                    if((ligne = br.readLine()) != null){
                        temp = ligne.split(";");
                        diametre = Double.parseDouble(temp[0]);
                        surface = Double.parseDouble(temp[1]);
                        longueur = Double.parseDouble(temp[2]);
                    }
                    else {
                        System.err.println("une des carottes de la série ne contient aucune donnée");
                        return;
                    }
                    listMesures = new ArrayList<M_Mesure>();
                    nbrMesuresCarottes = 0;
                    while((ligne = br.readLine()) != null) {
                        try {
                            temp = ligne.split(";");
                            dateHeure = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                            dateHeure.setTime(sdf.parse(temp[4].concat(" " + temp[5])));
                            hauteurFange = Double.parseDouble(temp[7]);
                            mesure = new M_Mesure(dateHeure, hauteurFange);
                            listMesures.add(mesure);
                            nbrMesuresCarottes++;
                        } catch (ParseException e) {
                            System.err.println("Problème de format de date");
                            e.printStackTrace();
                        }
                    }
                    carotte = new M_Carotte(nomCarotte, diametre, longueur, 0.0, listMesures);
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
    }


}
