package geoimbib.Models;

import com.googlecode.jcsv.writer.CSVEntryConverter;


/**
 * <b>Created by ravier on 01/02/2016.</b>
 * <b>Classe implementant CSVEntryConverter de la google librairie jcsv</b>
 */
public class M_conceptionCSVConverterListeMesure implements CSVEntryConverter<M_Mesure> {


    /**
     * Methode convertEntry @Override
     * <p>
     * Methode de l'interface M_conceptionCSVConverterListeMesure où on transforme en tableau de String toute les données que l'on veut
     * afficher dans le fichier csv de l'echantillon.
     * </p>
     * @return Tableau de String
     */
    @Override
    public String[] convertEntry(M_Mesure m_mesures) {
        //System.out.println("Temps : "+m_mesures.getTemps());
        String[] columns = new String[12];
        columns[0] = " ";
        columns[1] = " ";
        columns[2] = " ";
        columns[3] = " ";
        columns[4] = String.valueOf(m_mesures.getDateMesure());
        columns[5] = String.valueOf(m_mesures.getHeureMesure());
        columns[6] = String.valueOf(m_mesures.getMasse());
        columns[7] = String.valueOf(m_mesures.getHauteurFrangeHumide());
        columns[8] = String.valueOf(m_mesures.getTemps());
        columns[9] = String.valueOf(Math.sqrt(m_mesures.getTemps()));
        columns[10] = "g/cm²";
        columns[11] = "dL(cm)";

        return columns;
    }
}
