/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.domain.gameobject.archetypes.ImmutableObjectArchetype;
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

    private String pcString;
    private PlayerCharacter pc;

    private String npcsString;
    private List<NonPlayerCharacter> npcs;

    private String blocksString;
    private List<ImmutableObject> blocks;

    private final String levelName = "testLevel";
    private String gameLevelString;
    private GameLevel gameLevel;

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
        pcString = "20;0;1;2;3;4,5,6";
        pc = new PlayerCharacter(20, 0, 1, 2, 3, new Coords(4, 5, 6), Direction.DOWN);

        npcsString
                = "r;0,1,2\n"
                + "r;2,4,7\n"
                + "r;3,3,3\n";

        npcs = new ArrayList<>();
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.RAT, new Coords(0, 1, 2), Direction.DOWN));
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.RAT, new Coords(2, 4, 7), Direction.DOWN));
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.RAT, new Coords(3, 3, 3), Direction.DOWN));

        blocksString
                = "00000\n"
                + "0...0\n"
                + "0.,.0\n"
                + "0...0\n"
                + "00000\n";
        blocks = new ArrayList<>();
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                Coords coords = new Coords(x, y, TextFileGameLevels.BLOCKS_LEVEL);
                if (x == 0 || x == 4 || y == 0 || y == 4) {
                    blocks.add(new ImmutableObject(ImmutableObjectArchetype.STONE_WALL, coords, Direction.DOWN));
                } else if (x == 2 && y == 2) {
                    blocks.add(new ImmutableObject(ImmutableObjectArchetype.GRASS, coords, Direction.DOWN));
                } else {
                    blocks.add(new ImmutableObject(ImmutableObjectArchetype.STONE_PATH, coords, Direction.DOWN));
                }
            }
        }

        gameLevelString
                = levelName + "\n"
                + "\n"
                + pcString + "\n"
                + "\n"
                + npcsString
                + "\n"
                + blocksString + "\n"
                + "EMPTY\n"
                + "\n"
                + "EMPTY\n"
                + "\n"
                + "EMPTY\n"
                + "\n";

        gameLevel = new BasicGameLevel(levelName, pc, npcs, blocks, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMakeGameLevel() {
        GameLevel madeGameLevel = TextFileGameLevels.makeGameLevel(gameLevelString);

        assertTrue("Expected " + gameLevel.toString() + " but got " + madeGameLevel.toString(), madeGameLevel.equals(gameLevel));
    }

    @Test
    public void testPrintGameLevel() {
        String printedGameLevel = TextFileGameLevels.printGameLevel((BasicGameLevel) gameLevel);

        assertTrue("Expected\n" + gameLevelString + "\nbut got\n" + printedGameLevel, printedGameLevel.equals(gameLevelString));
    }

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
        PlayerCharacter madePc = TextFileGameLevels.makePlayerCharacter(pcString);

        assertTrue("Expected " + pc.toString() + " but got " + madePc.toString(), madePc.equals(pc));
    }

    @Test
    public void testPrintPlayerCharacter() {
        String printedPc = TextFileGameLevels.printPlayerCharacter(pc);

        assertTrue("Expected " + pcString + " but got " + printedPc, printedPc.equals(pcString));
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
    public void testMakeNPCList() {
        List<NonPlayerCharacter> madeNpcs = TextFileGameLevels.makeNPCList(npcsString);

        assertTrue("Expected " + npcs.toString() + " but got " + madeNpcs.toString(), madeNpcs.equals(npcs));
    }

    @Test
    public void testPrintNPCList() {
        String printedNpcs = TextFileGameLevels.printNPCList(npcs);

        assertTrue("Expected\n" + npcsString + "\nbut got\n" + printedNpcs, printedNpcs.equals(npcsString));
    }

    @Test
    public void testMakeBlockList() {
        List<ImmutableObject> madeBlocks = TextFileGameLevels.makeBlockList(blocksString);

        assertTrue("Expected\n" + blocks.toString() + "\nbut got\n" + madeBlocks.toString(), madeBlocks.containsAll(blocks));
    }

    @Test
    public void testPrintBlockList() {
        String printedBlocks = TextFileGameLevels.printBlockList(blocks);

        assertTrue(printedBlocks.equals(blocksString));
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
