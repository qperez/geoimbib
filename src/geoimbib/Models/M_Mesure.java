package geoimbib.Models;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <b>Created by Quentin PEREZ on 12/01/16.</b>
*/
public class M_Mesure {
    private Calendar dateHeure;
    private double hauteurFrangeHumide;

    /**
     *
     */
    public M_Mesure(){}

    public M_Mesure(double hauteurFrangeHumide){
        this.hauteurFrangeHumide = hauteurFrangeHumide;
        dateHeure = Calendar.getInstance();

    }

    public M_Mesure(Calendar dateHeure, double hauteurFrangeHumide) {
        this.dateHeure = dateHeure;
        this.hauteurFrangeHumide = hauteurFrangeHumide;
    }

    public Calendar getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Calendar dateHeure) {
        this.dateHeure = dateHeure;
    }


    public String getDateMesure() {
        String dateFormate = new SimpleDateFormat("dd/MM/yyyy").format(dateHeure.getTime());
        return dateFormate;
    }

    public String getHeureMesure() {
        String dateFormate = new SimpleDateFormat("hh:mm").format(dateHeure.getTime());
        return dateFormate;
    }
}
