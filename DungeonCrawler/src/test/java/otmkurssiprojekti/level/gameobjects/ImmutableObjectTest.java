/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects;

import otmkurssiprojekti.level.gameobjects.gameinanimates.ImmutableObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.level.gameobjects.archetypes.ImmutableObjectArchetype;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;

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
    private ImmutableObject immo;

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
        immo = new ImmutableObject(id, transparent, solid, coords, direction);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAllArchetypeConstructors() {
        for (ImmutableObjectArchetype arche : ImmutableObjectArchetype.values()) {
            testArchetypeConstructor(arche);
        }
    }

    public void testArchetypeConstructor(ImmutableObjectArchetype arche) {
        ImmutableObject immarch = new ImmutableObject(arche, new Coords(0, 0, 0), Direction.UP);
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
