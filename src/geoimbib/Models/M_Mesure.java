package geoimbib.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private double masse;
    private double temps;

    /**
     * Constructeur M_Mesure.
     * <p>
     * Constructeur vide
     * </p>
     */
    public M_Mesure(){

    }


    /**
     * Constructeur M_Mesure.
     * <p>
     * A la construction d'un objet M_Mesure, M_Mesure est initinitialis&eacute;
     * avec les param&egrave;tres du constructeur.
     * </p>
     * @param masse Hauteur de la frange humide
     */
    public M_Mesure(double masse){
        this.masse = masse;
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
     * Constructeur M_Mesure.
     * <p>
     * A la construction d'un objet M_Mesure, M_Mesure est initinitialis&eacute;
     * avec les param&egrave;tres du constructeur.
     * </p>
     * @param dateHeure Date et heure de la mesure
     * @param hauteurFrangeHumide Hauteur de la frange humide
     * @param masse masse de l'échantillon
     */
    public M_Mesure(Calendar dateHeure, double hauteurFrangeHumide, double masse) {
        this.dateHeure = dateHeure;
        this.hauteurFrangeHumide = hauteurFrangeHumide;
        this.masse = masse;
    }

    /**
     * Constructeur M_Mesure.
     * <p>
     * A la construction d'un objet M_Mesure, M_Mesure est initinitialis&eacute;
     * avec les param&egrave;tres du constructeur.
     * </p>
     * @param dateHeure Date et heure de la mesure
     * @param hauteurFrangeHumide Hauteur de la frange humide
     * @param masse masse de l'échantillon
     * @param temps durée de la mesure
     */
    public M_Mesure(Calendar dateHeure, double hauteurFrangeHumide, double masse, double temps) {
        this.dateHeure = dateHeure;
        this.hauteurFrangeHumide = hauteurFrangeHumide;
        this.masse = masse;
        this.temps = temps;
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
        String dateFormate = new SimpleDateFormat("HH:mm").format(dateHeure.getTime());
        return dateFormate;
    }



    /**
     * Met &agrave; jour l'heure sous le format: "hh:mm" de la mesure
     */
    public void setHeureMesure(String d, String newHeure) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dateInString = d+" "+newHeure;
        Date date = null;
        sdf.setLenient(false);
        date = sdf.parse(dateInString);
        dateHeure.setTime(date);
    }



    /**
     * Retourne la masse de la carotte lors de la mesure
     * @return Double masse de la carotte lors de la mesure
     */
    public double getMasse() {
        return masse;
    }

    /**
     * Met &agrave; jour la masse de la carotte lors de la mesure
     * @param masse masse de la carotte lors de la mesure
     */
    public void setMasse(double masse) {
        this.masse = masse;
    }

    /**
     * Retourne le temps entre cette mesure et la mesure précédente
     * @return Double temps entre deux mesures
     */
    public double getTemps() {return temps;}

    /**
     * Met &agrave; jour le temps entre cette mesure et la mesure precedente
     * @param temps temps entre deux mesures
     */
    public void setTemps(double temps) {this.temps = temps;}

    /**
     * M&eacute;thode permettant d'obtenir la chaine de caract&egrave;re d&eacute;crivant l'objet mesure
     * @return String de l'objet M_Mesured
     */
    @Override
    public String toString() {
        return "M_Mesure{" +
                "hauteur : " + hauteurFrangeHumide +
                ", masse : " + masse +
                ", dateHeure : " + getDateMesure() + " " + getHeureMesure() +
                '}';
    }

    /**
     * Retourne la racine carr&eacute; du temps entre 2 mesures
     * @return la racine carr&eacute; du temps
     */
    public double getRacineCarreTemps() {
        return Math.sqrt(temps);
    }
}
