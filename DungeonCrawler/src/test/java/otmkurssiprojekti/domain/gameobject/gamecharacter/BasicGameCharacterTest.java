/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gamecharacter;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype.THIEF;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import static otmkurssiprojekti.domain.gameobject.location.Direction.DOWN;
import static otmkurssiprojekti.domain.gameobject.location.Direction.LEFT;

/**
 *
 * @author Juho Gröhn
 */
public class BasicGameCharacterTest {

    private BasicGameCharacter bgc;
    private static final Coords TEST_COORDS = new Coords(8, 2, 1);
    private static final Direction TEST_DIRECTION = DOWN;

    public BasicGameCharacterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        bgc = new BasicPlayerCharacter(THIEF, TEST_COORDS, TEST_DIRECTION);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIsTransparent() {
        assertTrue(bgc.isTransparent());
    }

    @Test
    public void testIsSolid() {
        assertTrue(!bgc.isSolid());
    }

    @Test
    public void testGetCoords() {
        Coords coords = bgc.getCoords();
        assertTrue(coords.equals(TEST_COORDS));
    }

    @Test
    public void testGetDirection() {
        assertTrue(bgc.getDirection().equals(TEST_DIRECTION));
    }

    @Test
    public void testMove_coords() {
        Coords testCoords1 = new Coords(0, 0, 0);
        bgc.move(testCoords1);
        assertTrue(bgc.getCoords().equals(testCoords1));
    }

    @Test
    public void testMove_direction() {
        Coords expected = TEST_COORDS.sum(TEST_DIRECTION.getCoords());
        bgc.move(TEST_DIRECTION);
        assertTrue(bgc.getCoords().equals(expected));
    }

    @Test
    public void testTurn() {
        Direction turn = LEFT;
        bgc.turn(turn);
        assertTrue(bgc.getDirection().equals(turn));
    }

}
