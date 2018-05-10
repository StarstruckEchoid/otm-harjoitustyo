/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject.dataobject;

import static java.lang.System.currentTimeMillis;
import java.util.Date;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import otmkurssiprojekti.domain.level.GameLevel;
import static otmkurssiprojekti.utilityclasses.TextFileGameLevels.makeGameLevel;

/**
 *
 * @author gjuho
 */
public class GameSaveTest {

    private Date date;
    private GameLevel gameLevel;
    private GameSave gameSave;
    private GameSave otherSave;

    public GameSaveTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        date = new Date(currentTimeMillis());
        gameLevel = makeGameLevel(
                "Starting_Level.txt\n"
                + "\n"
                + "10;1;1;1;1;3,3,0;0\n"
                + "\n"
                + "%;7,10,0\n"
                + "r;8,8,0\n"
                + "d;4,9,0\n"
                + "�;1,1,0\n"
                + "\n"
                + "0,,,,,,,,,,,,,,0\n"
                + "0,,,,,,,,,,,,,,0\n"
                + "0,,,000000000000\n"
                + "0,,,,,,,,,,,,,,0\n"
                + "0,,,,,,,,,,,,,,0\n"
                + "0,,000,,000,,,00\n"
                + "0,,0,,,,0,,,,,,0\n"
                + "0,,0,,,,0,,,,,,0\n"
                + "000000000,,,,,,0\n"
                + "0,,,,,,,0,,,,,,0\n"
                + "0,,,0,,,0,,,,,,0\n"
                + "0,,,0,,,0,,,,,,0\n"
                + "0,,,00000,,,,,,0\n"
                + "0,,,,,,,,,,,,,,0\n"
                + "0,,,,,,,,,,,,,,0\n"
                + "000000000000,,,0\n"
                + "\n"
                + "EMPTY\n"
                + "\n"
                + "d;14,14,0;Second_Level.txt\n"
                + "\n"
                + "EMPTY"
        );

        gameSave = new GameSave(date, gameLevel);
        otherSave = new GameSave(new Date(1_000), null);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getSaveDate method, of class GameSave.
     */
    @Test
    public void testGetSaveDate() {
        assertThat(gameSave.getSaveDate(), is(date));
    }

    /**
     * Test of getGameLevel method, of class GameSave.
     */
    @Test
    public void testGetGameLevel() {
        assertThat(gameSave.getGameLevel(), is(gameLevel));
    }

    /**
     * Test of toString method, of class GameSave.
     */
    @Test
    public void testToString() {
        assertFalse("toString() should not span multiple lines!", gameSave.toString().contains("\n"));
    }

    /**
     * Test of hashCode method, of class GameSave.
     */
    @Test
    public void testHashCode() {
        assertFalse(gameSave.hashCode() == otherSave.hashCode());
    }

    /**
     * Test of equals method, of class GameSave.
     */
    @Test
    public void testEquals1() {
        assertTrue(gameSave.equals(gameSave));
    }

    /**
     * Test of equals method, of class GameSave.
     */
    @Test
    public void testEquals2() {
        assertFalse(gameSave.equals(otherSave));
    }

    /**
     * Test of equals method, of class GameSave.
     */
    @Test
    public void testEqual3() {
        assertFalse(otherSave.equals(gameSave));
    }

    /**
     * Test of equals method, of class GameSave.
     */
    @Test
    public void testEquals4() {
        assertTrue(otherSave.equals(otherSave));
    }

    /**
     * Test of equals method, of class GameSave.
     */
    @Test
    public void testEquals5() {
        assertFalse(gameSave.equals(null));
    }

    /**
     * Test of equals method, of class GameSave.
     */
    @Test
    public void testEquals() {
        assertFalse(gameSave.equals(date));
    }

    /**
     * Test of equals method, of class GameSave.
     */
    @Test
    public void testEquals6() {
        assertFalse(gameSave.equals(new GameSave(date, null)));
    }

}
