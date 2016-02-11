package geoimbib.Controlers;

import geoimbib.Models.M_Mesure;
import geoimbib.Views.JDialogs.*;
import geoimbib.Views.JPanels.V_JPanelMainRight;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ravier on 18/01/2016.
 */
public class C_ControlDialogSerie implements ActionListener, KeyListener {

    private V_JPanelMainRight v_jPanelMainRight = null;

    private V_jDialogMasse v_jDialogMasse = null;
    private V_JDialogNouvelleSerie v_jDialogNouvelleSerie = null;
    private V_JDialogChoiceNameEchant v_jDialogChoiceNameEchant = null;
    private V_JDialogChoiceHautDiam v_JDialogChoiceHautDiam = null;
    private V_JDialogInfoFinFillEchant v_JDialogInfoFinFillEchant = null;
    private V_JDialogFrange v_JDialogFrange = null;
    private V_JDialogHeure v_JDialogHeure;

    //Variables d'informations du jdialog v_jDialogNouvelleSerie
    private String nameSerie = "Serie";
    private int nbEchant = 0;
    private boolean fastMesures;
    private Calendar calendarnewserie = null;

    //Variables d'informations du jdialog v_jDialogChoiceNameEchant
    private String[] tabNameEchant = null;
    private double[] tabHautEchant = null;
    private double[] tabDiamEchant = null;

    private ArrayList<ArrayList<M_Mesure>> arrayOfArrayMesure = null;



