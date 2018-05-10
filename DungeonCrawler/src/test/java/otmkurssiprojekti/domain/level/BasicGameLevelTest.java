/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.level;

import java.util.*;
import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype.RAT;
import static otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype.ASSASSIN;
import static otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype.SOLDIER;
import static otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype.WARRIOR;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.BasicNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gameinanimates.Block;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LevelLink;
import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.interfaces.GameObject;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import static otmkurssiprojekti.domain.gameobject.location.Direction.DOWN;
import static otmkurssiprojekti.domain.gameobject.location.Direction.RIGHT;
import static otmkurssiprojekti.domain.gameobject.location.Direction.UP;
import static otmkurssiprojekti.domain.level.BasicGameLevel.hasCoords;

/**
 *
 * @author Juho Gröhn
 */
public class BasicGameLevelTest {

    private BasicGameLevel gameLevel;
    private String levelName;

    private PlayerCharacter player;
    private PlayerCharacter newPlayer;

    private Coords monsterCoords;
    private BasicNonPlayerCharacter monster;
    private List<NonPlayerCharacter> nonPlayerCharacters;

    private List<Block> blocks;

    private List<InteractiveObject> interactiveObjects;

    private List<LevelLink> levelLinks;

    private List<PointsBall> pointsBalls;

    private Coords solidBlockCoords;

    private GameLevel otherLevel;

    public BasicGameLevelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        levelName = "testLevel.txt";
        player = new BasicPlayerCharacter(SOLDIER, new Coords(0, 0, 1), DOWN);
        newPlayer = new BasicPlayerCharacter(ASSASSIN, new Coords(3, 3, 2), DOWN);

        nonPlayerCharacters = new ArrayList<>();
        monsterCoords = new Coords(0, 3, 0);
        monster = new HostileNonPlayerCharacter(RAT, monsterCoords, DOWN);
        nonPlayerCharacters.add(monster);

        blocks = new ArrayList<>();

        solidBlockCoords = new Coords(8, 6, 0);

        blocks.add(new Block('0', false, true, solidBlockCoords, DOWN));

        interactiveObjects = new ArrayList<>();
        levelLinks = new ArrayList<>();
        levelLinks.add(new LevelLink('[', new Coords(7, 7, 7), levelName));
        pointsBalls = new ArrayList<>();

        gameLevel = new BasicGameLevel(levelName, player, nonPlayerCharacters, blocks, interactiveObjects, levelLinks, pointsBalls);

        otherLevel = new BasicGameLevel("other.txt", new BasicPlayerCharacter(WARRIOR, solidBlockCoords, DOWN), new ArrayList<>(), blocks, interactiveObjects, new ArrayList<>(), pointsBalls);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getLevelName method, of class BasicGameLevel.
     */
    @Test
    public void testGetLevelName() {
        assertThat(gameLevel.getLevelName(), is(levelName));
    }

    /**
     * Test of getPlayer method, of class BasicGameLevel.
     */
    @Test
    public void testGetPlayer() {
        assertThat(gameLevel.getPlayer(), is(player));
    }

    /**
     * Test of getNonPlayerCharacters method, of class BasicGameLevel.
     */
    @Test
    public void testGetNonPlayerCharacters() {
        assertThat(gameLevel.getNonPlayerCharacters(), is(nonPlayerCharacters));
    }

    /**
     * Test of getBlocks method, of class BasicGameLevel.
     */
    @Test
    public void testGetBlocks() {
        assertThat(gameLevel.getBlocks(), is(blocks));
    }

    /**
     * Test of getInteractiveObjects method, of class BasicGameLevel.
     */
    @Test
    public void testGetInteractiveObjects() {
        assertThat(gameLevel.getInteractiveObjects(), is(interactiveObjects));
    }

    /**
     * Test of getLevelLinks method, of class BasicGameLevel.
     */
    @Test
    public void testGetLevelLinks() {
        assertThat(gameLevel.getLevelLinks(), is(levelLinks));
    }

    /**
     * Test of getPointsBalls method, of class BasicGameLevel.
     */
    @Test
    public void testGetPointsBalls() {
        assertThat(gameLevel.getPointsBalls(), is(pointsBalls));
    }

    /**
     * Test of setPlayer method, of class BasicGameLevel.
     */
    @Test
    public void testSetPlayer() {
        gameLevel.setPlayer(newPlayer);
        assertThat(gameLevel.getPlayer(), is(newPlayer));
    }

    /**
     * Test of isInaccessible method, of class BasicGameLevel.
     */
    @Test
    public void testIsInaccessible1() {
        assertTrue(gameLevel.isInaccessible(solidBlockCoords));
    }

