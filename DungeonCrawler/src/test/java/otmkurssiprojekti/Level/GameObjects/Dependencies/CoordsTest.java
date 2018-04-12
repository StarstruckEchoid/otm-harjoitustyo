/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects.Dependencies;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juho Gröhn
 */
public class CoordsTest {

    private Coords coords;

    public CoordsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        coords = new Coords(0, 5, 10);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetX() {
        assertTrue(coords.getX() == 0);
    }

    @Test
    public void testGetY() {
        assertTrue(coords.getY() == 5);

    }

    @Test
    public void testGetZ() {
        assertTrue(coords.getZ() == 10);

    }

    @Test
    public void testAdd() {
        Coords c = new Coords(0, -5, -10);
        coords.add(c);
        assertTrue(coords.equals(new Coords(0, 0, 0)));
    }

    @Test
    public void testMultiply() {
        coords.multiply(3);
        assertTrue(coords.equals(new Coords(0, 15, 30)));
    }

    @Test
    public void testSum() {
        Coords c = new Coords(1, 0, 4);
        Coords sum = coords.sum(c);
        assertTrue(coords.equals(new Coords(0, 5, 10)));
        assertTrue(c.equals(new Coords(1, 0, 4)));
        assertTrue(sum.equals(new Coords(1, 5, 14)));
    }

    @Test
    public void testSqEucDist1() {
        testSquaredEuclideanDistance(coords, coords, 0);
    }

    @Test
    public void testSqEucDist2() {
        testSquaredEuclideanDistance(coords, new Coords(0, 0, 0), 125);
    }

    @Test
    public void testSqEucDist3() {
        testSquaredEuclideanDistance(coords, new Coords(5, 0, 0), 150);
    }

    public void testSquaredEuclideanDistance(Coords c1, Coords c2, int expected) {
        Coords c1Copy = new Coords(c1.getX(), c1.getY(), c1.getZ());
        Coords c2Copy = new Coords(c2.getX(), c2.getY(), c2.getZ());
        int distance = c1.squaredEuclideanDistance(c2);
        assertTrue("Expected squared distance to be " + expected + " but insted it was " + distance, distance == expected);
        assertTrue("Method changed the value of c1!", c1.equals(c1Copy));
        assertTrue("Method changed the value of c2!", c2.equals(c2Copy));
    }

    @Test
    public void testLesserThan() {
        Coords less = new Coords(-1, 4, 9);
        Coords more = new Coords(1, 10, 20);
        Coords diagonal = new Coords(-1, 5, 11);
        assertTrue(!coords.lesserThan(coords));
        assertTrue(less.lesserThan(coords));
        assertTrue(!more.lesserThan(coords));
        assertTrue(!diagonal.lesserThan(coords));
    }

    @Test
    public void testGreaterThanOrEqualTo() {
        Coords less = new Coords(0, 4, 9);
        Coords more = new Coords(1, 10, 20);
        Coords diagonal = new Coords(-1, 5, 11);
        assertTrue(coords.greaterThanOrEqualTo(coords));
        assertTrue(!less.greaterThanOrEqualTo(coords));
        assertTrue(more.greaterThanOrEqualTo(coords));
        assertTrue(!diagonal.greaterThanOrEqualTo(coords));
    }

    @Test
    public void testToString() {
        assertEquals("(0,5,10)", coords.toString());
    }

}
