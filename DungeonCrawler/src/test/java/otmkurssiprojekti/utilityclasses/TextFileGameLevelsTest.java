/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.domain.level.BasicGameLevel;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gameinanimates.ImmutableObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LinkObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.interfaces.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author gjuho
 */
public class TextFileGameLevelsTest {

    public TextFileGameLevelsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    @Test
//    public void testMakeGameLevel() {
//        System.out.println("makeGameLevel");
//        String string = "";
//        GameLevel expResult = null;
//        GameLevel result = TextFileGameLevels.makeGameLevel(string);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testPrintGameLevel() {
//        System.out.println("printGameLevel");
//        BasicGameLevel gameLevel = null;
//        String expResult = "";
//        String result = TextFileGameLevels.printGameLevel(gameLevel);
//        assertEquals(expResult, result);
//        fail("The test case is a prototype.");
//    }
    @Test
    public void testMakeCoords() {
        Coords c = TextFileGameLevels.makeCoords("0,1,7");
        Coords expected = new Coords(0, 1, 7);
        assertTrue(c.equals(expected));
    }

    @Test
    public void testPrintCoords() {
        String c = TextFileGameLevels.printCoords(new Coords(3, 4, 55));
        String expected = "3,4,55";
        assertTrue(c.equals(expected));
    }

    @Test
    public void testMakeArcheType_pca() {

        PlayerCharacterArchetype pca = PlayerCharacterArchetype.ASSASSIN;

        PlayerCharacterArchetype c = TextFileGameLevels.makeArcheType(PlayerCharacterArchetype.class, pca.toString());
        PlayerCharacterArchetype expected = pca;

        assertTrue(c.equals(expected));
    }

    @Test
    public void testMakeArcheType_npca() {

        NonPlayerCharacterArchetype npca = NonPlayerCharacterArchetype.RAT;

        NonPlayerCharacterArchetype c = TextFileGameLevels.makeArcheType(NonPlayerCharacterArchetype.class, npca.toString());
        NonPlayerCharacterArchetype expected = npca;

        assertTrue(c.equals(expected));
    }

    @Test
    public void testPrintArchetype_npca() {
        NonPlayerCharacterArchetype npca = NonPlayerCharacterArchetype.RAT;

        String c = TextFileGameLevels.printArchetype(npca);
        String expected = npca.toString();

        assertTrue(c.equals(expected));
    }

    @Test
    public void testMakePlayerCharacter() {
        PlayerCharacter pc = TextFileGameLevels.makePlayerCharacter("20;0;1;2;3;4,5,6");
        PlayerCharacter expected = new PlayerCharacter(20, 0, 1, 2, 3, new Coords(4, 5, 6), Direction.DOWN);

        assertTrue("Expected " + expected.toString() + " but got " + pc.toString(), pc.equals(expected));
    }

    @Test
    public void testPrintPlayerCharacter() {
        String pcS = TextFileGameLevels.printPlayerCharacter(new PlayerCharacter(20, 0, 1, 2, 3, new Coords(4, 5, 6), Direction.DOWN));
        String expected = "20;0;1;2;3;4,5,6";

        assertTrue("Expected " + expected + " but got " + pcS, pcS.equals(expected));
    }

    @Test
    public void testMakeNonPlayerCharacter() {
        NonPlayerCharacterArchetype npca = NonPlayerCharacterArchetype.RAT;

        NonPlayerCharacter npc = TextFileGameLevels.makeNonPlayerCharacter(npca.toString() + ";4,5,6");
        NonPlayerCharacter expected = new HostileNonPlayerCharacter(npca, new Coords(4, 5, 6), Direction.DOWN);

        assertTrue("Expected " + expected.toString() + " but got " + npc.toString(), npc.equals(expected));
    }

    @Test
    public void testPrintNonPlayerCharacter() {
        NonPlayerCharacterArchetype npca = NonPlayerCharacterArchetype.RAT;

        String npcS = TextFileGameLevels.printNonPlayerCharacter(new HostileNonPlayerCharacter(npca, new Coords(0, 1, 2), Direction.DOWN));
        String expected = npca.toString() + ";0,1,2";

        assertTrue("Expected " + expected + " but got " + npcS, npcS.equals(expected));
    }

    @Test
    public void testMakePrintNPCList() {
        String dat
                = "r;0,1,2\n"
                + "r;2,4,7\n"
                + "r;3,3,3\n";
        List<NonPlayerCharacter> npcs = TextFileGameLevels.makeNPCList(dat);
        String dat2 = TextFileGameLevels.printNPCList(npcs);

        assertTrue(dat + "\ndoes not equal\n" + dat2, dat.equals(dat2));

    }

    @Test
    public void testPrintNPCList() {
    }

    @Test
    public void testMakeBlockList() {
    }

    @Test
    public void testPrintBlockList() {
    }
//    
//    @Test
//    public void testMakeInteractiveObjectList() {
//    }
//    
//    @Test
//    public void testPrintInteractiveObjectList() {
//    }
//    
//    @Test
//    public void testMakeLevelLinkList() {
//    }
//    
//    @Test
//    public void testPrintLevelLinkList() {
//    }
//    
//    @Test
//    public void testMakePointsSourceList() {
//    }
//    
//    @Test
//    public void testPrintPointsSourceList() {
//    }
}
