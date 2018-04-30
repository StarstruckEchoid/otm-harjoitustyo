/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.level;

import otmkurssiprojekti.domain.level.BasicGameLevel;
import otmkurssiprojekti.domain.gameobject.interfaces.GameObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LinkObject;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gameinanimates.ImmutableObject;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class BasicGameLevelTest {

    private BasicGameLevel glvl;
    private String levelName;
    private BasicPlayerCharacter player;
    private List<NonPlayerCharacter> npcs;
    private List<ImmutableObject> blocks;
    private List<InteractiveObject> interactives;
    private List<LinkObject> levelLinks;
    private List<PointsBall> points;

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
        levelName = "testLevel";
        player = new BasicPlayerCharacter(PlayerCharacterArchetype.THIEF, new Coords(0, 0, 1), Direction.DOWN);

        npcs = new ArrayList<>();
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.VILLAGER, new Coords(10, 10, 4), Direction.DOWN));

        blocks = new ArrayList<>();
        blocks.add(new ImmutableObject(',', true, false, new Coords(8, 6, 0), Direction.DOWN));

        interactives = new ArrayList<>();
        levelLinks = new ArrayList<>();
        points = new ArrayList<>();

        glvl = new BasicGameLevel(levelName, player, npcs, blocks, interactives, levelLinks, points);
    }

    @After
    public void tearDown() {
    }

}
