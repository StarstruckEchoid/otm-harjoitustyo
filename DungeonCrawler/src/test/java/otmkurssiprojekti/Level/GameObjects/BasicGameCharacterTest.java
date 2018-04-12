/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.Level.GameObjects.Archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class BasicGameCharacterTest {
    
    private BasicGameCharacter bgc;
    private static final Coords TEST_COORDS = new Coords(8, 2, 1);
    private static final Direction TEST_DIRECTION = Direction.DOWN;
    
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
        bgc = new PlayerCharacter(PlayerCharacterArchetype.THIEF, TEST_COORDS, TEST_DIRECTION);
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
    public void testSetCoords() {
        Coords testCoords1 = new Coords(0, 0, 0);
        bgc.setCoords(testCoords1);
        assertTrue(bgc.getCoords().equals(testCoords1));
    }
    
    @Test
    public void testMove() {
        Coords expected = TEST_COORDS.sum(TEST_DIRECTION.getCoords());
        bgc.move(TEST_DIRECTION);
        assertTrue(bgc.getCoords().equals(expected));
    }
    
    @Test
    public void testTurn() {
        Direction turn = Direction.LEFT;
        bgc.turn(turn);
        assertTrue(bgc.getDirection().equals(turn));
    }
    
}