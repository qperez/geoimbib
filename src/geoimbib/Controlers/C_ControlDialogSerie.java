package geoimbib.Controlers;

import geoimbib.IO.IO_SyncBulkTransfer;
import geoimbib.Models.M_Carotte;
import geoimbib.Models.M_Mesure;
import geoimbib.Models.M_Serie;
import geoimbib.Views.JDialogs.*;
import geoimbib.Views.JPanels.V_JPanelMainRight;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Contr&ocirc;leur principal de la cr&eacute;ation de s&eacute;rie et modification, impl&eacute;mente ActionListener et KeyListener
 */
public class C_ControlDialogSerie implements ActionListener, KeyListener {



    /*Variables de cr&eacute;ation de s&eacute;rie*/
    private V_JPanelMainRight v_jPanelMainRight = null;

    private V_jDialogMasse v_jDialogMasse = null;
    private V_JDialogNouvelleSerie v_jDialogNouvelleSerie = null;
    private V_JDialogChoiceNameEchant v_jDialogChoiceNameEchant = null;
    private V_JDialogChoiceHautDiam v_JDialogChoiceHautDiam = null;
    private V_JDialogInfoFinFillEchant v_JDialogInfoFinFillEchant = null;
    private V_JDialogFrange v_JDialogFrange = null;
    private V_JDialogHeure v_JDialogHeure;
    private V_JDialogRecap v_JDialogRecap = null;

    //Variables d'informations du jdialog v_jDialogNouvelleSerie
    private String nameSerie = "Serie";
    private int nbEchant = 0;
    private boolean fastMesures;

    private Calendar calendarnewserie = null;






    //remaniement des donn&eacute;es
    private M_Serie m_serie;
    private String tmpHour;
    private boolean firstMesure = true;


    public C_ControlDialogSerie(V_JPanelMainRight v_jPanelMainRight) {
        this.v_jPanelMainRight = v_jPanelMainRight;
        resetVariables();
    }



