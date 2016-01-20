package geoimbib.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * <b>Created by Quentin PEREZ on 11/01/16.</b>
 * <b>Classe permettant de mod&eacute;liser une série.</b>
 * Une Serie est caract&eacute;ris&eacute;e par les informations suivantes :
 * <ul>
 * <li>Un nom</li>
 * <li>Un nombre de mesures par carottes</li>
 * <li>Une liste de Carottes</li>
 * <li>Une date/heure </li>
 * </ul>
 */
public class M_Serie {

    private String nom;
    private int nombreMesuresParCarottes;
    private ArrayList<M_Carotte> listCarotte;
    private Calendar dateHeure;

    /**
     * Constructeur M_Serie.
     * <p>
     * Constructeur vide
     * </p>
     */
    public M_Serie(){
        dateHeure = Calendar.getInstance();;
    }

    /**
     * Constructeur M_Serie.
     * <p>
     * A la construction d'un objet M_Serie, M_Serie est initinitialis&eacute;
     * avec les param&egrave;tres du constructeur.
     * </p>
     * @param nom Nom de la s&eacute;rie
     * @param nombreMesuresParCarottes nombre de mesures faites par carottes
     * @param listCarotte liste de carottes
     * @param dateHeure objet Calendar
     * @see M_Carotte
     */
    public M_Serie(String nom,
                   int nombreMesuresParCarottes,
                   ArrayList<M_Carotte> listCarotte,
                   Calendar dateHeure){
        this.nom = nom;
        this.nombreMesuresParCarottes = nombreMesuresParCarottes;
        this.listCarotte = listCarotte;
        this.dateHeure = dateHeure;
    }

    /**
     * Constructeur M_Serie.
     * <p>
     * A la construction d'un objet M_Serie, M_Serie est initinitialis&eacute;
     * avec les param&egrave;tres du constructeur.
     * </p>
     * @param nom Nom de la s&eacute;rie
     * @param nombreMesuresParCarottes nombre de mesures faites par carottes
     */
    public M_Serie(String nom, int nombreMesuresParCarottes){
        this.nom = nom;
        this.nombreMesuresParCarottes = nombreMesuresParCarottes;
        this.listCarotte = new ArrayList<M_Carotte>();
        this.dateHeure = Calendar.getInstance();
    }

    /**
     * Retourne l'objet Date contenant la date et l'heure du syst&egrave;me
     * @return objet Date
     */
    public Calendar getDateHeure() {
        return dateHeure;
    }

    /**
     * Met &agrave; jour l'objet Date
     * @param dateHeure le nouvel objet Date
     */
    public void setDateHeure(Calendar dateHeure) {
        this.dateHeure = dateHeure;
    }

    /**
     * Retourne la liste des &eacute;chantillons
     * @return liste des &eacute;chantillons
     */
    public ArrayList<M_Carotte> getListCarotte() {
        return listCarotte;
    }

    /**
     * Met &agrave; jour la liste des &eacute;chantillons
     * @param listCarotte nouvelle ArrayListe d'échantillons
     */
    public void setListCarotte(ArrayList<M_Carotte> listCarotte) {
        this.listCarotte = listCarotte;
    }

    /**
     * Retourne le nom de la s&eacute;rie
     * @return nom de Serie
     */
    public String getNom() {
        return nom;
    }

    /**
     * Met &agrave; jour le nom de la Serie.
     * @param nom de la Serie
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le nombre de mesures par carottes
     * @return nombre de mesures par carottes
     */
    public int getNombreMesuresParCarottes() {
        return nombreMesuresParCarottes;
    }

    /**
     * Met &agrave; jour le nombre de mesures par carottes
     * @param nombreMesuresParCarottes nouveau nombre de mesures par carottes
     */
    public void setNombreMesuresParCarottes(int nombreMesuresParCarottes) {
        this.nombreMesuresParCarottes = nombreMesuresParCarottes;
    }


    public double calculMoyenneSerie() {
        double sommeMesures = 0;
        for(M_Carotte carotte : listCarotte){
            sommeMesures += carotte.calulMoyenneMesure();
        }
        return sommeMesures/(listCarotte.size());
    }
}
