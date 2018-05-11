/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.dataaccessobject.dataobject.GameSave;
import otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.domain.level.BasicGameLevel;
import otmkurssiprojekti.domain.level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public class TextFileGameSaveDaoTest {

    private TextFileGameSaveDao dao;
    private Path directory;
    private GameSave gameSave;
    private long nanoTime;
    private Date date;
    private GameLevel gameLevel;

    public TextFileGameSaveDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        directory = Files.createTempDirectory("testDir_GameSaveDao");
        dao = new TextFileGameSaveDao(directory);
        nanoTime = System.currentTimeMillis();
        date = new Date(nanoTime);
        gameLevel = new BasicGameLevel(
                "testLevel",
                new BasicPlayerCharacter(PlayerCharacterArchetype.THIEF, new Coords(), Direction.DOWN),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        gameSave = new GameSave(date, gameLevel);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSaveGame() {
        try {
            dao.saveGame(gameSave);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testLoadSave() {
        testSaveGame();
        try {
            GameSave load = dao.loadSave(Long.toString(nanoTime));
            assertThat(load, is(gameSave));
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testListGameSaves() {
        testSaveGame();
        try {
            List<GameSave> saves = dao.listGameSaves();
            assertTrue(saves.remove(gameSave));
            assertTrue(saves.isEmpty());
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }

}
