/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.domain.gameobject.archetypes.BlockArchetype;
import otmkurssiprojekti.domain.level.BasicGameLevel;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gameinanimates.Block;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LevelLink;
import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.interfaces.GameObject;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.utilityclasses.TextFileGameLevels;

/**
 *
 * @author gjuho
 */
public class TextFileLevelDaoTest {

    private Path directory;
    private TextFileLevelDao tfld;

    public TextFileLevelDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        directory = Files.createTempDirectory("levelsTest");
        tfld = new TextFileLevelDao(directory);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSLL0() {
        BasicGameLevel gl = new BasicGameLevel(
                "testLevel",
                new BasicPlayerCharacter(PlayerCharacterArchetype.THIEF, new Coords(0, 0, 0), Direction.DOWN),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        testSaveLoadLevel(gl);
    }

    @Test
    public void testSLL1() {
        List<NonPlayerCharacter> npcs = new ArrayList<>();
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.RAT, new Coords(), Direction.DOWN));

        BasicGameLevel gl = new BasicGameLevel(
                "testLevel",
                new BasicPlayerCharacter(PlayerCharacterArchetype.THIEF, new Coords(0, 0, 0), Direction.DOWN),
                npcs,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        testSaveLoadLevel(gl);
    }

    @Test
    public void testSLL2() {
        List<NonPlayerCharacter> npcs = new ArrayList<>();
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.RAT, new Coords(2, 2, 0), Direction.DOWN));

        List<Block> blocks = TextFileGameLevels.makeBlockList(
                "   0 \n"
                + " ... \n"
                + "  .  \n"
        );

        BasicGameLevel gl = new BasicGameLevel(
                "testLevel",
                new BasicPlayerCharacter(PlayerCharacterArchetype.THIEF, new Coords(0, 0, 0), Direction.DOWN),
                npcs,
                blocks,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        testSaveLoadLevel(gl);
    }

    //If this looks familiar, it's because it's the default starting level from the main class.
    public void testSLL3() {
        String levelName = "Test Level";
        BasicPlayerCharacter player = new BasicPlayerCharacter(10, 1, 1, 1, 1, new Coords(3, 3, 0), Direction.DOWN);
        List<NonPlayerCharacter> npcs = new ArrayList<>();
        //Add some npcs.
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.VILLAGER, new Coords(7, 10, 0), Direction.DOWN));
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.RAT, new Coords(8, 8, 0), Direction.DOWN));
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.DEER, new Coords(4, 9, 0), Direction.DOWN));
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.FLY, new Coords(1, 1, 0), Direction.DOWN));
        List<Block> blocks = new ArrayList<>();
        for (int x = 0; x < BasicGameLevel.DIMENSIONS.getX(); x++) {
            for (int y = 0; y < BasicGameLevel.DIMENSIONS.getY(); y++) {
                if (x == 0 || x == BasicGameLevel.DIMENSIONS.getX() - 1) {
                    //Some solid blocks.
                    blocks.add(new Block(BlockArchetype.STONE_WALL, new Coords(x, y, 1), Direction.DOWN));
                } else {
                    //Non-solid blocks.
                    blocks.add(new Block(BlockArchetype.GRASS, new Coords(x, y, 1), Direction.DOWN));
                }
            }
        }
        List<InteractiveObject> interactives = new ArrayList<>();
        List<LevelLink> levelLinks = new ArrayList<>();
        List<PointsBall> points = new ArrayList<>();
        BasicGameLevel gamelvl = new BasicGameLevel(
                levelName,
                player,
                npcs,
                blocks,
                interactives,
                levelLinks,
                points
        );

        testSaveLoadLevel(gamelvl);
    }

    @Test
    public void testSLL4() {
        String levelName = "Test_Level.txt";
        BasicPlayerCharacter player = new BasicPlayerCharacter(10, 1, 2, 1, 5, new Coords(3, 3, 0), Direction.DOWN);
        List<NonPlayerCharacter> npcs = new ArrayList<>();
        //Add some npcs.
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.VILLAGER, new Coords(7, 10, 0), Direction.DOWN));
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.RAT, new Coords(8, 8, 0), Direction.DOWN));
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.DEER, new Coords(4, 9, 0), Direction.DOWN));
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.FOLLOWER, new Coords(1, 1, 0), Direction.DOWN));
        List<Block> blocks = new ArrayList<>();
        for (int x = 0; x < BasicGameLevel.DIMENSIONS.getX(); x++) {
            for (int y = 0; y < BasicGameLevel.DIMENSIONS.getY(); y++) {
                if (x == 0 || x == BasicGameLevel.DIMENSIONS.getX() - 1) {
                    //Some solid blocks.
                    blocks.add(new Block(BlockArchetype.STONE_WALL, new Coords(x, y, 1), Direction.DOWN));
                } else {
                    //Non-solid blocks.
                    blocks.add(new Block(BlockArchetype.GRASS, new Coords(x, y, 1), Direction.DOWN));
                }
            }
        }
        List<InteractiveObject> interactives = new ArrayList<>();
        List<LevelLink> levelLinks = new ArrayList<>();
        levelLinks.add(new LevelLink('d', new Coords(0, 0, 0), "Other_Level.txt"));
        List<PointsBall> points = new ArrayList<>();
        BasicGameLevel gamelvl = new BasicGameLevel(
                levelName,
                player,
                npcs,
                blocks,
                interactives,
                levelLinks,
                points
        );

        testSaveLoadLevel(gamelvl);
    }

    public void testSaveLevel(GameLevel g1) {
        try {
            tfld.saveLevel(g1);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testSaveLoadLevel(GameLevel g1) {
        testSaveLevel(g1);
        GameLevel g2 = tfld.loadLevel(g1.getLevelName());
        assertTrue(g2 != null);
        testLevelsEqual(g2, g1);

    }

    public void testLevelsEqual(GameLevel g1, GameLevel g2) {
        String g1Name = g1.getLevelName();
        String g2Name = g2.getLevelName();
        assertThat(g1Name, is(g2Name));
        List<GameObject> g1objs = g1.getGameObjects();
        List<GameObject> g2objs = g2.getGameObjects();
        assertTrue(g1objs.containsAll(g2objs));
        assertTrue(g2objs.containsAll(g1objs));
    }

}
