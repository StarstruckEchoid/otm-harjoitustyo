/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import java.util.Optional;
import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype;
import static otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype.RAT;
import otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype;
import static otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype.ASSASSIN;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LevelLink;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import static otmkurssiprojekti.domain.gameobject.location.Direction.DOWN;
import static otmkurssiprojekti.utilityclasses.TextFileGameObjects.makeArcheType;
import static otmkurssiprojekti.utilityclasses.TextFileGameObjects.makeCoords;
import static otmkurssiprojekti.utilityclasses.TextFileGameObjects.makeLinkObject;
import static otmkurssiprojekti.utilityclasses.TextFileGameObjects.makeNonPlayerCharacter;
import static otmkurssiprojekti.utilityclasses.TextFileGameObjects.makePlayerCharacter;
import static otmkurssiprojekti.utilityclasses.TextFileGameObjects.printArchetype;
import static otmkurssiprojekti.utilityclasses.TextFileGameObjects.printCoords;
import static otmkurssiprojekti.utilityclasses.TextFileGameObjects.printLinkObject;
import static otmkurssiprojekti.utilityclasses.TextFileGameObjects.printNonPlayerCharacter;
import static otmkurssiprojekti.utilityclasses.TextFileGameObjects.printPlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
public class TextFileGameObjectsTest {

    private String pcString;
    private PlayerCharacter pc;

    private String loString;
    private LevelLink lo;

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
        pc = new BasicPlayerCharacter(20, 0, 1, 2, 3, new Coords(4, 5, 6), DOWN, 240_000);

        loString = "[;8,8,0;Test_Level.txt";
        lo = new LevelLink('[', new Coords(8, 8, 0), "Test_Level.txt");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMakeCoords() {
        Coords c = makeCoords("0,1,7");
        Coords expected = new Coords(0, 1, 7);
        assertTrue(c.equals(expected));
    }

    @Test
    public void testMakeCoords_invalid() {
        try {
            Coords c = makeCoords("0,1");
            fail();
        } catch (IllegalArgumentException iae) {
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testPrintCoords() {
        String c = printCoords(new Coords(3, 4, 55));
        String expected = "3,4,55";
        assertTrue(c.equals(expected));
    }

    @Test
    public void testMakeArcheType_pca() {

        PlayerCharacterArchetype pca = ASSASSIN;

        PlayerCharacterArchetype c = makeArcheType(PlayerCharacterArchetype.class, pca.toString()).get();
        PlayerCharacterArchetype expected = pca;

        assertTrue(c.equals(expected));
    }

    @Test
    public void testMakeArcheType_npca() {

        NonPlayerCharacterArchetype npca = RAT;

        NonPlayerCharacterArchetype c = makeArcheType(NonPlayerCharacterArchetype.class, npca.toString()).get();
        NonPlayerCharacterArchetype expected = npca;

        assertTrue(c.equals(expected));
    }

    @Test
    public void testPrintArchetype_npca() {
        NonPlayerCharacterArchetype npca = RAT;

        String c = printArchetype(npca);
        String expected = npca.toString();

        assertTrue(c.equals(expected));
    }

    @Test
    public void testMakePlayerCharacter() {
        PlayerCharacter madePc = makePlayerCharacter(pcString);

        assertThat(madePc, is(pc));
    }

    @Test
    public void testPrintPlayerCharacter() {
        String printedPc = printPlayerCharacter(pc);

        assertTrue("Expected " + pcString + " but got " + printedPc, printedPc.equals(pcString));
    }

    @Test
    public void testMakeNonPlayerCharacter() {
        NonPlayerCharacterArchetype npca = RAT;

        NonPlayerCharacter npc = makeNonPlayerCharacter(npca.toString() + ";4,5,6").get();
        NonPlayerCharacter expected = new HostileNonPlayerCharacter(npca, new Coords(4, 5, 6), DOWN);

        assertThat(npc, is(expected));
    }

    @Test
    public void testMakeNonPlayerCharacter_invalid1() {

        Optional<NonPlayerCharacter> npcOpt = makeNonPlayerCharacter("R");

        assertThat(npcOpt, is(empty()));
    }

    @Test
    public void testMakeNonPlayerCharacter_invalid2() {

        Optional<NonPlayerCharacter> npcOpt = makeNonPlayerCharacter("R;0,0,0");

        assertThat(npcOpt, is(empty()));
    }

    @Test
    public void testPrintNonPlayerCharacter() {
        NonPlayerCharacterArchetype npca = RAT;

        String npcS = printNonPlayerCharacter(new HostileNonPlayerCharacter(npca, new Coords(0, 1, 2), DOWN));
        String expected = npca.toString() + ";0,1,2";

        assertThat(npcS, is(expected));
    }

    /**
     * Test of makeLinkObject method, of class TextFileGameObjects.
     */
    @Test
    public void testMakeLinkObject() {
        LevelLink madeLo = makeLinkObject(loString).get();

        assertThat(madeLo, is(lo));
    }

    /**
     * Test of makeLinkObject method, of class TextFileGameObjects.
     */
    @Test
    public void testMakeLinkObject_invalid() {
        Optional<LevelLink> madeLo = makeLinkObject("@");

        assertThat(madeLo, is(empty()));
    }

    @Test
    public void testPrintLinkObject() {
        String madeLoString = printLinkObject(lo);

        assertThat(madeLoString, is(loString));
    }

}
