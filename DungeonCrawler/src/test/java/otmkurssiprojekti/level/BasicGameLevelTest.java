/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level;

import otmkurssiprojekti.level.gameobjects.interfaces.GameObject;
import otmkurssiprojekti.level.gameobjects.PointsBall;
import otmkurssiprojekti.level.gameobjects.InteractiveObject;
import otmkurssiprojekti.level.gameobjects.LinkObject;
import otmkurssiprojekti.level.gameobjects.gamecharacter.playercharacter.PlayerCharacter;
import otmkurssiprojekti.level.gameobjects.ImmutableObject;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.level.gameobjects.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.level.gameobjects.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.level.gameobjects.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.level.gameobjects.interfaces.NonPlayerCharacter;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class BasicGameLevelTest {

    private BasicGameLevel glvl;
    private String levelName;
    private PlayerCharacter player;
    private List<NonPlayerCharacter> npcs;
    private List<ImmutableObject> blocks;
    private List<InteractiveObject> interactives;
    private List<LinkObject> levelLinks;
    private List<PointsBall> points;

    public BasicGameLevelTest() {
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
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.VILLAGER, new Coords(10, 10, 4), Direction.DOWN));

        blocks = new ArrayList<>();
        blocks.add(new ImmutableObject(',', true, false, new Coords(8, 6, 0), Direction.DOWN));

        interactives = new ArrayList<>();
        levelLinks = new ArrayList<>();
        points = new ArrayList<>();

        glvl = new BasicGameLevel(levelName, player, npcs, blocks, interactives, levelLinks, points);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetLevelData() {
        GameObject[][][] result = glvl.getLevelData();

        assertTrue(result[1][0][0].equals(player));
        assertTrue(result[4][10][10].getId() == NonPlayerCharacterArchetype.VILLAGER.getId());
        assertTrue(result[0][6][8].getId() == ',');
    }
}
