package geoimbib.Controlers;

import geoimbib.Views.JDialogs.V_JDialogChoiceNameEchant;
import geoimbib.Views.JDialogs.V_JDialogNouvelleSerie;
import geoimbib.Views.JPanels.V_JPanelMainRight;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ravier on 18/01/2016.
 */
public class C_ControlDialogSerie implements ActionListener {

    private V_JPanelMainRight v_jPanelMainRight = null;

    private V_JDialogNouvelleSerie v_jDialogNouvelleSerie = null;
    private V_JDialogChoiceNameEchant v_jDialogChoiceNameEchant = null;

    //Variables d'informations du jdialog v_jDialogNouvelleSerie
    private String nameSerie = "Serie";
    private int nbEchant;
    private boolean fastMesures;
    //Variables d'informations du jdialog v_jDialogChoiceNameEchant
    private String[] tabNameEchant = null;

    public C_ControlDialogSerie(V_JPanelMainRight v_jPanelMainRight) {
        this.v_jPanelMainRight = v_jPanelMainRight;
        resetVariables();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        //Récolte le nom + nombre de hauteurs de la série
        if (e.getSource() == v_jDialogNouvelleSerie.getjButtonnext()){
            try {
                nameSerie += "_"+v_jDialogNouvelleSerie.getjTextFieldNomSerie().getText();
                nbEchant = Integer.parseInt(v_jDialogNouvelleSerie.getjTextFieldNombreEchantillons().getText());
                fastMesures = v_jDialogNouvelleSerie.getStateJCheckBoxFastMesure();

                v_jDialogNouvelleSerie.dispose();

                v_jPanelMainRight.displayJDialogChoiceNameCar();
            }catch (Exception exception) {
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }

        if (e.getSource() == v_jDialogChoiceNameEchant.getjButtonnext()){
            try{
                tabNameEchant = new String[nbEchant];
                for (int i = 0; i<nbEchant; ++i){
                    tabNameEchant[i] = v_jDialogChoiceNameEchant.getjTextFieldNom_tab()[i].getText();
                }

                v_jDialogChoiceNameEchant.dispose();

                //Test d'affichage pour debug
                System.out.println(toString());
            }catch(Exception exception) {
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }
    }

    public void resetVariables() {
        nameSerie = "Serie";
        nbEchant = 0;
        fastMesures = false;

        this.v_jDialogNouvelleSerie = null;
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

    public int getNbEchant() {return nbEchant;}


    //Fonction de test d'affichage
    @Override
    public String toString(){
        String affichage = "";
        affichage += "Nom de la série : "+nameSerie+"\n";
        affichage += "Nombre d'échantillons : "+nbEchant+"\n";
        affichage += "Mesure rapide : "+fastMesures+"\n";
        affichage += "Nom des échantillons : \n";
        for (int i = 0; i<nbEchant; ++i) {
            affichage += "     "+i+" : "+tabNameEchant[i]+"\n";
        }

        return affichage;
    }
}
