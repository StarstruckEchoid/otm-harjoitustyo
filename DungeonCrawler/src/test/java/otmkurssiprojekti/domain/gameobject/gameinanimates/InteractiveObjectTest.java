/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gameinanimates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import otmkurssiprojekti.domain.gameobject.archetypes.ActivationType;
import static otmkurssiprojekti.domain.gameobject.archetypes.ActivationType.SIGNAL_ONLY;
import static otmkurssiprojekti.domain.gameobject.archetypes.InteractiveObjectArchetype.SWITCH;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import static otmkurssiprojekti.domain.gameobject.location.Direction.DOWN;
import static otmkurssiprojekti.domain.gameobject.location.Direction.LEFT;
import static otmkurssiprojekti.domain.gameobject.location.Direction.UP;

/**
 *
 * @author gjuho
 */
public class InteractiveObjectTest {
    
    private char id;
    private boolean solid;
    private Coords coords;
    private Direction dir;
    private ActivationType at;
    private List<InteractiveObject> children;
    private Consumer<InteractiveObject> action;
    private InteractiveObject into;
    
    public InteractiveObjectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        id = 'D';
        solid = true;
        coords = new Coords(0, 0, 0);
        dir = DOWN;
        at = SIGNAL_ONLY;
        children = new ArrayList<>();
        action = (i) -> i.setSolid(false);
        into = new InteractiveObject(id, solid, coords, dir, at, children, action);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetId() {
        assertTrue(into.getId() == id);
    }
    
    @Test
    public void testIsTransparent() {
        assertTrue(into.isTransparent() == true);
    }
    
    @Test
    public void testIsSolid() {
        assertTrue(into.isSolid() == solid);
    }
    
    @Test
    public void testGetCoords() {
        assertTrue(into.getCoords().equals(coords));
    }
    
    @Test
    public void testGetDirection() {
        assertTrue(into.getDirection().equals(dir));
    }
    
    @Test
    public void testGetChildren() {
        assertTrue(into.getChildren().equals(children));
    }
    
    @Test
    public void testGetAction() {
        assertTrue(into.getAction().equals(action));
    }
    
    @Test
    public void testSetSolid() {
        into.setSolid(false);
        assertTrue(into.isSolid() == false);
    }
    
    @Test
    public void testMove() {
        into.move(UP);
        assertTrue(into.getCoords().equals(new Coords(0, 1, 0)));
    }
    
    @Test
    public void testTurn() {
        into.turn(LEFT);
        assertTrue(into.getDirection().equals(LEFT));
    }
    
    @Test
    public void testAddChild() {
        InteractiveObject into2 = new InteractiveObject(SWITCH, coords, dir, children);
        into.addChild(into2);
        assertTrue(into.getChildren().contains(into2));
    }
    
    @Test
    public void testReactToTouch() {
        into.reactToTouch();
        assertTrue(into.isSolid());
    }
    
    @Test
    public void testReactToPress() {
        into.reactToPress();
        assertTrue(into.isSolid());
    }
    
    @Test
    public void testReactToSignal() {
        into.reactToSignal();
        assertTrue(!into.isSolid());
    }
    
}
