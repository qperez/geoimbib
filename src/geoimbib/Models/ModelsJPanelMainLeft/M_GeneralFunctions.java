package geoimbib.Models.ModelsJPanelMainLeft;

import geoimbib.Models.ModelsJPanelMainLeft.Threads.M_ThreadWarningNoPath;
import geoimbib.Views.JPanels.V_JPanelMainLeft;

import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Vector;

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
