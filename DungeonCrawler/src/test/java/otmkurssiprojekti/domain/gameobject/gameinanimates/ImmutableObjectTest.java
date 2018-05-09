/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gameinanimates;

import otmkurssiprojekti.domain.gameobject.gameinanimates.Block;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.domain.gameobject.archetypes.BlockArchetype;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class ImmutableObjectTest {

    private char id;
    private boolean transparent;
    private boolean solid;
    private Coords coords;
    private Direction direction;
    private Block immo;

    public ImmutableObjectTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        id = ',';
        transparent = false;
        solid = false;
        coords = new Coords(0, 0, 0);
        direction = Direction.UP;
        immo = new Block(id, transparent, solid, coords, direction);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAllArchetypeConstructors() {
        for (BlockArchetype arche : BlockArchetype.values()) {
            testArchetypeConstructor(arche);
        }
    }

    public void testArchetypeConstructor(BlockArchetype arche) {
        Block immarch = new Block(arche, new Coords(0, 0, 0), Direction.UP);
        assertTrue("ID should have been " + arche.getId() + " but was " + immarch.getId(), immarch.getId() == arche.getId());
        assertTrue("Transparency should have been " + arche.isTransparent() + " but was " + immarch.isTransparent(), immarch.isTransparent() == arche.isTransparent());
        assertTrue("Solid should have been " + arche.isSolid() + " but was " + immarch.isSolid(), immarch.isSolid() == arche.isSolid());
    }

    @Test
    public void testGetId() {
        assertTrue(immo.getId() == id);
    }

    @Test
    public void testIsTransparent() {
        assertTrue(immo.isTransparent() == transparent);
    }

    @Test
    public void testIsSolid() {
        assertTrue(immo.isSolid() == solid);
    }

    @Test
    public void testGetCoords() {
        assertTrue(immo.getCoords().equals(coords));
    }

    @Test
    public void testGetDirection() {
        assertTrue(immo.getDirection().equals(direction));
    }

}
