/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gameinanimates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import otmkurssiprojekti.domain.gameobject.archetypes.interactiveobject.ActivationType;
import static otmkurssiprojekti.domain.gameobject.archetypes.interactiveobject.ActivationType.SIGNAL_ONLY;
import static otmkurssiprojekti.domain.gameobject.archetypes.interactiveobject.InteractiveObjectArchetype.SWITCH;
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
        at = SIGNAL_ONLY;
        children = new ArrayList<>();
        action = (i) -> i.setSolid(false);
        into = new InteractiveObject(id, solid, coords, at, children, action);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetId() {
        assertThat(into.getId(), is(id));
    }

    @Test
    public void testIsTransparent() {
        assertThat(into.isTransparent(), is(InteractiveObject.TRANSPARENT));
    }

    @Test
    public void testIsSolid() {
        assertThat(into.isSolid(), is(solid));
    }

    @Test
    public void testGetCoords() {
        assertThat(into.getCoords(), is(coords));
    }

    @Test
    public void testGetDirection() {
        assertThat(into.getDirection(), is(InteractiveObject.DIRECTION));
    }

    @Test
    public void testGetChildren() {
        assertThat(into.getChildren(), is(children));
    }

    @Test
    public void testGetAction() {
        assertThat(into.getAction(), is(action));
    }

    @Test
    public void testSetSolid() {
        into.setSolid(false);
        assertThat(into.isSolid(), is(false));
    }

    @Test
    public void testMove() {
        into.move(UP);
        assertThat(into.getCoords(), is(new Coords(0, 1, 0)));
    }

    @Test
    public void testTurn() {
        into.turn(LEFT);
        assertThat(into.getDirection(), is(LEFT));
    }

    @Test
    public void testAddChild() {
        InteractiveObject into2 = new InteractiveObject(SWITCH, coords, children);
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
        assertFalse(into.isSolid());
    }

}
