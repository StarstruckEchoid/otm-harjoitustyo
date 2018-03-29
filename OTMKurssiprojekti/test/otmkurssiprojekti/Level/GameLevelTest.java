/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level;

import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.FormatConverter;
import otmkurssiprojekti.Level.GameObjects.GameBlocks.*;
import otmkurssiprojekti.Level.GameObjects.GameCharacters.*;
import otmkurssiprojekti.Level.GameObjects.GameObject;

/**
 *
 * @author Juho Gröhn
 */
public class GameLevelTest {

    private GameLevel glvl;
    private GameLevelObject<PlayerCharacter> player;
    List<GameLevelObject<GameCharacter>> npcs;
    List<GameLevelObject<GameBlock>> blocks;

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
        //Player
        player = new GameLevelObject<>(
                new PlayerCharacter(1000, 10, 10),
                new Coords(0, 0, 1),
                Direction.UP
        );

        //NPCs
        npcs = new ArrayList<>();
        npcs.add(new GameLevelObject<>(
                new HostileCharacter('v', 1000, 10, 10),
                new Coords(10, 10, 4),
                Direction.LEFT
        ));

        //Blocks
        blocks = new ArrayList<>();
        for (int x = 0; x < GameLevel.getDimensions().x; x++) {
            for (int y = 0; y < GameLevel.getDimensions().y; y++) {
                blocks.add(new GameLevelObject<>(
                        new InertBlock(','),
                        new Coords(x, y, 0),
                        Direction.OUT
                ));
            }
        }

        glvl = new GameLevel(player, npcs, blocks);
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

        assertTrue(result[1][0][0].equals(player.getTopDownObject()));
        assertTrue(result[4][10][10].equals(glvl.getNpcs().get(0).getTopDownObject()));
        assertTrue(result[0][6][8].equals(new InertBlock(',')));
    }

}
