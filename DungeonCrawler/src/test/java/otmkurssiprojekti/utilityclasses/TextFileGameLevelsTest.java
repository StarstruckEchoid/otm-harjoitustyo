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
import otmkurssiprojekti.level.BasicGameLevel;
import otmkurssiprojekti.level.GameLevel;
import otmkurssiprojekti.level.gameobjects.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.level.gameobjects.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.level.gameobjects.gamecharacter.playercharacter.PlayerCharacter;
import otmkurssiprojekti.level.gameobjects.gameinanimates.ImmutableObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.InteractiveObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.LinkObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.PointsBall;
import otmkurssiprojekti.level.gameobjects.interfaces.NonPlayerCharacter;
import otmkurssiprojekti.level.gameobjects.location.Coords;

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

//    @Test
//    public void testMakePlayerCharacter() {
//    }
//    
//    @Test
//    public void testPrintPlayerCharacter() {
//    }
//    
//    @Test
//    public void testMakeNonPlayerCharacter() {
//    }
//    
//    @Test
//    public void testPrintNonPlayerCharacter() {
//    }
//    
//    @Test
//    public void testMakeNPCList() {
//    }
//    
//    @Test
//    public void testPrintNPCList() {
//    }
//    
//    @Test
//    public void testMakeBlockList() {
//    }
//    
//    @Test
//    public void testPrintBlockList() {
//    }
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
