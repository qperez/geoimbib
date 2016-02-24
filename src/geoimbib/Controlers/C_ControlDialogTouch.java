package geoimbib.Controlers;

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

/**
 * Created by ravier on 24/02/2016.
 */
public class C_ControlDialogTouch implements ActionListener, KeyListener {
    private V_JPanelMainRight v_jPanelMainRight;
    private V_JDialogTouch v_jDialogTouch;
    private V_JDialogInfoFinFillEchant v_JDialogInfoFinFillEchant;
    private V_JDialogHeure v_jDialogHour;
    private V_jDialogMasse v_jDialogMasse;
    private V_JDialogFrange v_jDialogFrange;

    private ArrayList<String> arrayListName;
    private int nbEchantToUpdate;
    private ArrayList<M_Carotte> arrayListM_carotte;
    private boolean fastMesures;

    private Calendar calendarnewserie = null;
    private String tmpHour;
    private boolean firstMesure = true;



    public C_ControlDialogTouch(V_JPanelMainRight v_jPanelMainRight){
        this.v_jPanelMainRight = v_jPanelMainRight;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        * Modification de série
        * */

        if (e.getSource() == v_jDialogTouch.getButonNext()){
            try {
                arrayListName = new ArrayList<>();
                for (int i=0; i<v_jDialogTouch.getNameEchant().length; ++i){
                    if (v_jDialogTouch.getJCHeckbokAtIndex(i)){
                        arrayListName.add(v_jDialogTouch.getNameEchant()[i]);
                        System.out.println(v_jDialogTouch.getNameEchant()[i]);
                    }
                }
                nbEchantToUpdate = arrayListName.size();

                arrayListM_carotte = new ArrayList<>();
                for (int i = 0; i<nbEchantToUpdate; ++i)
                    arrayListM_carotte.add(new M_Carotte());

                calendarnewserie = Calendar.getInstance();

                v_jDialogTouch.dispose();

                v_jPanelMainRight.displayInfoFinFillEchant(this);


            }catch (Exception exception) {
                v_jDialogTouch.displayJDialogErrorUpdateSet();
            }
        }


        else if (e.getSource() == v_JDialogInfoFinFillEchant.getJButtonNext()){
            fastMesures = v_JDialogInfoFinFillEchant.getStateJCheckBoxFastMesure();

            v_jPanelMainRight.loopAcquisitionMasse(nbEchantToUpdate, fastMesures, arrayListM_carotte, this);

            v_JDialogInfoFinFillEchant.dispose();
        }

        /****************************************************
         * DEBUT DE LOOPACQUISITION
         */
        else if (e.getSource()==v_jDialogHour.getButtonOk()){
            try{
                tmpHour = v_jDialogHour.getJTextfield();
                v_jDialogHour.dispose();
            }catch(Exception e2){
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }

        else if (e.getSource() == v_jDialogMasse.getJButtonOkManuel()){
            try {
                if (firstMesure){
                    for (int i= 0; i<nbEchantToUpdate; ++i)
                        arrayListM_carotte.get(i).setListMesures(new ArrayList<M_Mesure>());
                    firstMesure = false;
                }

                //récup idechantillon
                int id = v_jDialogMasse.getIdCar();

                //Récup de la masse
                double valMasse = Double.parseDouble(v_jDialogMasse.getJtextfieldValMan());

                //création de la mesure et on ajoute que la masse pour le moment
                M_Mesure m_mesure = new M_Mesure(valMasse);

                //on ajoute la mesure à arraylist de l'idcar
                arrayListM_carotte.get(id).getListMesures().add(m_mesure);


                v_jDialogMasse.dispose();
            }catch (Exception exc){
                System.out.println(exc);
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }

        else if (e.getSource() == v_jDialogFrange.getbuttonOk()){
            try{
                int id = v_jDialogFrange.getIdEchant();
                arrayListM_carotte.get(id).getListMesures().get(arrayListM_carotte.get(id).getListMesures().size()-1).setHauteurFrangeHumide(Double.parseDouble(v_jDialogFrange.getFrangeHu().replace(",", ".")));
                v_jDialogFrange.dispose();
            }catch (Exception e1){
                System.out.println(e1);
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }
    }

    public void setV_jDialogTouch(V_JDialogTouch v_jDialogTouch){this.v_jDialogTouch=v_jDialogTouch;}

    public void setV_jDialogInfoFillEchant(V_JDialogInfoFinFillEchant v_JDialogInfoFinFillEchant) {
        this.v_JDialogInfoFinFillEchant = v_JDialogInfoFinFillEchant;
    }

    public void setV_jDialogHour(V_JDialogHeure v_jDialogHour) {
        this.v_jDialogHour = v_jDialogHour;
    }

    public Calendar getCalendarSerie() {
        return calendarnewserie;
    }

    public void setV_jDialogMasse(V_jDialogMasse v_jDialogMasse) {
        this.v_jDialogMasse = v_jDialogMasse;
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

                if (firstMesure){
                    for (int i= 0; i<nbEchantToUpdate; ++i)
                        arrayListM_carotte.get(i).setListMesures(new ArrayList<M_Mesure>());
                    firstMesure = false;
                }
            try {
                //récup idechantillon
                int id = v_jDialogMasse.getIdCar();

                //Récup de la masse
                double valMasse = Double.parseDouble(v_jDialogMasse.getJtextfieldValMan());

                //création de la mesure et on ajoute que la masse pour le moment
                M_Mesure m_mesure = new M_Mesure(valMasse);

                //on ajoute la mesure à arraylist de l'idcar
                arrayListM_carotte.get(id).getListMesures().add(m_mesure);


                v_jDialogMasse.dispose();
            }catch (Exception exc){
                System.out.println(exc);
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }
        else if (e.getKeyCode() == 77) {
            v_jDialogMasse.setManuel();
        }
    }

    public void setV_jDialogFrange(V_JDialogFrange v_jDialogFrange) {
        this.v_jDialogFrange = v_jDialogFrange;
    }

    public ArrayList<M_Carotte> getArrayCarottes() {
        return arrayListM_carotte;
    }

    public ArrayList<String> getArrayNameUpdate() {
        return arrayListName;
    }

    public void loopAssignHourArrayMesure() {
        try {
            int index;
            String date = "";
            for (int i = 0; i<arrayListM_carotte.size(); ++i){
                index = arrayListM_carotte.get(i).getListMesures().size()-1;
                date = arrayListM_carotte.get(i).getListMesures().get(index).getDateMesure();
                arrayListM_carotte.get(i).getListMesures().get(index).setHeureMesure(date, tmpHour);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
