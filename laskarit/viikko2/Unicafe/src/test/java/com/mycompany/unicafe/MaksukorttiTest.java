package com.mycompany.unicafe;

import java.util.Random;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertTrue("Kortin saldon täytyy olla alussa 10.0.",kortti.saldo()==10);
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein(){
        int lataus = new Random().nextInt(100);
        kortti.lataaRahaa(lataus);
        assertTrue("Kun kortille ladataan "+lataus+"€ rahaa, tulee saldon olla tämän jälkeen "+(10+lataus), kortti.saldo()==10+lataus);
    }
    
    //Rahan ottaminen toimii oikein:
    @Test
    public void saldoVaheneeOikeinJosRahaaOnTarpeeksi(){
        int nosto = new Random().nextInt(10);
        kortti.otaRahaa(nosto);
        assertTrue("Kun kortilta otetaan "+nosto+"€ rahaa, tulee kortille jäädä saldoa "+(10-nosto)+"€.", kortti.saldo()==10-nosto);
    }
    @Test
    public void saldoEiMuutuJosRahaaEiOleTarpeeksi(){
        kortti.otaRahaa(12);
        assertTrue("Kun kortilta yritetään nostaa enemmän rahaa, kuin kortilla on, ei rahamäärä kortilla saa muuttua.", kortti.saldo()==10);
    }
    @Test
    public void palautaTrueJosJaVainJosRahatRiittivat(){
        assertTrue("Pitää palauttaa true, jos rahat riittivät.",kortti.otaRahaa(6));
        assertTrue("Pitää palauttaa false, jos rahat eivät riittäneet.",!kortti.otaRahaa(6));
    }
    
    @Test
    public void toStringToimiiOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
}
