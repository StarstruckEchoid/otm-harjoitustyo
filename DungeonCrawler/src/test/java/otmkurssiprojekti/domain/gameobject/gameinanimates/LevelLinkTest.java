/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gameinanimates;

import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author gjuho
 */
public class LevelLinkTest {

    private char id;
    private Coords coords;
    private String address;
    private LevelLink levelLink;
    private LevelLink otherLink;

    public LevelLinkTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        id = '[';
        coords = new Coords(8, 2, 7);
        address = "testLevel.txt";
        levelLink = new LevelLink(id, coords, address);
        otherLink = new LevelLink(id, new Coords(0, 2, 3), "otherOther.txt");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class LevelLink.
     */
    @Test
    public void testGetId() {
        assertThat(levelLink.getId(), is(id));
    }

    /**
     * Test of isTransparent method, of class LevelLink.
     */
    @Test
    public void testIsTransparent() {
        assertThat(levelLink.isTransparent(), is(LevelLink.TRANSPARENT));
    }

    /**
     * Test of isSolid method, of class LevelLink.
     */
    @Test
    public void testIsSolid() {
        assertThat(levelLink.isSolid(), is(LevelLink.SOLID));
    }

    /**
     * Test of getCoords method, of class LevelLink.
     */
    @Test
    public void testGetCoords() {
        assertThat(levelLink.getCoords(), is(coords));
    }

    /**
     * Test of getDirection method, of class LevelLink.
     */
    @Test
    public void testGetDirection() {
        assertThat(levelLink.getDirection(), is(LevelLink.DIRECTION));
    }

    /**
     * Test of getLinkAddress method, of class LevelLink.
     */
    @Test
    public void testGetLinkAddress() {
        assertThat(levelLink.getLinkAddress(), is(address));
    }

    /**
     * Test of hashCode method, of class LevelLink.
     */
    @Test
    public void testHashCode() {
        assertFalse(levelLink.hashCode() == otherLink.hashCode());
    }

    /**
     * Test of equals method, of class LevelLink.
     */
    @Test
    public void testEquals1() {
        assertTrue(levelLink.equals(levelLink));
    }

    /**
     * Test of equals method, of class LevelLink.
     */
    @Test
    public void testEquals2() {
        assertFalse(levelLink.equals(otherLink));
    }

    /**
     * Test of equals method, of class LevelLink.
     */
    @Test
    public void testEquals3() {
        assertFalse(otherLink.equals(levelLink));
    }

    /**
     * Test of equals method, of class LevelLink.
     */
    @Test
    public void testEquals4() {
        assertTrue(otherLink.equals(otherLink));
    }

    /**
     * Test of toString method, of class LevelLink.
     */
    @Test
    public void testToString() {
        assertFalse(levelLink.toString().equals(otherLink.toString()));
    }

}
