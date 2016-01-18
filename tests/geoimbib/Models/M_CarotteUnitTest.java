package geoimbib.Models;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        M_Carotte carotte = new M_Carotte("CarotteToTest", 3.2, 15.2, 398.4);
        assertEquals(Math.PI * Math.pow(1.6, 2), carotte.calculSurface());
    }

    @Test
    public void testCalculMoyenneCarotte(){
        M_Mesure mesure1 = new M_Mesure(Calendar.getInstance(), 12.1);
        M_Mesure mesure2 = new M_Mesure(Calendar.getInstance(), 4.4);
        M_Mesure mesure3 = new M_Mesure(10.5);

        ArrayList<M_Mesure> listMesureCarotte1 = new ArrayList<M_Mesure>();
        listMesureCarotte1.add(mesure1);
        listMesureCarotte1.add(mesure2);
        listMesureCarotte1.add(mesure3);

        M_Carotte carotte = new M_Carotte("CarotteToTest", 5, 10, 150,listMesureCarotte1);
        assertEquals("La moyenne des mesures de la carotte n'est pas celle attendue", 9.0, carotte.calulMoyenneMesure());
    }

}
