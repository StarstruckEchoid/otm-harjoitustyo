/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LinkObject;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class TextFileGameObjectsTest {

    private String pcString;
    private PlayerCharacter pc;

    private String loString;
    private LinkObject lo;

    public TextFileGameObjectsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pcString = "20;0;1;2;3;4,5,6;240000";
        pc = new BasicPlayerCharacter(20, 0, 1, 2, 3, new Coords(4, 5, 6), Direction.DOWN, 240_000);

        loString = "[;8,8,0;Test_Level.txt";
        lo = new LinkObject('[', new Coords(8, 8, 0), "Test_Level.txt");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMakeCoords() {
        Coords c = TextFileGameObjects.makeCoords("0,1,7");
        Coords expected = new Coords(0, 1, 7);
        assertTrue(c.equals(expected));
    }

    @Test
    public void testPrintCoords() {
        String c = TextFileGameObjects.printCoords(new Coords(3, 4, 55));
        String expected = "3,4,55";
        assertTrue(c.equals(expected));
    }

    @Test
    public void testMakeArcheType_pca() {

        PlayerCharacterArchetype pca = PlayerCharacterArchetype.ASSASSIN;

        PlayerCharacterArchetype c = TextFileGameObjects.makeArcheType(PlayerCharacterArchetype.class, pca.toString()).get();
        PlayerCharacterArchetype expected = pca;

        assertTrue(c.equals(expected));
    }

    @Test
    public void testMakeArcheType_npca() {

        NonPlayerCharacterArchetype npca = NonPlayerCharacterArchetype.RAT;

        NonPlayerCharacterArchetype c = TextFileGameObjects.makeArcheType(NonPlayerCharacterArchetype.class, npca.toString()).get();
        NonPlayerCharacterArchetype expected = npca;

        assertTrue(c.equals(expected));
    }

    @Test
    public void testPrintArchetype_npca() {
        NonPlayerCharacterArchetype npca = NonPlayerCharacterArchetype.RAT;

        String c = TextFileGameObjects.printArchetype(npca);
        String expected = npca.toString();

        assertTrue(c.equals(expected));
    }

    @Test
    public void testMakePlayerCharacter() {
        PlayerCharacter madePc = TextFileGameObjects.makePlayerCharacter(pcString);

        assertTrue("Expected " + pc.toString() + " but got " + madePc.toString(), madePc.equals(pc));
    }

    @Test
    public void testPrintPlayerCharacter() {
        String printedPc = TextFileGameObjects.printPlayerCharacter(pc);

        assertTrue("Expected " + pcString + " but got " + printedPc, printedPc.equals(pcString));
    }

    @Test
    public void testMakeNonPlayerCharacter() {
        NonPlayerCharacterArchetype npca = NonPlayerCharacterArchetype.RAT;

        NonPlayerCharacter npc = TextFileGameObjects.makeNonPlayerCharacter(npca.toString() + ";4,5,6").get();
        NonPlayerCharacter expected = new HostileNonPlayerCharacter(npca, new Coords(4, 5, 6), Direction.DOWN);

        assertTrue("Expected " + expected.toString() + " but got " + npc.toString(), npc.equals(expected));
    }

    @Test
    public void testPrintNonPlayerCharacter() {
        NonPlayerCharacterArchetype npca = NonPlayerCharacterArchetype.RAT;

        String npcS = TextFileGameObjects.printNonPlayerCharacter(new HostileNonPlayerCharacter(npca, new Coords(0, 1, 2), Direction.DOWN));
        String expected = npca.toString() + ";0,1,2";

        assertTrue("Expected " + expected + " but got " + npcS, npcS.equals(expected));
    }

    /**
     * Test of makeLinkObject method, of class TextFileGameObjects.
     */
    @Test
    public void testMakeLinkObject() {
        LinkObject madeLo = TextFileGameObjects.makeLinkObject(loString).get();

        assertThat(madeLo, is(lo));
    }

    @Test
    public void testPrintLinkObject() {
        String madeLoString = TextFileGameObjects.printLinkObject(lo);

        assertThat(madeLoString, is(loString));
    }

}
