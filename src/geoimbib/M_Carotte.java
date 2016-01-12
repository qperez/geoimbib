package geoimbib;

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
 * <li>Une liste de hauteurs de mesures de franges humides</li>
 * </ul> */
public class M_Carotte {

    private String nom;
    private double longueur;
    private double diametre;
    private double masse;
    private ArrayList<double[]> listHauteursMesures;

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
     * @param listHauteursMesures liste des hauteurs mesurées
     */
    public M_Carotte(String nom, double diametre, double longueur ,double masse, ArrayList<double[]> listHauteursMesures) {
        this.nom = nom;
        this.diametre = diametre;
        this.longueur = longueur;
        this.masse = masse;
        this.listHauteursMesures = listHauteursMesures;
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
        this.masse = masse;
        listHauteursMesures = new ArrayList<double[]>();
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
    public ArrayList<double[]> getListHauteursMesures() {
        return listHauteursMesures;
    }

    /**
     * Met &agrave; jour la liste des hauteurs mesur&eacute;es
     * @param listHauteursMesures liste des hauteurs mesur&eacute;es
     */
    public void setListHauteursMesures(ArrayList<double[]> listHauteursMesures) {
        this.listHauteursMesures = listHauteursMesures;
    }

    /**
     * Met &agrave; jour la liste des hauteurs mesur&eacute;es
     * @param indice du tableau de mesure
     */
    public void getTabHauteursMesures(int indice){
        listHauteursMesures.get(indice);
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
     * Retourne la masse de la carotte
     * @return masse de la carotte
     */
    public double getMasse() {
        return masse;
    }

    /**
     * Met &agrave; jour la masse de la carotte
     * @param masse de la carotte
     */
    public void setMasse(double masse) {
        this.masse = masse;
    }

    /**
     * Calcule et retourne la surface de la base de la carotte
     * @return surface de la carotte
     */
    public double calculSurface() {
        return Math.pow(diametre/2,2) * Math.PI;
    }
}
