/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gjuho
 */
public class KassapaateTest {

    private Kassapaate kassa;
    private Maksukortti kortti;

    public KassapaateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void luotuOikein() {
        assertTrue("Kassassa pitää olla rahaa 1000.00€", kassa.kassassaRahaa() == 1000_00);
        assertTrue("Edullisia lounaita tulee olla aluksi myyty 0.", kassa.edullisiaLounaitaMyyty() == 0);
        assertTrue("Maukkaita lounaita tulee olla aluksi myyty 0.", kassa.maukkaitaLounaitaMyyty() == 0);
    }

    //KORTTIOSTO
    //-Käteistestit
    //--Edullisesti
    @Test
    public void syoEdullisestiToimiiKunRahaaTarpeeksi() {
        int vaihtoraha = kassa.syoEdullisesti(10_00);
        assertTrue("Kassassa täytyy olla tarpeeksi suuren maksun jälkeen 1002.40€ rahaa.", kassa.kassassaRahaa() == 1002_40);
        assertTrue("Myytyjen edullisten lounaiden määrä täytyy olla 1.", kassa.edullisiaLounaitaMyyty() == 1);
        assertTrue("Vaihtorahaa tulee antaa 7.60€", vaihtoraha == 7_60);
    }

    @Test
    public void syoEdullisestiToimiiKunRahaaEiTarpeeksi() {
        int vaihtoraha = kassa.syoEdullisesti(1_00);
        assertTrue("Kassassa täytyy olla riittämättömän maksun jälkeen edelleen 1000.00€.", kassa.kassassaRahaa() == 1000_00);
        assertTrue("Myytyjen edullisten lounaiden määrä täytyy olla 0.", kassa.edullisiaLounaitaMyyty() == 0);
        assertTrue("Vaihtorahaa tulee antaa 1.00€", vaihtoraha == 1_00);
    }

    //--Maukkaasti
    @Test
    public void syoMaukkaastiToimiiKunRahaaTarpeeksi() {
        int vaihtoraha = kassa.syoMaukkaasti(10_00);
        assertTrue("Kassassa täytyy olla tarpeeksi suuren maksun jälkeen 1004.00€ rahaa.", kassa.kassassaRahaa() == 1004_00);
        assertTrue("Myytyjen maukkaiden lounaiden määrä täytyy olla 1.", kassa.maukkaitaLounaitaMyyty() == 1);
        assertTrue("Vaihtorahaa tulee antaa 6.00€", vaihtoraha == 6_00);
    }

    @Test
    public void syoMaukkaastiToimiiKunRahaaEiTarpeeksi() {
        int vaihtoraha = kassa.syoMaukkaasti(1_00);
        assertTrue("Kassassa täytyy olla riittämättömän maksun jälkeen edelleen 1000.00€.", kassa.kassassaRahaa() == 1000_00);
        assertTrue("Myytyjen edullisten lounaiden määrä täytyy olla 0.", kassa.maukkaitaLounaitaMyyty() == 0);
        assertTrue("Vaihtorahaa tulee antaa 1.00€", vaihtoraha == 1_00);
    }

    //-Korttitestit
    //--Edullisesti
    @Test
    public void syoEdullisestiToimiiKunKortillaRahaaTarpeeksi() {
        kortti = new Maksukortti(10_00);
        assertTrue(kassa.syoEdullisesti(kortti));
        assertTrue("Kassassa täytyy olla tarpeeksi suuren maksun jälkeen edelleen 1000.00€ rahaa.", kassa.kassassaRahaa() == 1000_00);
        assertTrue("Myytyjen edullisten lounaiden määrä täytyy olla 1.", kassa.edullisiaLounaitaMyyty() == 1);
        assertTrue("Kortille tulee jäädä 7.60€", kortti.saldo() == 7_60);
    }

    @Test
    public void syoEdullisestiToimiiKunKortillaRahaaEiTarpeeksi() {
        kortti = new Maksukortti(1_00);
        assertTrue(!kassa.syoEdullisesti(kortti));
        assertTrue("Kassassa täytyy olla riittämättömän maksun jälkeen edelleen 1000.00€.", kassa.kassassaRahaa() == 1000_00);
        assertTrue("Myytyjen edullisten lounaiden määrä täytyy olla 0.", kassa.edullisiaLounaitaMyyty() == 0);
        assertTrue("Kortille tulee jäädä 1.00€", kortti.saldo() == 1_00);
    }

    //--Maukkaasti
    @Test
    public void syoMaukkaastiToimiiKunKortillaRahaaTarpeeksi() {
        kortti = new Maksukortti(10_00);
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertTrue("Kassassa täytyy olla tarpeeksi suuren maksun jälkeen edelleen 1000.00€ rahaa.", kassa.kassassaRahaa() == 1000_00);
        assertTrue("Myytyjen maukkaiden lounaiden määrä täytyy olla 1.", kassa.maukkaitaLounaitaMyyty() == 1);
        assertTrue("Kortille tulee jäädä 6.00€", kortti.saldo() == 6_00);
    }

    @Test
    public void syoMaukkaastiToimiiKunKortillaRahaaEiTarpeeksi() {
        kortti = new Maksukortti(1_00);
        assertTrue(!kassa.syoMaukkaasti(kortti));
        assertTrue("Kassassa täytyy olla riittämättömän maksun jälkeen edelleen 1000.00€.", kassa.kassassaRahaa() == 1000_00);
        assertTrue("Myytyjen edullisten lounaiden määrä täytyy olla 0.", kassa.maukkaitaLounaitaMyyty() == 0);
        assertTrue("Kortille tulee jäädä 1.00€", kortti.saldo() == 1_00);
    }

    //LATAUSTESTIT
    @Test
    public void lataaRahaaKortilleToimiiKunLatausPositiivinen() {
        kortti = new Maksukortti(0_00);
        kassa.lataaRahaaKortille(kortti, 10_00);
        assertTrue("Kassassa on latauksen jälkeen 1010.00€.", kassa.kassassaRahaa() == 1010_00);
        assertTrue("Kortilla on rahaa 10.00€", kortti.saldo() == 10_00);
    }

    @Test
    public void lataaRahaaKortilleToimiiKunLatausNegatiivinen() {
        kortti = new Maksukortti(0_00);
        kassa.lataaRahaaKortille(kortti, -10_00);
        assertTrue("Kassassa on epäonnistuneen latauksen jälkeen 1000.00€.", kassa.kassassaRahaa() == 1000_00);
        assertTrue("Kortilla on rahaa 0.00€", kortti.saldo() == 0_00);
    }

}
