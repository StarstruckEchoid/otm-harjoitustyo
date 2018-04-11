/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects.Dependencies;

import otmkurssiprojekti.Level.GameObjects.PlayerCharacter;
import otmkurssiprojekti.Level.GameObjects.NonPlayerCharacter;
import otmkurssiprojekti.Level.GameObjects.ImmutableObject;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import otmkurssiprojekti.Level.GameLevel;
import static org.junit.Assert.*;
import otmkurssiprojekti.Level.GameObjects.*;
import otmkurssiprojekti.Level.GameObjects.Archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.Level.GameObjects.Archetypes.PlayerCharacterArchetype;

/**
 *
 * @author Juho Gröhn
 */
public class GameLevelTest {

    private GameLevel glvl;
    private String levelName;
    private PlayerCharacter player;
    private List<NonPlayerCharacter> npcs;
    private List<ImmutableObject> blocks;
    private List<InteractiveObject> interactives;
    private List<LinkObject> levelLinks;
    private List<PointsObject> points;

    public GameLevelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        levelName = "testLevel";
        player = new PlayerCharacter(PlayerCharacterArchetype.THIEF, new Coords(0, 0, 1), Direction.DOWN);

        npcs = new ArrayList<>();
        npcs.add(new NonPlayerCharacter(NonPlayerCharacterArchetype.VILLAGER, new Coords(10, 10, 4), Direction.DOWN));

        blocks = new ArrayList<>();
        blocks.add(new ImmutableObject(',', true, false, new Coords(8, 6, 0), Direction.DOWN));

        interactives = new ArrayList<>();
        levelLinks = new ArrayList<>();
        points = new ArrayList<>();

        glvl = new GameLevel(levelName, player, npcs, blocks, interactives, levelLinks, points);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetPlayer() {
        assertTrue(glvl.getPlayer().equals(player));
    }

    @Test
    public void testMovePlayer1() {
        glvl.movePlayer(Direction.UP);
        assertTrue(glvl.getPlayer().getCoords().equals(new Coords(0, 1, 1)));
    }

    @Test
    public void testMovePlayer2() {
        glvl.movePlayer(Direction.LEFT);
        assertTrue(glvl.getPlayer().getCoords().equals(new Coords(0, 0, 1)));
    }

    @Test
    public void testGetLevelData() {
        GameObject[][][] result = glvl.getLevelData();

        assertTrue(result[1][0][0].equals(player));
        assertTrue(result[4][10][10].getId() == NonPlayerCharacterArchetype.VILLAGER.getId());
        assertTrue(result[0][6][8].getId() == ',');
    }
}
