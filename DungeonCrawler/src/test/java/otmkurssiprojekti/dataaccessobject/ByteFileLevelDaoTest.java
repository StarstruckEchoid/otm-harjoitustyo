/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.domain.level.BasicGameLevel;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.BasicNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
public class ByteFileLevelDaoTest {

    private Path directory;
    private ByteFileLevelDao bfldao;
    private GameLevel glvl;

    List<NonPlayerCharacter> npcs;
    BasicNonPlayerCharacter npc;

    public ByteFileLevelDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        directory = Files.createTempDirectory("testDir");
        bfldao = new ByteFileLevelDao(directory);
        npcs = new ArrayList<>();
        npc = new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.VILLAGER, new Coords(6, 7, 2), Direction.DOWN);
        npcs.add(npc);

        glvl = new BasicGameLevel(
                "testLevel",
                new BasicPlayerCharacter(PlayerCharacterArchetype.THIEF, new Coords(), Direction.DOWN),
                npcs,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSaveLoadLevel() {
        bfldao.saveLevel(glvl);
        GameLevel glvl2 = bfldao.loadLevel(glvl.toString());

        assertTrue("Levels are not equal according to equals().", glvl.equals(glvl2));
    }

    @Test
    public void testSaveLoadWithName() {
        String newName = "testName";
        bfldao.saveLevel(glvl, newName);
        GameLevel glvl2 = bfldao.loadLevel(newName);

        assertTrue("Name is not the same!", glvl2.getLevelName().equals(glvl.getLevelName()));
        assertTrue("Levels are not equal according to equals().", glvl.equals(glvl2));
    }

}
