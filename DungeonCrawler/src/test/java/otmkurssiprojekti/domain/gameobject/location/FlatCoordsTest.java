/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.location;

import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gjuho
 */
public class FlatCoordsTest {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private FlatCoords f1;
    private FlatCoords f2;

    public FlatCoordsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        x1 = 3;
        y1 = 1;
        x2 = -2;
        y2 = 0;

        f1 = new FlatCoords(x1, y1);
        f2 = new FlatCoords(x2, y2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getX method, of class FlatCoords.
     */
    @Test
    public void testGetX() {
        assertThat(f1.getX(), is(x1));
    }

    /**
     * Test of getY method, of class FlatCoords.
     */
    @Test
    public void testGetY() {
        assertThat(f1.getY(), is(y1));
    }

    /**
     * Test of add method, of class FlatCoords.
     */
    @Test
    public void testAdd() {
        f1.add(f2);
        assertThat(f1, is(new FlatCoords(x1 + x2, y1 + y2)));
    }

    /**
     * Test of multiply method, of class FlatCoords.
     */
    @Test
    public void testMultiply() {
        int mul = 3;
        f1.multiply(mul);
        assertThat(f1, is(new FlatCoords(mul * x1, mul * y1)));
    }

    /**
     * Test of sum method, of class FlatCoords.
     */
    @Test
    public void testSum() {
        assertThat(f1.sum(f2), is(new FlatCoords(x1 + x2, y1 + y2)));
    }

    /**
     * Test of squaredEuclideanDistance method, of class FlatCoords.
     */
    @Test
    public void testSquaredEuclideanDistance() {
        assertThat(f1.squaredEuclideanDistance(f2), is(26));
    }

    /**
     * Test of manhattanDistance method, of class FlatCoords.
     */
    @Test
    public void testManhattanDistance() {
        assertThat(f1.manhattanDistance(f2), is(6));
    }

    /**
     * Test of lesserThan method, of class FlatCoords.
     */
    @Test
    public void testLesserThan1() {
        assertThat(f1.lesserThan(f2), is(false));
    }

    /**
     * Test of lesserThan method, of class FlatCoords.
     */
    @Test
    public void testLesserThan2() {
        assertThat(f2.lesserThan(f1), is(true));
    }

    /**
     * Test of lesserThan method, of class FlatCoords.
     */
    @Test
    public void testLesserThan3() {
        assertThat(f1.lesserThan(f1), is(false));
    }

    /**
     * Test of greaterThanOrEqualTo method, of class FlatCoords.
     */
    @Test
    public void testGreaterThanOrEqualTo1() {
        assertThat(f1.greaterThanOrEqualTo(f1), is(true));
    }

    /**
     * Test of greaterThanOrEqualTo method, of class FlatCoords.
     */
    @Test
    public void testGreaterThanOrEqualTo2() {
        assertThat(f1.greaterThanOrEqualTo(f2), is(true));
    }

    /**
     * Test of greaterThanOrEqualTo method, of class FlatCoords.
     */
    @Test
    public void testGreaterThanOrEqualTo3() {
        assertThat(f2.greaterThanOrEqualTo(f1), is(false));
    }

    /**
     * Test of toString method, of class FlatCoords.
     */
    @Test
    public void testToString() {
        assertFalse(f1.toString().equals(f2.toString()));
    }

    /**
     * Test of hashCode method, of class FlatCoords.
     */
    @Test
    public void testHashCode() {
        assertFalse(f1.hashCode() == f2.hashCode());
    }

    /**
     * Test of equals method, of class FlatCoords.
     */
    @Test
    public void testEquals1() {
        assertTrue(f1.equals(f1));
    }

    /**
     * Test of equals method, of class FlatCoords.
     */
    @Test
    public void testEquals2() {
        assertFalse(f1.equals(f2));
    }

    /**
     * Test of equals method, of class FlatCoords.
     */
    @Test
    public void testEquals3() {
        assertFalse(f2.equals(f1));
    }

    /**
     * Test of equals method, of class FlatCoords.
     */
    @Test
    public void testEquals4() {
        assertTrue(f2.equals(f2));
    }

    /**
     * Test of compareTo method, of class FlatCoords.
     */
    @Test
    public void testCompareTo1() {
        assertTrue(f1.compareTo(f2) > 0);
    }

    /**
     * Test of compareTo method, of class FlatCoords.
     */
    @Test
    public void testCompareTo2() {
        assertTrue(f2.compareTo(f1) < 0);
    }

    /**
     * Test of compareTo method, of class FlatCoords.
     */
    @Test
    public void testCompareTo3() {
        assertTrue(f1.compareTo(f1) == 0);
    }

    /**
     * Test of compareTo method, of class FlatCoords.
     */
    @Test
    public void testCompareTo() {
        assertTrue(f2.compareTo(f2) == 0);
    }

    /**
     * Test of toCoords method, of class FlatCoords.
     */
    @Test
    public void testToCoords() {
        assertThat(f1.toCoords(), is(new Coords(x1, y1, 0)));
    }

}
