package geoimbib.Models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <b>Created by Quentin PEREZ on 12/01/16.</b>
 * <b>Classe permettant de mod&eacute;liser une mesure.</b>
 * Une Mesure est caract&eacute;ris&eacute;e par les informations suivantes :<ul>
 * <li>Une date/heure</li>
 * <li>Une hauteur de frange humide</li>
 * </ul> */
public class M_Mesure {
    private Calendar dateHeure;
    private double hauteurFrangeHumide;

    /**
     * Constructeur M_Mesure.
     * <p>
     * Constructeur vide
     * </p>
     */
    public M_Mesure(){}

    /**
     * Constructeur M_Mesure.
     * <p>
     * A la construction d'un objet M_Mesure, M_Mesure est initinitialis&eacute;
     * avec les param&egrave;tres du constructeur.
     * </p>
     * @param hauteurFrangeHumide Hauteur de la frange humide
     */
    public M_Mesure(double hauteurFrangeHumide){
        this.hauteurFrangeHumide = hauteurFrangeHumide;
        dateHeure = Calendar.getInstance();

    }

    /**
     * Constructeur M_Mesure.
     * <p>
     * A la construction d'un objet M_Mesure, M_Mesure est initinitialis&eacute;
     * avec les param&egrave;tres du constructeur.
     * </p>
     * @param dateHeure Date et heure de la mesure
     * @param hauteurFrangeHumide Hauteur de la frange humide
     */
    public M_Mesure(Calendar dateHeure, double hauteurFrangeHumide) {
        this.dateHeure = dateHeure;
        this.hauteurFrangeHumide = hauteurFrangeHumide;
    }

    /**
     * Retourne l'objet Calendar qui stocke la date et l'heure de la mesure
     * @return date et heure de la mesure
     */
    public Calendar getDateHeure() {
        return dateHeure;
    }

    /**
     * Met &agrave; jour l'object Calendar dateHeure de la mesure
     * @param dateHeure le nouvelle object calendar contenant la date et l'heure de la mesure
     */
    public void setDateHeure(Calendar dateHeure) {
        this.dateHeure = dateHeure;
    }

    /**
     * Retourne la hauteur de la frange humide de la mesure
     * @return double hauteur de la frange humide
     */
    public double getHauteurFrangeHumide() {
        return hauteurFrangeHumide;
    }

    /**
     * Met &agrave; jour la hauteur de la frange humide
     * @param hauteurFrangeHumide la nouvelle hauteur de la frange humide
     */
    public void setHauteurFrangeHumide(double hauteurFrangeHumide) {
        this.hauteurFrangeHumide = hauteurFrangeHumide;
    }

    /**
     * Retourne la date sous le format: "jj/mm/aaaa" de la mesure
     * @return String date de la mesure
     */
    public String getDateMesure() {
        String dateFormate = new SimpleDateFormat("dd/MM/yyyy").format(dateHeure.getTime());
        return dateFormate;
    }

    /**
     * Retourne l'heure sous le format: "hh:mm" de la mesure
     * @return String heure de la mesure
     */
    public String getHeureMesure() {
        String dateFormate = new SimpleDateFormat("hh:mm").format(dateHeure.getTime());
        return dateFormate;
    }
}
