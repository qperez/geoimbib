package geoimbib.Models;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static junit.framework.TestCase.assertEquals;
/**
 * Created by quentin on 12/01/16.
 */
public class M_MesureUnitTest {
    @Test
    public void testGetDateMesure(){
        //Date dateCurrent = new Date(116 + 1900, 01, 15, 00, 10, 59);
        Calendar dateCurrent = Calendar.getInstance();
        dateCurrent.set(2016,00,15);
        M_Mesure mesure = new M_Mesure(dateCurrent, 12.3);
        assertEquals("La date de la mesure n'est pas égale à celle attendue",
                "15/01/2016", mesure.getDateMesure());
    }

    @Test
    public void testGetHeureMesure(){
        Calendar dateCurrent = Calendar.getInstance();
        dateCurrent.set(2016,00,15,12,28,30);
        M_Mesure mesure = new M_Mesure(dateCurrent, 12.3);
        assertEquals("L'heure de la mesure n'est pas égale à celle attendue",
                "12:28", mesure.getHeureMesure());
    }
}