    @Override
    public void actionPerformed(ActionEvent e) {


        /*
        * Cr&eacute;ation de s&eacute;rie
        * */
        //R&eacute;colte le nom + nombre de hauteurs de la s&eacute;rie
         if (e.getSource() == v_jDialogNouvelleSerie.getjButtonnext()){
            try {
                resetVariables();

                calendarnewserie = Calendar.getInstance();

                nbEchant = Integer.parseInt(v_jDialogNouvelleSerie.getjTextFieldNombreEchantillons().getText());

                m_serie = new M_Serie();

                m_serie.setNom(nameSerie += "_"+v_jDialogNouvelleSerie.getjTextFieldNomSerie().getText());

                ArrayList<M_Carotte> listCarottes = new ArrayList<>();
                for (int i = 0; i<nbEchant; ++i)
                    listCarottes.add(new M_Carotte());

                m_serie.setListCarotte(listCarottes);

                v_jDialogNouvelleSerie.dispose();

                v_jPanelMainRight.displayJDialogChoiceNameCar();

            }catch (Exception exception) {
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }

        else if (e.getSource() == v_jDialogChoiceNameEchant.getjButtonnext()){
            try{
                for (int i = 0; i<nbEchant; ++i){
                   m_serie.getListCarotte().get(i).setNom(v_jDialogChoiceNameEchant.getjTextFieldNom_tab()[i].getText());

                }

                v_jDialogChoiceNameEchant.dispose();

                v_jPanelMainRight.displayJDialogHautDiam();

            }catch(Exception exception) {
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }






        else if (e.getSource() == v_JDialogChoiceHautDiam.getjButtonnext()) {
            try{
                for (int i = 0; i<nbEchant; ++i){
                    m_serie.getListCarotte().get(i).setDiametre(Double.parseDouble(v_JDialogChoiceHautDiam.getjTextFieldDiam_tab()[i].getText()));
                    m_serie.getListCarotte().get(i).setLongueur(Double.parseDouble(v_JDialogChoiceHautDiam.getjTextFieldHaut_tab()[i].getText()));
                }

                v_JDialogChoiceHautDiam.dispose();


                v_jPanelMainRight.displayInfoFinFillEchant(this);
            }catch(Exception exception) {
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }





        else if (e.getSource() == v_JDialogInfoFinFillEchant.getJButtonNext()){
            fastMesures = v_JDialogInfoFinFillEchant.getStateJCheckBoxFastMesure();

            v_jPanelMainRight.loopAcquisitionMasse(getNbEchant(), fastMesures, m_serie.getListCarotte(), this);
            v_JDialogInfoFinFillEchant.dispose();
        }




/****************************************************
 * DEBUT DE LOOPACQUISITION
 */
        else if (e.getSource()==v_JDialogHeure.getButtonOk()){
            try{
                tmpHour = v_JDialogHeure.getJTextfield();
                v_JDialogHeure.dispose();
            }catch(Exception e2){
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }

        else if (e.getSource() == v_jDialogMasse.getJButtonOkManuel()){
            try {
                if (firstMesure){
                    for (int i= 0; i<nbEchant; ++i)
                        m_serie.getListCarotte().get(i).setListMesures(new ArrayList<M_Mesure>());
                    firstMesure = false;
                }

                //r&eacute;cup idechantillon
                int id = v_jDialogMasse.getIdCar();

                //R&eacute;cup de la masse
                double valMasse = Double.parseDouble(v_jDialogMasse.getJtextfieldValMan());

                //cr&eacute;ation de la mesure et on ajoute que la masse pour le moment
                M_Mesure m_mesure = new M_Mesure(valMasse);

                //on ajoute la mesure &agrave; arraylist de l'idcar
                m_serie.getListCarotte().get(id).getListMesures().add(m_mesure);


                v_jDialogMasse.dispose();
            }catch (Exception exc){
                System.out.println(exc);
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }





        else if (e.getSource() == v_JDialogFrange.getbuttonOk()){
            try{
                int id = v_JDialogFrange.getIdEchant();
                m_serie.getListCarotte().get(id).getListMesures().get(m_serie.getListCarotte().get(id).getListMesures().size()-1).setHauteurFrangeHumide(Double.parseDouble(v_JDialogFrange.getFrangeHu().replace(",", ".")));
                v_JDialogFrange.dispose();
            }catch (Exception e1){
                System.out.println(e1);
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }

         else if (e.getSource() == v_JDialogRecap.getButtonVal()){
             try{
                 v_JDialogRecap.dispose();
             }catch (Exception e1){
                 System.out.println(e1);
                 v_jPanelMainRight.displayJDialogErrorinputNewSerie();
             }
         }

         else if (e.getSource() == v_JDialogRecap.getButtonGraph()) {
             new V_JDialogGraph(
                     this.v_jPanelMainRight.getV_mainWindow(),
                     "Graphique",
                     true,
                     v_JDialogRecap.getCarotte(),
                     v_jPanelMainRight
             );
         }

    }


    /**
     * Boucle d'assignation des heures
     */
    public void loopAssignHourArrayMesure(int ii){
        try {
            int index;
            String date = "";
            for (int i = 0; i<m_serie.getListCarotte().size(); ++i){
                System.out.println(m_serie.getListCarotte().get(ii));
                index = m_serie.getListCarotte().get(ii).getListMesures().size()-1;
                date = m_serie.getListCarotte().get(ii).getListMesures().get(index).getDateMesure();
                m_serie.getListCarotte().get(ii).getListMesures().get(index).setHeureMesure(date, tmpHour);

                for (int y=0; y<m_serie.getListCarotte().get(ii).getListMesures().size(); ++y){
                    if (y==0)
                        m_serie.getListCarotte().get(ii).getListMesures().get(y).setTemps(0);
                    else{
                        Date h1 = m_serie.getListCarotte().get(ii).getListMesures().get(y).getDateHeure().getTime();
                        Date h2 = m_serie.getListCarotte().get(ii).getListMesures().get(y-1).getDateHeure().getTime();
                        double diff = getDiffTimeTwoEchant(h1, h2);
                        m_serie.getListCarotte().get(ii).getListMesures().get(y).setTemps(diff+m_serie.getListCarotte().get(ii).getListMesures().get(y-1).getTemps());
                    }
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * M&eacute;thode de r&eacute;initialisation des variables
     */
    public void resetVariables() {
        nameSerie = "Serie";
        nbEchant = 0;
        fastMesures = false;
        firstMesure = true;


        calendarnewserie = null;

    }


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

    public void setV_jDialogNouvelleSerie(V_JDialogRecap v_jDialogRecap) {
        this.v_JDialogRecap = v_jDialogRecap;
    }

    public void setV_jDialogNouvelleSerie(V_JDialogFrange v_JDialogFrange) {
        this.v_JDialogFrange = v_JDialogFrange;
    }

    public void setV_jDialogNouvelleSerie(V_JDialogHeure v_JDialogHeure) {
        this.v_JDialogHeure = v_JDialogHeure;
    }


    public int getNbEchant() {return nbEchant;}



    /**
     * M&eacute;thode d'affichage
     * */
    @Override
    public String toString() {
        String affichage = "";
        affichage += "Nom de la série : "+nameSerie+"\n";
        affichage += "Nombre d'échantillons : "+nbEchant+"\n";
        affichage += "Mesure rapide : "+fastMesures+"\n";
        affichage += "Nom des échantillons : \n";
        for (int i = 0; i<nbEchant; ++i) {
            affichage += "     "+i+" : "+m_serie.getListCarotte().get(i).getNom()+ "    Haut : "+m_serie.getListCarotte().get(i).getLongueur()+"     Diam : "+m_serie.getListCarotte().get(i).getDiametre()+"\n";
            for (int y = 0; y<m_serie.getListCarotte().get(i).getListMesures().size();++y)
                affichage +="      masse : "+m_serie.getListCarotte().get(i).getListMesures().get(y).getMasse()+"      HFH : "+ m_serie.getListCarotte().get(i).getListMesures().get(y).getHauteurFrangeHumide()+ "       Date/Heure : " +m_serie.getListCarotte().get(i).getListMesures().get(y).getDateMesure()+" - "+m_serie.getListCarotte().get(i).getListMesures().get(y).getHeureMesure()+"\n";
        }

        return affichage;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * M&eacute;thode d'&eacute;coute d'appuie sur la touhe espace et la touche m
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == 32){
            if (firstMesure){
                for (int i= 0; i<nbEchant; ++i)
                    m_serie.getListCarotte().get(i).setListMesures(new ArrayList<M_Mesure>());
                firstMesure = false;
            }
            try{
                //r&eacute;cup idechantillon
                int id = v_jDialogMasse.getIdCar();


                //R&eacute;cup de la masse
                double valMasse = Double.parseDouble(v_jDialogMasse.getJlabelDoubleValeurBalance().getText());
                v_jDialogMasse.getThreadJlabelValeurBalance().stopThread();

                //cr&eacute;ation de la mesure et on ajoute que la masse pour le moment
                M_Mesure m_mesure = new M_Mesure(valMasse);

                //on ajoute la mesure &agrave; arraylist de l'idcar
                m_serie.getListCarotte().get(id).getListMesures().add(m_mesure);

                v_jDialogMasse.dispose();
            }catch(Exception exception){
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }

        }
        else if (e.getKeyCode() == 77) {
            v_jDialogMasse.getThreadJlabelValeurBalance().stopThread();
            v_jDialogMasse.setManuel();
        }
    }


    public M_Serie getM_serie(){return m_serie;}

    /**
     * Retourne une nouvelle instance de l'object Calendar
     * @return Calendar
     */
    public Calendar getNewCalendarSerie() {
        return Calendar.getInstance();
    }

    /**
     * Retourne la diff&eacute;rence de date (heure) entre deux dates.
     * @param d1
     * @param d2
     * @return
     */
    private double getDiffTimeTwoEchant(Date d1, Date d2){
        return (d1.getTime() - d2.getTime()) /(1000.0*60.0) /60.0;
    }
}
