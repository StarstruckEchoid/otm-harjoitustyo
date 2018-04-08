/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import otmkurssiprojekti.DataAccessObject.ByteFileLevelDao;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.Level.GameLevel;
import otmkurssiprojekti.Level.GameObjects.Archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.Level.GameObjects.NonPlayerCharacter;
import otmkurssiprojekti.Level.GameObjects.PlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
public class ByteFileLevelDaoTest {

    private Path source;
    private ByteFileLevelDao bfldao;
    private GameLevel glvl;

    List<NonPlayerCharacter> npcs;
    NonPlayerCharacter npc;

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
        source = Files.createTempDirectory("testDir");
        bfldao = new ByteFileLevelDao(source);
        npcs = new ArrayList<>();
        npc = new NonPlayerCharacter(NonPlayerCharacterArchetype.VILLAGER, new Coords(6, 7, 2), Direction.DOWN);
        npcs.add(npc);

        glvl = new GameLevel(
                "testLevel",
                new PlayerCharacter(new Coords(3, 2, 1), 0, 0, Direction.DOWN, 0),
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
        bfldao.saveLevel(source, glvl);
        GameLevel glvl2 = bfldao.loadLevel(source, glvl.getLevelName());

        assertTrue("Player coords not the same!", glvl2.getPlayer().getCoords().equals(glvl.getPlayer().getCoords()));
        assertTrue("Name is not the same!", glvl2.getLevelName().equals(glvl.getLevelName()));
        assertTrue("Does not contain the same npcs!", glvl2.getNpcs().contains(npc));
    }

}
