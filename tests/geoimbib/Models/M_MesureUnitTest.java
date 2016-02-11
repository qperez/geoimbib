package geoimbib.Models;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.ParseException;
import java.util.Calendar;

import static junit.framework.TestCase.assertEquals;
/**
 * Created by quentin on 12/01/16.
 */
public class M_MesureUnitTest {

    private Calendar dateCurrent;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp(){
        dateCurrent = Calendar.getInstance();
    }

    @Test
    public void testGetDateMesure(){
        //Date dateCurrent = new Date(116 + 1900, 01, 15, 00, 10, 59);
        dateCurrent.set(2016,Calendar.JANUARY,15);
        M_Mesure mesure = new M_Mesure(dateCurrent, 12.3);
        assertEquals("La date de la mesure n'est pas égale à celle attendue",
                "15/01/2016", mesure.getDateMesure());
    }

    @Test
    public void testGetHeureMesure(){
        dateCurrent.set(2016,Calendar.JANUARY,15,13,28,30);
        M_Mesure mesure = new M_Mesure(dateCurrent, 12.3);
        assertEquals("L'heure de la mesure n'est pas égale à celle attendue",
                "13:28", mesure.getHeureMesure());
    }

    @Test
    public void testRacineCarreTemps(){
        dateCurrent.set(2016,Calendar.JANUARY,15);
        M_Mesure mesure = new M_Mesure(dateCurrent, 12.3);
        mesure.setTemps(23.0);
        assertEquals("La racine carré du temps attendue ne correspond pas avec celle donnée",mesure.getRacineCarreTemps(),Math.sqrt(23.0));
    }

    @Test
    public void testSetHeureMesure() throws ParseException {
        dateCurrent.set(2016,Calendar.JANUARY,15);
        M_Mesure mesure = new M_Mesure(dateCurrent, 12.3);
        mesure.setHeureMesure("15/01/2016", "13:28");

        Calendar calendarExpected = Calendar.getInstance();
        calendarExpected.set(2016,Calendar.JANUARY,15,13,28,00);
        assertEquals("L'heure mise à jour n'est pas correcte", calendarExpected.getTime().toString(), mesure.getDateHeure().getTime().toString());
    }

    @Test
    public void testSetHeureMesureAvecErreurParsingDate() throws ParseException {
        thrown.expect(ParseException.class);
        thrown.expectMessage("15/01-2016 13:28");

        dateCurrent.set(2016,Calendar.JANUARY,15);
        M_Mesure mesure = new M_Mesure(dateCurrent, 12.3);
        mesure.setHeureMesure("15/01-2016", "13:28");
    }

    @Test
    public void testSetHeureMesureAvecErreurParsingHeure() throws ParseException {
        thrown.expect(ParseException.class);
        thrown.expectMessage("15/01/2016 13**28");

        dateCurrent.set(2016,Calendar.JANUARY,15);
        M_Mesure mesure = new M_Mesure(dateCurrent, 12.3);
        mesure.setHeureMesure("15/01/2016", "13**28");
    }

    @Test
    public void testSetHeureMesureAvecHeureInvalide() throws ParseException {
        thrown.expect(ParseException.class);

        dateCurrent.set(2016,Calendar.JANUARY,15);
        M_Mesure mesure = new M_Mesure(dateCurrent, 12.3);
        mesure.setHeureMesure("15/01/2016", "28:28");
    }

    @Test
    public void testSetHeureMesureAvecDateInvalide() throws ParseException {
        thrown.expect(ParseException.class);

        dateCurrent.set(2016,Calendar.JANUARY,15);
        M_Mesure mesure = new M_Mesure(dateCurrent, 12.3);
        mesure.setHeureMesure("15/178/2016", "22:28");
    }

    @Test
    public void testSetHeureMesureAvecDateEtHeureInvalides() throws ParseException {
        thrown.expect(ParseException.class);

        dateCurrent.set(2016,Calendar.JANUARY,15);
        M_Mesure mesure = new M_Mesure(dateCurrent, 12.3);
        mesure.setHeureMesure("35/01/2016", "27:28");
    }

}
