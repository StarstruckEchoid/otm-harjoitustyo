/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.Level.GameObjects.GameCharacters.PlayerCharacter;
import otmkurssiprojekti.Level.GameObjects.GameObject;

/**
 *
 * @author Juho Gröhn
 */
public class GameLevelObjectTest {

    private GameLevelObject glo;
    private GameObject go;

    public GameLevelObjectTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        go = new PlayerCharacter();
        glo = new GameLevelObject<>(go, new Coords(0, 0, 0), Direction.DOWN);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMove() {
        glo.move(Direction.RIGHT);
        assertTrue(glo.getCoords().equals(new Coords(1, 0, 0)));
        glo.move(Direction.UP);
        assertTrue(glo.getCoords().equals(new Coords(1, 1, 0)));
        glo.move(Direction.OUT);
        assertTrue(glo.getCoords().equals(new Coords(1, 1, 1)));
        glo.move(Direction.IN);
        assertTrue(glo.getCoords().equals(new Coords(1, 1, 0)));
        glo.move(Direction.IN);
        assertTrue(glo.getCoords().equals(new Coords(1, 1, -1)));

    }

    @Test
    public void testGetSetDirection() {
        glo.setDirection(Direction.UP);
        assertTrue(glo.getDirection().equals(Direction.UP));
    }

    @Test
    public void testGetCoords() {
        assertTrue(glo.getCoords().equals(new Coords(0, 0, 0)));
    }

    @Test
    public void testGetTopDownObject() {
        assertTrue(glo.getTopDownObject().equals(go));
    }

    @Test
    public void testToString() {
        assertEquals("@", glo.toString());
    }

}