    /**
     * Test of isInaccessible method, of class BasicGameLevel.
     */
    @Test
    public void testIsInaccessible2() {
        assertFalse(gameLevel.isInaccessible(monsterCoords));
    }

    /**
     * Test of isInaccessible method, of class BasicGameLevel.
     */
    @Test
    public void testIsInaccessible3() {
        assertTrue(gameLevel.isInaccessible(new Coords(1_000, 1_000, 1_000)));
    }

    /**
     * Test of isInaccessible method, of class BasicGameLevel.
     */
    @Test
    public void testIsInaccessible4() {
        assertTrue(gameLevel.isInaccessible(new Coords(-10, -10, -10)));
    }

    /**
     * Test of hasCoords method, of class BasicGameLevel.
     */
    @Test
    public void testHasCoords1() {
        assertTrue(hasCoords(solidBlockCoords));
    }

    /**
     * Test of hasCoords method, of class BasicGameLevel.
     */
    @Test
    public void testHasCoords2() {
        assertFalse(hasCoords(new Coords(1_000, 1_000, 1_000)));
    }

    /**
     * Test of hasSolidBlockAt method, of class BasicGameLevel.
     */
    @Test
    public void testHasSolidBlockAt1() {
        assertTrue(gameLevel.hasSolidBlockAt(solidBlockCoords));
    }

    /**
     * Test of hasSolidBlockAt method, of class BasicGameLevel.
     */
    @Test
    public void testHasSolidBlockAt2() {
        assertFalse(gameLevel.hasSolidBlockAt(monsterCoords));
    }

    /**
     * Test of movePlayer method, of class BasicGameLevel.
     */
    @Test
    public void testMovePlayer() {
        gameLevel.movePlayer(RIGHT);

        assertThat(gameLevel.getPlayer().getCoords(), is(new Coords(1, 0, 1)));
    }

    /**
     * Test of moveMobile method, of class BasicGameLevel.
     */
    @Test
    public void testMoveMobile() {
        gameLevel.moveMobile(monster, UP);

        assertThat(monster.getCoords(), is(new Coords(0, 4, 0)));
    }

    /**
     * Test of hashCode method, of class BasicGameLevel.
     */
    @Test
    public void testHashCode() {
        int hash = gameLevel.hashCode();
        int hashOther = otherLevel.hashCode();

        assertTrue(hash != hashOther);
    }

    /**
     * Test of equals method, of class BasicGameLevel.
     */
    @Test
    public void testEquals1() {
        assertTrue(gameLevel.equals(gameLevel));
    }

    /**
     * Test of equals method, of class BasicGameLevel.
     */
    @Test
    public void testEquals2() {
        assertFalse(gameLevel.equals(otherLevel));
    }

    /**
     * Test of equals method, of class BasicGameLevel.
     */
    @Test
    public void testEquals3() {
        assertFalse(otherLevel.equals(gameLevel));
    }

    @Test
    public void testEquals4() {
        assertFalse(gameLevel.equals(null));
    }
    
    @Test
    public void testEquals5(){
        assertFalse(gameLevel.equals(levelName));
    }

    /**
     * Test of toString method, of class BasicGameLevel.
     */
    @Test
    public void testToString() {
        String string = gameLevel.toString();
        String otherString = otherLevel.toString();

        assertFalse(string.equals(otherString));
    }

    /**
     * Test of doGameTick method, of class BasicGameLevel.
     */
    @Test
    public void testDoGameTick() {
        gameLevel.doGameTick();

        assertFalse(monster.toString() + "has not moved.", monster.getCoords().equals(monsterCoords));
    }

    /**
     * Test of getGameObjects method, of class BasicGameLevel.
     */
    @Test
    public void testGetGameObjects() {
        List<GameObject> gobjs = gameLevel.getGameObjects();
        assertTrue(gobjs.containsAll(blocks));
        assertTrue(gobjs.containsAll(interactiveObjects));
        assertTrue(gobjs.containsAll(levelLinks));
        assertTrue(gobjs.containsAll(nonPlayerCharacters));
        assertTrue(gobjs.containsAll(pointsBalls));
    }

    /**
     * Test of playerInteract method, of class BasicGameLevel.
     */
    @Test
    public void testPlayerInteract() {
        assertThat(gameLevel.playerInteract(), is(empty()));
    }

    @Test(timeout = 100)
    public void testPlayerAttack() {
        player.move(monsterCoords);
        player.move(DOWN);
        while (!monster.isDead()) {
            gameLevel.playerAttack(UP);
        }
        assertThat(monster.isDead(), is(true));
        assertThat(player.getPoints(), is(monster.getPoints()));
    }

}
