package geoimbib.Models;

import java.util.ArrayList;

/**
 * <b>Created by Quentin PEREZ on 11/01/16.</b>
 * <b>Classe permettant de mod&eacute;liser une carotte.</b>
 * Une Carotte est caract&eacute;ris&eacute;e par les informations suivantes :
 * <ul>
 * <li>Un nom</li>
 * <li>Une longueur</li>
 * <li>Un diam&egrave;tre</li>
 * <li>Une masse</li>
 * <li>Une liste de Mesures</li>
 * </ul> */
public class M_Carotte {

    private String nom;
    private double longueur;
    private double diametre;
    private ArrayList<M_Mesure> listMesures;

    /**
     * Constructeur M_Carotte.
     * <p>
     * Constructeur vide
     * </p>
     */
    private M_Carotte(){

    }

    /**
     * Constructeur M_Carotte.
     * <p>
     * A la construction d'un objet M_Carotte, M_Carotte est initinitialis&eacute;
     * avec les param&egrave;tres du constructeur.
     * </p>
     * @param nom Nom de la carotte
     * @param diametre Diametre de la carotte
     * @param longueur Longueur de la carotte
     * @param masse Masse de la la carotte
     * @param listMesures liste des hauteurs mesurées
     */
    public M_Carotte(String nom, double diametre, double longueur , double masse, ArrayList<M_Mesure> listMesures) {
        this.nom = nom;
        this.diametre = diametre;
        this.longueur = longueur;
        this.listMesures = listMesures;
    }

    /**
     * Constructeur M_Carotte.
     * <p>
     * A la construction d'un objet M_Carotte, M_Carotte est initinitialis&eacute;
     * avec les param&egrave;tres du constructeur.
     * </p>
     * @param nom Nom de la carotte
     * @param diametre Diametre de la carotte
     * @param longueur Longueur de la carotte
     * @param masse Masse de la la carotte
     */
    public M_Carotte(String nom, double diametre, double longueur ,double masse) {
        this.nom = nom;
        this.diametre = diametre;
        this.longueur = longueur;
        listMesures = new ArrayList<M_Mesure>();
    }

    /**
     * Retourne le nom de la carotte
     * @return nom de la carotte
     */
    public String getNom() {
        return nom;
    }

    /**
     * Met &agrave; jour le nom de la Carotte
     * @param nom de la carotte
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne la liste des hauteurs de franges humides mesur&eacute;es
     * @return liste des hauteurs mesur&eacute;es
     */
    public ArrayList<M_Mesure> getListMesures() {
        return listMesures;
    }

    /**
     * Met &agrave; jour la liste des hauteurs mesur&eacute;es
     * @param listMesures liste des hauteurs mesur&eacute;es
     */
    public void setListMesures(ArrayList<M_Mesure> listMesures) {
        this.listMesures = listMesures;
    }

    /**
     * Retourne la longueur de la carotte
     * @return longueur de la carotte
     */
    public double getLongueur() {
        return longueur;
    }

     /**
     * Met &agrave; jour la longueur de la carotte
     * @param  longueur de la carotte
     */
    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    /**
     * Retourne le diametre de la carotte
     * @return diametre de la carotte
     */
    public double getDiametre() {
        return diametre;
    }

    /**
     * Met &agrave; jour le diametre de la carotte
     * @param diametre de la carotte
     */
    public void setDiametre(double diametre) {
        this.diametre = diametre;
    }

    /**
     * Calcule et retourne la surface de la base de la carotte
     * @return surface de la carotte
     */
    public double calculSurface() {
        return Math.pow(diametre/2,2) * Math.PI;
    }


    public double calulMoyenneMesure() {
        double sommeMesure = 0;
        for (M_Mesure mesure : listMesures){
            sommeMesure += mesure.getHauteurFrangeHumide();
        }
        return sommeMesure / listMesures.size();
    }

    @Override
    public String toString() {
        return "M_Carotte{" +
                "nom : '" + nom + '\'' +
                ", diametre : " + diametre +
                ", longueur : " + longueur +
                ", listMesures : " + listMesures.toString() +
                '}';
    }
}