    public C_ControlDialogSerie(V_JPanelMainRight v_jPanelMainRight) {
        this.v_jPanelMainRight = v_jPanelMainRight;
        resetVariables();
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        //Récolte le nom + nombre de hauteurs de la série
        if (e.getSource() == v_jDialogNouvelleSerie.getjButtonnext()){
            try {
                resetVariables();

                calendarnewserie = Calendar.getInstance();

                nameSerie += "_"+v_jDialogNouvelleSerie.getjTextFieldNomSerie().getText();
                nbEchant = Integer.parseInt(v_jDialogNouvelleSerie.getjTextFieldNombreEchantillons().getText());
                fastMesures = v_jDialogNouvelleSerie.getStateJCheckBoxFastMesure();

                v_jDialogNouvelleSerie.dispose();

                v_jPanelMainRight.displayJDialogChoiceNameCar();

            }catch (Exception exception) {
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }

        else if (e.getSource() == v_jDialogChoiceNameEchant.getjButtonnext()){
            try{
                tabNameEchant = new String[nbEchant];
                for (int i = 0; i<nbEchant; ++i){
                    tabNameEchant[i] = v_jDialogChoiceNameEchant.getjTextFieldNom_tab()[i].getText();
                }

                v_jDialogChoiceNameEchant.dispose();

                v_jPanelMainRight.displayJDialogHautDiam();

            }catch(Exception exception) {
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }

        else if (e.getSource() == v_JDialogChoiceHautDiam.getjButtonnext()) {
            try{
                tabDiamEchant = new double[nbEchant];
                tabHautEchant = new double[nbEchant];

                for (int i = 0; i<nbEchant; ++i){
                    tabNameEchant[i] = v_jDialogChoiceNameEchant.getjTextFieldNom_tab()[i].getText();

                    tabHautEchant[i] = Double.parseDouble(v_JDialogChoiceHautDiam.getjTextFieldHaut_tab()[i].getText());
                    tabDiamEchant[i] = Double.parseDouble(v_JDialogChoiceHautDiam.getjTextFieldDiam_tab()[i].getText());
                }

                v_JDialogChoiceHautDiam.dispose();


                v_jPanelMainRight.displayInfoFinFillEchant();
            }catch(Exception exception) {
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }

        else if (e.getSource() == v_JDialogInfoFinFillEchant.getJButtonNext()){
            v_jPanelMainRight.loopAcquisitionMasse(getNbEchant(), fastMesures, tabNameEchant);
            v_JDialogInfoFinFillEchant.dispose();
        }
        else if (e.getSource() == v_jDialogMasse.getJButtonOkManuel()){
            try {
                if (arrayOfArrayMesure==null){
                    arrayOfArrayMesure = new ArrayList<>();
                    for (int i= 0; i<nbEchant; ++i)
                        arrayOfArrayMesure.add(new ArrayList<M_Mesure>());
                }

                //récup idechantillon
                int id = v_jDialogMasse.getIdCar();

                //Récup de la masse
                double valMasse = Double.parseDouble(v_jDialogMasse.getJtextfieldValMan());

                //création de la mesure et on ajoute que la masse pour le moment
                M_Mesure m_mesure = new M_Mesure();
                m_mesure.setMasse(valMasse);

                //importation de la date
                Calendar calendar = Calendar.getInstance();
                m_mesure.setDateHeure(calendar);

                //on ajoute la mesure à arraylist de l'idcar
                arrayOfArrayMesure.get(id).add(m_mesure);

                v_jDialogMasse.dispose();
            }catch (Exception exc){
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }
        else if (e.getSource() == v_JDialogFrange.getbuttonOk()){
            try{
                int id = v_JDialogFrange.getIdEchant();
                arrayOfArrayMesure.get(id).get(arrayOfArrayMesure.get(id).size()-1).setHauteurFrangeHumide(Double.parseDouble(v_JDialogFrange.getFrangeHu()));

                v_JDialogFrange.dispose();
            }catch (Exception e1){
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }

        else if (e.getSource()==v_JDialogHeure.getButtonOk()){
            try{
                int id = v_JDialogHeure.getIdEchant();
                setM_mesureHeure(id, v_JDialogHeure.getJTextfield());


                v_JDialogHeure.dispose();
            }catch(Exception e2){
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }

    }

    public void resetVariables() {
        nameSerie = "Serie";
        nbEchant = 0;
        fastMesures = false;

        tabNameEchant = null;
        tabHautEchant= null;
        tabDiamEchant = null;

        calendarnewserie = null;

        arrayOfArrayMesure = null;
    }

    /*
    * Getters Setters
    * */

    public void setV_jDialogNouvelleSerie(V_JDialogNouvelleSerie v_jDialogNouvelleSerie) {
        resetVariables();
        this.v_jDialogNouvelleSerie = v_jDialogNouvelleSerie;
    }

    public void setV_jDialogNouvelleSerie(V_JDialogChoiceNameEchant v_jDialogChoiceNameEchant) {
        this.v_jDialogChoiceNameEchant = v_jDialogChoiceNameEchant;
    }

    public void setV_jDialogNouvelleSerie(V_JDialogChoiceHautDiam v_JDialogChoiceHautDiam) {
        this.v_JDialogChoiceHautDiam = v_JDialogChoiceHautDiam;
    }

    public void setV_jDialogNouvelleSerie(V_JDialogInfoFinFillEchant v_JDialogInfoFinFillEchant) {
        this.v_JDialogInfoFinFillEchant = v_JDialogInfoFinFillEchant;
    }

    public void setV_jDialogNouvelleSerie(V_jDialogMasse v_jDialogNouvelleSerie) {
        this.v_jDialogMasse = v_jDialogNouvelleSerie;
    }

    public void setV_jDialogNouvelleSerie(V_JDialogFrange v_JDialogFrange) {
        this.v_JDialogFrange = v_JDialogFrange;
    }

    public void setV_jDialogNouvelleSerie(V_JDialogHeure v_JDialogHeure) {
        this.v_JDialogHeure = v_JDialogHeure;
    }


    public int getNbEchant() {return nbEchant;}

    public String getHeureM_mesure(int idCar) {
        return arrayOfArrayMesure.get(idCar).get(arrayOfArrayMesure.get(idCar).size()-1).getHeureMesure();
    }

    public void setM_mesureHeure(int i, String newHeure){
        M_Mesure m_mesure = arrayOfArrayMesure.get(i).get(arrayOfArrayMesure.get(i).size()-1);
        try {
            m_mesure.setHeureMesure(m_mesure.getDateMesure(), newHeure);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /*
    * Fonction de test d'affichage
    * */
    @Override
    public String toString() {
        String affichage = "";
        affichage += "Nom de la série : "+nameSerie+"\n";
        affichage += "Nombre d'échantillons : "+nbEchant+"\n";
        affichage += "Mesure rapide : "+fastMesures+"\n";
        affichage += "Nom des échantillons : \n";
        for (int i = 0; i<nbEchant; ++i) {
            affichage += "     "+i+" : "+tabNameEchant[i]+ "    Haut : "+tabHautEchant[i]+"     Diam : "+tabDiamEchant[i]+"\n";
            for (int y = 0; y<arrayOfArrayMesure.get(i).size();++y)
                affichage +="      masse : "+arrayOfArrayMesure.get(i).get(y).getMasse()+"      HFH : "+ arrayOfArrayMesure.get(i).get(y).getHauteurFrangeHumide()+ "       Date/Heure : " +arrayOfArrayMesure.get(i).get(y).getDateMesure()+" - "+arrayOfArrayMesure.get(i).get(y).getHeureMesure()+"\n";
        }

        return affichage;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 32){
            if (arrayOfArrayMesure==null){
                arrayOfArrayMesure = new ArrayList<>();
                for (int i= 0; i<nbEchant; ++i)
                    arrayOfArrayMesure.add(new ArrayList<M_Mesure>());
            }
            try{
                //récup idechantillon
                int id = v_jDialogMasse.getIdCar();

                //Récup de la masse
                double valMasse = Double.parseDouble(v_jDialogMasse.getJLabelBalance());

                //création de la mesure et on ajoute que la masse pour le moment
                M_Mesure m_mesure = new M_Mesure();
                m_mesure.setMasse(valMasse);

                //importation de la date
                Calendar calendar = Calendar.getInstance();
                m_mesure.setDateHeure(calendar);

                //on ajoute la mesure à arraylist de l'idcar
                arrayOfArrayMesure.get(id).add(m_mesure);

                v_jDialogMasse.dispose();
            }catch(Exception exception){
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }

        }
        else if (e.getKeyCode() == 77) {
            v_jDialogMasse.setManuel();
        }
    }

    public ArrayList<ArrayList<M_Mesure>> getDonnees() {
        return arrayOfArrayMesure;
    }

    public Calendar getCalendarSerie() {
        return calendarnewserie;
    }

    public String getNomSerie() {
        return nameSerie;
    }

    public String[] getTabNomechant() {
        return tabNameEchant;
    }

    public double[] getTabHautEchant() {
        return tabHautEchant;
    }

    public double[] getTabDiamEchant() {
        return tabDiamEchant;
    }
}
