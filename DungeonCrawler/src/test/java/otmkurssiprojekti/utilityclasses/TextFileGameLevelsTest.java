/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
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
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gameinanimates.ImmutableObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LinkObject;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
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

    private String linksString;
    private List<LinkObject> links;

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
        pcString = "20;0;1;2;3;4,5,6;240000";
        pc = new BasicPlayerCharacter(20, 0, 1, 2, 3, new Coords(4, 5, 6), Direction.DOWN, 240_000);

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

        linksString
                = "[;0,1,11;Test1.txt\n"
                + "[;3,6,5;Test2.txt\n"
                + "[;2,0,0;Test3.txt\n";

        links = new ArrayList<>();
        links.add(new LinkObject('[', new Coords(0, 1, 11), "Test1.txt"));
        links.add(new LinkObject('[', new Coords(3, 6, 5), "Test2.txt"));
        links.add(new LinkObject('[', new Coords(2, 0, 0), "Test3.txt"));

        gameLevelString
                = levelName + "\n"
                + "\n"
                + pcString + "\n"
                + "\n"
                + npcsString
                + "\n"
                + blocksString
                + "\n"
                + "EMPTY\n"
                + "\n"
                + linksString
                + "\n"
                + "EMPTY\n"
                + "\n";

        gameLevel = new BasicGameLevel(levelName, pc, npcs, blocks, new ArrayList<>(), links, new ArrayList<>());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMakeGameLevel() {
        GameLevel madeGameLevel = TextFileGameLevels.makeGameLevel(gameLevelString);

        assertThat(madeGameLevel, is(gameLevel));
    }

    @Test
    public void testPrintGameLevel() {
        String madeGameLevelString = TextFileGameLevels.printGameLevel((BasicGameLevel) gameLevel);

        assertThat(madeGameLevelString, is(gameLevelString));
    }

    @Test
    public void testMakeNPCList() {
        List<NonPlayerCharacter> madeNpcs = TextFileGameLevels.makeNPCList(npcsString);

        assertThat(madeNpcs, is(npcs));
    }

    @Test
    public void testPrintNPCList() {
        String madeNpcsString = TextFileGameLevels.printNPCList(npcs);

        assertThat(madeNpcsString, is(npcsString));
    }

    @Test
    public void testMakeBlockList() {
        List<ImmutableObject> madeBlocks = TextFileGameLevels.makeBlockList(blocksString);

        assertTrue("Expected\n" + blocks.toString() + "\nbut got\n" + madeBlocks.toString(), madeBlocks.containsAll(blocks));
    }

    @Test
    public void testPrintBlockList() {
        String madeBlocksString = TextFileGameLevels.printBlockList(blocks);

        assertThat(madeBlocksString, is(blocksString));
    }

//    @Test
//    public void testMakeInteractiveObjectList() {
//    }
//    
//    @Test
//    public void testPrintInteractiveObjectList() {
//    }
    @Test
    public void testMakeLevelLinkList() {
        List<LinkObject> madeLinks = TextFileGameLevels.makeLevelLinkList(linksString);

        assertTrue(madeLinks.containsAll(links));
    }

    @Test
    public void testPrintLevelLinkList() {
        String madeLinksString = TextFileGameLevels.printLevelLinkList(links);

        assertThat(madeLinksString, is(linksString));
    }
//    
//    @Test
//    public void testMakePointsSourceList() {
//    }
//    
//    @Test
//    public void testPrintPointsSourceList() {
//    }
}
