/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.level.BasicGameLevel;
import otmkurssiprojekti.level.GameLevel;
import otmkurssiprojekti.level.gameobjects.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.level.gameobjects.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;
import otmkurssiprojekti.level.gameobjects.gamecharacter.nonplayercharacter.BasicNonPlayerCharacter;
import otmkurssiprojekti.level.gameobjects.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.level.gameobjects.gamecharacter.playercharacter.PlayerCharacter;
import otmkurssiprojekti.level.gameobjects.interfaces.Mobile;

/**
 *
 * @author Juho Gröhn
 */
public class AITest {

    private BasicNonPlayerCharacter npc;
    private List<Mobile> mobileObjects;
    private GameLevel gameLevel;

    public AITest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        npc = new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.RAT, new Coords(0, 0, 0), Direction.DOWN);
        mobileObjects = new ArrayList<>();
        mobileObjects.add(npc);

        gameLevel = new BasicGameLevel(
                "test",
                new PlayerCharacter(PlayerCharacterArchetype.THIEF, new Coords(3, 3, 0), Direction.DOWN),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()
        );
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = 50)
    public void testBR1() {
        Coords c1 = new Coords(0, 0, 0);
        Coords c2 = new Coords(0, 0, 0);
        Stack<Coords> expected = new Stack<>();

        testBestRoute(c1, c2, expected);
    }

    @Test(timeout = 500)
    public void testBR2() {
        Coords c1 = new Coords(0, 0, 0);
        Coords c2 = new Coords(1, 0, 0);
        Stack<Coords> expected = new Stack<>();
        expected.push(c2);

        testBestRoute(c1, c2, expected);
    }

    @Test(timeout = 5000)
    public void testBR3() {
        int n = 10;
        Coords c1 = new Coords(0, 0, 0);
        Coords c2 = new Coords(n, 0, 0);
        Stack<Coords> expected = new Stack<>();
        for (int i = n; i > 0; i--) {
            expected.push(new Coords(i, 0, 0));
        }

        testBestRoute(c1, c2, expected);

    }

    public void testBestRoute(Coords c1, Coords c2, Stack<Coords> expected) {
        Stack<Coords> actual = AI.bestRoute(c1, c2, gameLevel);
        assertTrue(actual.equals(expected));
    }

    @Test(timeout = 50)
    public void testGR1() {
        Coords c1 = new Coords(0, 0, 0);
        Coords c2 = new Coords(0, 0, 0);
        Stack<Coords> expected = new Stack<>();

        testGreedyRoute(c1, c2, expected);
    }

    @Test(timeout = 500)
    public void testGR2() {
        Coords c1 = new Coords(0, 0, 0);
        Coords c2 = new Coords(1, 0, 0);
        Stack<Coords> expected = new Stack<>();
        expected.push(c2);

        testGreedyRoute(c1, c2, expected);
    }

    @Test(timeout = 5000)
    public void testGR3() {
        int n = 10;
        Coords c1 = new Coords(0, 0, 0);
        Coords c2 = new Coords(n, 0, 0);
        Stack<Coords> expected = new Stack<>();
        for (int i = n; i > 0; i--) {
            expected.push(new Coords(i, 0, 0));
        }

        testGreedyRoute(c1, c2, expected);

    }

    public void testGreedyRoute(Coords c1, Coords c2, Stack<Coords> expected) {
        Stack<Coords> actual = AI.greedyRoute(c1, c2, gameLevel);
        assertTrue(actual.equals(expected));
    }

}
