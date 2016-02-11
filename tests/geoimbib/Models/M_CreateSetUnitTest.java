package geoimbib.Models;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import sun.security.krb5.internal.PAData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by quentin on 09/02/16.
 */
public class M_CreateSetUnitTest {

    private static String PATH_GEOIMBIB = "."+ File.separator +"Res"+ File.separator;
    private static String NAME_SERIE = "SerieToTest";

    @AfterClass
    public static void cleanUpDirectories() throws IOException {
        FileUtils.deleteDirectory(new File(PATH_GEOIMBIB + File.separator + NAME_SERIE));
    }

    @Test
    public void testConstructorM_CreateSet(){

        Calendar calendarTest = Calendar.getInstance();
        calendarTest.set(2016,Calendar.JANUARY,15,16,38,00);

        //Carotte numéro 1
        M_Mesure mesure1 = new M_Mesure(calendarTest, 0, 0);
        M_Mesure mesure2 = new M_Mesure(calendarTest, 4.4, 3.2);
        M_Mesure mesure3 = new M_Mesure(calendarTest, 12.3, 10.3);

        ArrayList<M_Mesure> listMesureCarotte1 = new ArrayList<M_Mesure>();
        listMesureCarotte1.add(mesure1);
        listMesureCarotte1.add(mesure2);
        listMesureCarotte1.add(mesure3);

        //Carotte numéro 2
        mesure1 = new M_Mesure(calendarTest, 0, 0);
        mesure2 = new M_Mesure(calendarTest, 2.3, 4.2);
        mesure3 = new M_Mesure(calendarTest, 10.7, 11.1);
        ArrayList<M_Mesure> listMesureCarotte2 = new ArrayList<M_Mesure>();
        listMesureCarotte2.add(mesure1);
        listMesureCarotte2.add(mesure2);
        listMesureCarotte2.add(mesure3);

        ArrayList<ArrayList<M_Mesure>> listMesures = new ArrayList<>();
        listMesures.add(listMesureCarotte1);
        listMesures.add(listMesureCarotte2);

        int nbEchantillons = 2;
        String[] tabNameEchantillon = {"echantillon_1", "echantillon_2"};
        double[] tabHauteurEchantillon = {9.6, 10.8};
        double[] tabDiamEchantillon = {3.2, 4};
        Calendar dateHeure = calendarTest;

        M_createSet m_createSet = new M_createSet(
                listMesures,
                NAME_SERIE,
                nbEchantillons,
                tabNameEchantillon,
                tabHauteurEchantillon,
                tabDiamEchantillon,
                dateHeure,
                PATH_GEOIMBIB);

        File actualFileEchantillon1 = new File("Res"+ File.separator +"SerieToTest"+ File.separator +"echantillon_1.csv");
        File expectedFileEchantillon1 = new File("tests"+ File.separator +"ressources"+ File.separator +"echantillon_1UnitTest.csv");
        File actualFileEchantillon2 = new File("Res"+ File.separator +"SerieToTest"+ File.separator +"echantillon_2.csv");
        File expectedFileEchantillon2 = new File("tests"+ File.separator +"ressources"+ File.separator +"echantillon_2UnitTest.csv");

        assertTrue("Le dossier " + NAME_SERIE + "n'a pas été créé",new File("Res"+ File.separator +"SerieToTest"+ File.separator).exists());
        assertTrue("Le fichier echantillon_1.csv n'a pas été créé",expectedFileEchantillon1.exists());
        assertTrue("Le fichier echantillon_2.csv n'a pas été créé",expectedFileEchantillon2.exists());

        assertThat(actualFileEchantillon1).hasSameContentAs(expectedFileEchantillon1);
    }
}
