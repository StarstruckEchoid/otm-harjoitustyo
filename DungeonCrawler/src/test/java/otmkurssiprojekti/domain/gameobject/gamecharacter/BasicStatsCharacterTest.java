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
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.Destructible;
import otmkurssiprojekti.domain.gameobject.interfaces.Hurtful;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import static otmkurssiprojekti.domain.gameobject.location.Direction.DOWN;

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
        bsc = new BasicStatsCharacter(20, 100, 100, 0, 0, new Coords(0, 0, 0), DOWN) {
            @Override
            public char getId() {
                return 'B';
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
        assertTrue(bsc.getHp() == 0);
    }

    @Test
    public void testDoDamage() {
        Destructible gc = new HostileNonPlayerCharacter('_', 10, 0, 0, 0, 0, 0, new Coords(), DOWN);
        gc.takeDamage(bsc);
        assertTrue(gc.isDead());
    }

    @Test
    public void testTakeDamage_StatsCharacter() {
        Hurtful sc = bsc;
        bsc.takeDamage(sc);
        assertTrue(bsc.isDead());
    }

    @Test
    public void testEquals() {
        assertTrue(bsc.equals(bsc));
    }

}
