package geoimbib.Controlers;

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

    //Variables d'informations du jdialog nouvelle série (nom + nb échantillons + mesure rapides ou non)
    private String nameSerie;
    private int nbEchant;
    private boolean fastMesures;

    public C_ControlDialogSerie(V_JPanelMainRight v_jPanelMainRight) {
        this.v_jPanelMainRight = v_jPanelMainRight;
        resetVariables();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(v_jDialogNouvelleSerie);
        System.out.println(v_jPanelMainRight);

        //Récolte le nom + nombre de hauteurs de la série
        if (e.getSource() == v_jDialogNouvelleSerie.getjButtonnext()){
            try {
                nameSerie = v_jDialogNouvelleSerie.getjTextFieldNomSerie().getText();
                nbEchant = Integer.parseInt(v_jDialogNouvelleSerie.getjTextFieldNombreEchantillons().getText());
                fastMesures = v_jDialogNouvelleSerie.getStateJCheckBoxFastMesure();

                v_jDialogNouvelleSerie.dispose();

                v_jPanelMainRight.displayJDialogChoiceNameCar();
            }catch (Exception exception) {
                v_jPanelMainRight.displayJDialogErrorinputNewSerie();
            }
        }
    }

    public void resetVariables() {
        nameSerie = "";
        nbEchant = 0;
        fastMesures = false;

        this.v_jDialogNouvelleSerie = null;
    }

    /*
    * Getters Setters
    * */

    public void setV_jDialogNouvelleSerie(V_JDialogNouvelleSerie v_jDialogNouvelleSerie) {
        this.v_jDialogNouvelleSerie = v_jDialogNouvelleSerie;
    }
}
