/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects;

import otmkurssiprojekti.level.gameobjects.GameCharacter;
import otmkurssiprojekti.level.gameobjects.StatsCharacter;
import otmkurssiprojekti.level.gameobjects.BasicStatsCharacter;
import otmkurssiprojekti.level.gameobjects.NonPlayerCharacter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.level.gameobjects.archetypes.Behaviour;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class BasicStatsCharacterTest {

    private BasicStatsCharacter bsc;

    public BasicStatsCharacterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        bsc = new BasicStatsCharacter(20, 100, 100, 0, 0, new Coords(0, 0, 0), Direction.DOWN) {
            @Override
            public char getId() {
                return 'B';
            }

            @Override
            public Behaviour getBehaviour() {
                return Behaviour.PASSIVE;
            }
        };
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIsDead1() {
        assertTrue(!bsc.isDead());
    }

    public void testIsDead2() {
        bsc.takeDamage(200);
        assertTrue(bsc.isDead());
    }

    @Test
    public void testTakeDamage_int() {
        bsc.takeDamage(20);
        assertTrue(bsc.hp == 0);
    }

    @Test
    public void testGetAttackDamage() {
        int attStr = bsc.getAttackDamage();
        assertTrue(bsc.str == attStr);
    }

    @Test
    public void testGetCriticalChance() {
        int critC = bsc.getCriticalChance();
        assertTrue(bsc.per == critC);
    }

    @Test
    public void testGetSlowness() {
        int expected = 10;
        int slow = bsc.getSlowness();
        assertTrue("Slowness not equal to" + expected + ". Instead was " + slow + ".", slow == expected);
    }

    @Test
    public void testDoDamage() {
        GameCharacter gc = new NonPlayerCharacter(Behaviour.PATROL, '_', 10, 0, 0, 0, 0, 0, new Coords(), Direction.DOWN);
        bsc.doDamage(gc);
        assertTrue(gc.isDead());
    }

    @Test
    public void testTakeDamage_StatsCharacter() {
        StatsCharacter sc = bsc;
        bsc.takeDamage(sc);
        assertTrue(bsc.isDead());
    }

    @Test
    public void testEquals() {
        assertTrue(bsc.equals(bsc));
    }

}
