/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter;

import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.utilityclasses.TextFileGameLevels;
import otmkurssiprojekti.utilityclasses.TextFileGameObjects;

/**
 *
 * @author gjuho
 */
public class HostileNonPlayerCharacterTest {

    private HostileNonPlayerCharacter hostileNPC;
    Coords playerCoords;
    private GameLevel gameLevel;

    public HostileNonPlayerCharacterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        hostileNPC = new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.RAT, new Coords(0, 0, 0), Direction.DOWN);
        playerCoords = new Coords(3, 0, 0);
        gameLevel = TextFileGameLevels.makeGameLevel(
                "test.txt\n"
                + "\n"
                + "10;1;1;1;1;" + TextFileGameObjects.printCoords(playerCoords) + ";240\n"
                + "\n"
                + "EMPTY\n"
                + "\n"
                + "EMPTY\n"
                + "\n"
                + "EMPTY\n"
                + "\n"
                + "EMPTY\n"
                + "\n"
                + "EMPTY\n"
                + "\n"
                + "EMPTY"
        );
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of act method, of class HostileNonPlayerCharacter.
     */
    @Test
    public void testAct1() {
        hostileNPC.act(gameLevel);
        assertThat(hostileNPC.getCoords(), is(new Coords(1, 0, 0)));
    }

    @Test
    public void testAct2() {
        for (int i = 0; i < 3; i++) {
            hostileNPC.act(gameLevel);
        }
        assertTrue(hostileNPC.getCoords().manhattanDistance(playerCoords) <= 2);
    }

    @Test
    public void testAct3() {
        assertFalse(gameLevel.getPlayer().isDead());
        for (int i = 0; i < 20; i++) {
            hostileNPC.act(gameLevel);
        }
        assertTrue(gameLevel.getPlayer().isDead());
    }

}
