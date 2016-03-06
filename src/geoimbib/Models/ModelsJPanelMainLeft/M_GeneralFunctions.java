package geoimbib.Models.ModelsJPanelMainLeft;

import geoimbib.Models.M_Carotte;
import geoimbib.Models.M_Mesure;
import geoimbib.Models.M_Serie;
import geoimbib.Models.ModelsJPanelMainLeft.Threads.M_ThreadWarningNoPath;
import geoimbib.Views.JPanels.V_JPanelMainLeft;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class de fonctions diverses, telles que la r&eacute;cup&eacute;ration des noms des &eacute;chantillons, de la gestion de spr&eacute;f&eacute;rences.
 */
public class M_GeneralFunctions {

    V_JPanelMainLeft v_jPanelMainLeft = null;

    public M_GeneralFunctions(V_JPanelMainLeft v_jPanelMainLeft) {
        this.v_jPanelMainLeft = v_jPanelMainLeft;
    }

    /**
     * M&eacute;thode qui renvoie le chemin du dossier sauvegard&eacute; dans les pr&eacute;f&eacute;rences.
     * @return path
     */
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

    /**
     * M&eacute;thode d'&eacute;criture du chemin dans le fichier des pr&eacute;f&eacute;rences
     * @param pathToSave
     */
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


    /**
     * Retourne la liste des s&eacute;ries
     * @param file
     * @return Vector<String>
     */
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
