package geoimbib.Models;

import com.googlecode.jcsv.writer.CSVEntryConverter;

/**
 * <b>Created by ravier on 01/02/2016.</b>
 * <b>Classe implementant CSVEntryConverter de la google librairie jcsv</b>
 */
public class M_conceptionCSVConverter implements CSVEntryConverter<M_Carotte> {


    /**
     * Methode convertEntry @Override
     * <p>
     * Methode de l'interface M_conceptionCSVConverter où on transforme en tableau de String toute les données que l'on veut
     * afficher dans le fichier csv de l'echantillon.
     * (Données en rapport avec l'information sur la série.
     * </p>
     * @return Tableau de String
     */
    @Override
    public String[] convertEntry(M_Carotte m) {
        String[] columns = new String[14];

        columns[0] = String.valueOf(m.getDiametre()); ////diamètre
        columns[1] = String.valueOf(Math.PI*m.getDiametre());//surface
        columns[2] = String.valueOf(m.getLongueur());//longueur
        columns[3] = " ";
        columns[4] = "Date";
        columns[5] = "Heure";
        columns[6] = "w(g)";
        columns[7] = "dL(cm)";
        columns[8] = "temps";
        columns[9] = "temps";
        columns[10] = "g/cm²";
        columns[11] = "dL(cm)";
        columns[12] = "(w2-ws)/s";
        columns[13] = "(w48-ws)/s";


        return columns;
    }

}
