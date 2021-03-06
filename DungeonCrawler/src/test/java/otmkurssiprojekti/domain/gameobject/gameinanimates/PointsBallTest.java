/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gameinanimates;

import static org.hamcrest.core.Is.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.domain.gameobject.location.Coords;

/**
 *
 * @author Juho Gröhn
 */
public class PointsBallTest {

    private char id;
    private Coords coords;
    private int points;
    private PointsBall pointsBall;
    PointsBall pbOther;

    public PointsBallTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        id = '*';
        coords = new Coords(8, 9, 14);
        points = 20;
        pointsBall = new PointsBall(id, coords, points);
        pbOther = new PointsBall('^', new Coords(0, 0, 3), points);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class PointsBall.
     */
    @Test
    public void testGetId() {
        assertThat(pointsBall.getId(), is(id));
    }

    /**
     * Test of isTransparent method, of class PointsBall.
     */
    @Test
    public void testIsTransparent() {
        assertThat(pointsBall.isTransparent(), is(PointsBall.TRANSPARENT));
    }

    /**
     * Test of isSolid method, of class PointsBall.
     */
    @Test
    public void testIsSolid() {
        assertThat(pointsBall.isSolid(), is(PointsBall.SOLID));
    }

    /**
     * Test of getCoords method, of class PointsBall.
     */
    @Test
    public void testGetCoords() {
        assertThat(pointsBall.getCoords(), is(coords));
    }

    /**
     * Test of getDirection method, of class PointsBall.
     */
    @Test
    public void testGetDirection() {
        assertThat(pointsBall.getDirection(), is(PointsBall.DIRECTION));
    }

    /**
     * Test of getPoints method, of class PointsBall.
     */
    @Test
    public void testGetPoints() {
        assertThat(pointsBall.getPoints(), is(points));
    }

    /**
     * Test of hashCode method, of class PointsBall.
     */
    @Test
    public void testHashCode() {
        assertNotEquals(pbOther.hashCode(), pointsBall.hashCode());
    }

    /**
     * Test of equals method, of class PointsBall.
     */
    @Test
    public void testEquals1() {
        assertNotEquals(pbOther, pointsBall);
    }

    /**
     * Test of equals method, of class PointsBall.
     */
    @Test
    public void testEquals2() {
        assertEquals(pbOther, pbOther);
    }

    /**
     * Test of equals method, of class PointsBall.
     */
    @Test
    public void testEquals3() {
        assertNotEquals(pointsBall, pbOther);
    }

    /**
     * Test of equals method, of class PointsBall.
     */
    @Test
    public void testEquals4() {
        assertEquals(pointsBall, pointsBall);
    }

}
