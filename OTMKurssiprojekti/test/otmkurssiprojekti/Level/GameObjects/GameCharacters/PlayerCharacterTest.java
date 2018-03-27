/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects.GameCharacters;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.Level.GameObjects.GameObject;

/**
 *
 * @author Juho Gröhn
 */
public class PlayerCharacterTest {

    private PlayerCharacter pc;

    public PlayerCharacterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        pc = new PlayerCharacter(100, 10, 10);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class PlayerCharacter.
     */
    @Test
    public void testGetId() {
        assertTrue(pc.getId() == '@');
    }

    /**
     * Test of touch method, of class PlayerCharacter.
     */
    @Test
    public void testTouch() {
        pc.touch(pc);
    }

    /**
     * Test of interact method, of class PlayerCharacter.
     */
    @Test
    public void testInteract() {
        pc.interact(pc);
    }

}
