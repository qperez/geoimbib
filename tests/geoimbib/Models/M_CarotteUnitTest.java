package geoimbib.Models;

import geoimbib.M_Carotte;
import org.junit.Test;

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
    public void testSurface(){
        M_Carotte carotte = new M_Carotte("CarotteToTest", 3.2, 15.2 ,398.4);
        assertEquals(Math.PI * Math.pow(1.6,2), carotte.calculSurface());
    }

}
