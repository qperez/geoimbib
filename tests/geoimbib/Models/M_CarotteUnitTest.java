package geoimbib.Models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by quentin on 12/01/16.
 */
public class M_CarotteUnitTest {

    /*
     Test du calcul de la surface d'une carotte
     Diametre carottre = 3.2
     Résultat attendu = 1.6^2 * Pi
     */
    @Test
    public void testCalculSurface() {
        M_Carotte carotte = new M_Carotte("CarotteToTest", 3.2, 15.2);
        assertEquals(Math.PI * Math.pow(1.6, 2), carotte.calculSurface());
    }

    @Test
    public void testCalculMoyenneMesureHauteursCarotte(){
        M_Mesure mesure1 = new M_Mesure(Calendar.getInstance(), 12.1);
        M_Mesure mesure2 = new M_Mesure(Calendar.getInstance(), 4.4);
        M_Mesure mesure3 = new M_Mesure(10.5);

        ArrayList<M_Mesure> listMesureCarotte1 = new ArrayList<M_Mesure>();
        listMesureCarotte1.add(mesure1);
        listMesureCarotte1.add(mesure2);
        listMesureCarotte1.add(mesure3);

        M_Carotte carotte = new M_Carotte("CarotteToTest", 5, 10,listMesureCarotte1);
        assertEquals("La moyenne des mesures de la carotte n'est pas celle attendue", 9.0, carotte.calulMoyenneMesureHauteurs());
    }

    @Test
    public void testCalculDeltaMasseMesures(){
        M_Mesure mesure1 = new M_Mesure(Calendar.getInstance(), 0, 0);
        M_Mesure mesure2 = new M_Mesure(Calendar.getInstance(), 5, 4.4);
        M_Mesure mesure3 = new M_Mesure(Calendar.getInstance(), 5, 7.9);
        M_Mesure mesure4 = new M_Mesure(Calendar.getInstance(), 5, 12.8);

        ArrayList<M_Mesure> listMesureCarotte = new ArrayList<M_Mesure>();
        listMesureCarotte.add(mesure1);
        listMesureCarotte.add(mesure2);
        listMesureCarotte.add(mesure3);
        listMesureCarotte.add(mesure4);
        M_Carotte carotte = new M_Carotte("CarotteToTest", 5, 10,listMesureCarotte);

        ArrayList<Double> listDeltaExpected = new ArrayList<>();
        listDeltaExpected.add(0.0);
        listDeltaExpected.add(4.4);
        listDeltaExpected.add(3.5);
        listDeltaExpected.add(4.9);
        assertEquals("La liste des deltas attendues n'est pas celle retournée", listDeltaExpected, carotte.calulDeltaMasseMesures());
    }

    @Test
    public void testCalculDeltaMasseMesuresAvecListeMesureVide(){
        M_Carotte carotte = new M_Carotte("CarotteToTest", 5, 10);
        ArrayList<Double> listDeltaExpected = new ArrayList<>();
        assertEquals("La liste des deltas attendues n'est pas celle retournée", listDeltaExpected, carotte.calulDeltaMasseMesures());
    }

    @Test
    public void testCalculDeltaHauteurMesures(){
        M_Mesure mesure1 = new M_Mesure(Calendar.getInstance(), 0, 0);
        M_Mesure mesure2 = new M_Mesure(Calendar.getInstance(), 25.3, 4.4);
        M_Mesure mesure3 = new M_Mesure(Calendar.getInstance(), 30, 7.9);
        M_Mesure mesure4 = new M_Mesure(Calendar.getInstance(), 50.7, 12.8);

        ArrayList<M_Mesure> listMesureCarotte = new ArrayList<M_Mesure>();
        listMesureCarotte.add(mesure1);
        listMesureCarotte.add(mesure2);
        listMesureCarotte.add(mesure3);
        listMesureCarotte.add(mesure4);
        M_Carotte carotte = new M_Carotte("CarotteToTest", 5, 10,listMesureCarotte);

        ArrayList<Double> listDeltaExpected = new ArrayList<>();
        listDeltaExpected.add(0.0);
        listDeltaExpected.add(25.3);
        listDeltaExpected.add(30-25.3);
        listDeltaExpected.add(50.7-30);
        assertEquals("La liste des deltas attendues n'est pas celle retournée", listDeltaExpected, carotte.calulDeltaHauteurMesures());
    }

}
