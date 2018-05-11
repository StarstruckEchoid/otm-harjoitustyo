/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter;

import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.interfaces.Destructible;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import static otmkurssiprojekti.domain.gameobject.location.Direction.DOWN;

/**
 *
 * @author Juho Gröhn
 */
public class BasicPlayerCharacterTest {

    private Coords bpcCoords;
    private int initPoints;
    private BasicPlayerCharacter bpc;

    public BasicPlayerCharacterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        bpcCoords = new Coords(0, 0, 0);
        initPoints = 2400;
        bpc = new BasicPlayerCharacter(20, 100, 0, 0, 0, bpcCoords, DOWN, initPoints);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of coordsAt method, of class BasicPlayerCharacter.
     */
    @Test
    public void testCoordsAt() {
        Direction dir = DOWN;
        BasicPlayerCharacter instance = bpc;
        Coords expResult = new Coords(0, -1, 0);
        Coords result = instance.coordsAt(dir);
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class BasicPlayerCharacter.
     */
    @Test
    public void testGetId() {
        BasicPlayerCharacter instance = bpc;
        char expResult = '@';
        char result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPoints method, of class BasicPlayerCharacter.
     */
    @Test
    public void testGetPoints() {
        BasicPlayerCharacter instance = bpc;
        int expResult = 2400;
        int result = instance.getPoints();
        assertEquals(expResult, result);
    }

    /**
     * Test of hurt method, of class BasicPlayerCharacter.
     */
    @Test
    public void testHurt() {
        Destructible d = new HostileNonPlayerCharacter('v', 20, 0, 0, 0, 0, 0, new Coords(), DOWN);
        BasicPlayerCharacter instance = bpc;
        instance.hurt(d);
        assertTrue(d.isDead());
        assertThat(bpc.getPoints(), is(2420));
    }

    /**
     * Test of collectPoint method, of class BasicPlayerCharacter.
     */
    @Test
    public void testCollectPoint1() {
        int points = 20;
        PointsBall pb = new PointsBall('*', bpcCoords, points);
        assertTrue(bpc.collectPoint(pb));
        assertThat(bpc.getPoints(), is(initPoints + points));
    }

    /**
     * Test of collectPoint method, of class BasicPlayerCharacter.
     */
    @Test
    public void testCollectPoint2() {
        PointsBall pb = new PointsBall('*', new Coords(-1, 3, 22), 20);
        assertFalse(bpc.collectPoint(pb));
        assertThat(bpc.getPoints(), is(initPoints));
    }

}
