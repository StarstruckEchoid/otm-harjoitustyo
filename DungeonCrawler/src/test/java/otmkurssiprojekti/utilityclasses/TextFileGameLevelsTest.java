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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static otmkurssiprojekti.domain.gameobject.archetypes.BlockArchetype.GRASS;
import static otmkurssiprojekti.domain.gameobject.archetypes.BlockArchetype.STONE_PATH;
import static otmkurssiprojekti.domain.gameobject.archetypes.BlockArchetype.STONE_WALL;
import static otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype.RAT;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gameinanimates.Block;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LevelLink;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import static otmkurssiprojekti.domain.gameobject.location.Direction.DOWN;
import otmkurssiprojekti.domain.level.BasicGameLevel;
import otmkurssiprojekti.domain.level.GameLevel;
import static otmkurssiprojekti.utilityclasses.TextFileGameLevels.BLOCKS_LEVEL;
import static otmkurssiprojekti.utilityclasses.TextFileGameLevels.makeBlockList;
import static otmkurssiprojekti.utilityclasses.TextFileGameLevels.makeGameLevel;
import static otmkurssiprojekti.utilityclasses.TextFileGameLevels.makeLevelLinkList;
import static otmkurssiprojekti.utilityclasses.TextFileGameLevels.makeNPCList;
import static otmkurssiprojekti.utilityclasses.TextFileGameLevels.printBlockList;
import static otmkurssiprojekti.utilityclasses.TextFileGameLevels.printGameLevel;
import static otmkurssiprojekti.utilityclasses.TextFileGameLevels.printLevelLinkList;
import static otmkurssiprojekti.utilityclasses.TextFileGameLevels.printNPCList;

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
    private List<Block> blocks;

    private String linksString;
    private List<LevelLink> links;

    private final String levelName = "Test_Level.txt";
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
        pc = new BasicPlayerCharacter(20, 0, 1, 2, 3, new Coords(4, 5, 6), DOWN, 240_000);

        npcsString
                = "r;0,1,2\n"
                + "r;2,4,7\n"
                + "r;3,3,3\n";

        npcs = new ArrayList<>();
        npcs.add(new HostileNonPlayerCharacter(RAT, new Coords(0, 1, 2), DOWN));
        npcs.add(new HostileNonPlayerCharacter(RAT, new Coords(2, 4, 7), DOWN));
        npcs.add(new HostileNonPlayerCharacter(RAT, new Coords(3, 3, 3), DOWN));

        blocksString
                = "00000\n"
                + "0...0\n"
                + "0.,.0\n"
                + "0...0\n"
                + "00000\n";
        blocks = new ArrayList<>();
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                Coords coords = new Coords(x, y, BLOCKS_LEVEL);
                if (x == 0 || x == 4 || y == 0 || y == 4) {
                    blocks.add(new Block(STONE_WALL, coords, DOWN));
                } else if (x == 2 && y == 2) {
                    blocks.add(new Block(GRASS, coords, DOWN));
                } else {
                    blocks.add(new Block(STONE_PATH, coords, DOWN));
                }
            }
        }

        linksString
                = "[;0,1,11;Test1.txt\n"
                + "[;3,6,5;Test2.txt\n"
                + "[;2,0,0;Test3.txt\n";

        links = new ArrayList<>();
        links.add(new LevelLink('[', new Coords(0, 1, 11), "Test1.txt"));
        links.add(new LevelLink('[', new Coords(3, 6, 5), "Test2.txt"));
        links.add(new LevelLink('[', new Coords(2, 0, 0), "Test3.txt"));

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
        GameLevel madeGameLevel = makeGameLevel(gameLevelString);
        assertThat(madeGameLevel.getLevelName(), is(levelName));
        assertThat(madeGameLevel, is(gameLevel));
    }

    @Test
    public void testPrintGameLevel() {
        String madeGameLevelString = printGameLevel(gameLevel);

        assertThat(madeGameLevelString, is(gameLevelString));
    }

    @Test
    public void testMakeNPCList() {
        List<NonPlayerCharacter> madeNpcs = makeNPCList(npcsString);

        assertThat(madeNpcs, is(npcs));
    }

    @Test
    public void testPrintNPCList() {
        String madeNpcsString = printNPCList(npcs);

        assertThat(madeNpcsString, is(npcsString));
    }

    @Test
    public void testMakeBlockList() {
        List<Block> madeBlocks = makeBlockList(blocksString);

        assertTrue("Expected\n" + blocks.toString() + "\nbut got\n" + madeBlocks.toString(), madeBlocks.containsAll(blocks));
    }

    @Test
    public void testPrintBlockList() {
        String madeBlocksString = printBlockList(blocks);

        assertThat(madeBlocksString, is(blocksString));
    }

    @Test
    public void testMakeLevelLinkList() {
        List<LevelLink> madeLinks = makeLevelLinkList(linksString);

        assertTrue(madeLinks.containsAll(links));
    }

    @Test
    public void testPrintLevelLinkList() {
        String madeLinksString = printLevelLinkList(links);

        assertThat(madeLinksString, is(linksString));
    }
}
