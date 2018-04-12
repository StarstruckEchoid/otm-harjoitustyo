/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.DataAccessObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.GameSave;
import otmkurssiprojekti.Level.BasicGameLevel;
import otmkurssiprojekti.Level.GameObjects.Archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.Level.GameObjects.PlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
public class ByteFileGameSaveDaoTest {

    private Date date;
    private BasicGameLevel glvl;
    private GameSave gsave;
    private GameSave gsave2;
    private Path temp;
    private ByteFileGameSaveDao bfgsdao;

    public ByteFileGameSaveDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        date = new Date(System.currentTimeMillis());
        glvl = new BasicGameLevel(
                "dungeon",
                new PlayerCharacter(PlayerCharacterArchetype.THIEF, new Coords(), Direction.DOWN),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        gsave = new GameSave(date, glvl);

        temp = Files.createTempDirectory("testDir");

        bfgsdao = new ByteFileGameSaveDao(temp);

        bfgsdao.saveGame(gsave);
        Path path = Paths.get(
                temp.toString(),
                Long.toString(date.getTime())
        );
        gsave2 = bfgsdao.loadSave(path.toFile());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDateIntegrity() {
        assertTrue(gsave.getSaveDate().equals(gsave2.getSaveDate()));
    }

    @Test
    public void testGameLevelIntegrity() {
        assertTrue(gsave.getGameLevel().equals(gsave2.getGameLevel()));
    }

    @Test
    public void testGameSaveIntegrity() {
        assertTrue(gsave.equals(gsave2));
    }

}
