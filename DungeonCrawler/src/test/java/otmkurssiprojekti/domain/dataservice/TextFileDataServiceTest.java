/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.dataservice;

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
import otmkurssiprojekti.dataaccessobject.TextFileLevelDao;
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
public class TextFileDataServiceTest {

    private String levelsDirName;
    private String usersDirName;
    private final String user = "testUser";
    private final String player = "testPlayer";
    private final String saveName = "24000330022";
    private final String levelName = "test.txt";
    private final GameLevel gameLevel = new BasicGameLevel(
            levelName,
            new BasicPlayerCharacter(PlayerCharacterArchetype.THIEF, new Coords(), Direction.DOWN),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
    );
    private TextFileDataService dataService;

    public TextFileDataServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        Path levelsDir = Files.createTempDirectory("testLevels");
        levelsDirName = levelsDir.toString();
        new TextFileLevelDao(levelsDir).saveLevel(gameLevel);
        usersDirName = Files.createTempDirectory("testUsers").toString();
        dataService = new TextFileDataService();
    }

    @After
    public void tearDown() throws IOException {
    }

    /**
     * Test of setLevelsDir method, of class TextFileDataService.
     */
    @Test
    public void testSetLevelsDir() {
        dataService.setLevelsDir(levelsDirName);
    }

    /**
     * Test of setUsersDir method, of class TextFileDataService.
     */
    @Test
    public void testSetUsersDir() {
        dataService.setUsersDir(usersDirName);
    }

    /**
     * Test of setUser method, of class TextFileDataService.
     */
    @Test
    public void testSetUser() {
        testSetUsersDir();
        dataService.setUser(user);
    }

    /**
     * Test of setPlayer method, of class TextFileDataService.
     */
    @Test
    public void testSetPlayer() {
        testSetUser();
        dataService.setPlayer(player);
    }

    /**
     * Test of setGameLevel method, of class TextFileDataService.
     */
    @Test
    public void testSetGameLevel() {
        testSetLevelsDir();
        dataService.loadLevel(gameLevel.getLevelName());
    }

    /**
     * Test of fetchGameLevel method, of class TextFileDataService.
     */
    @Test
    public void testFetchGameLevel() {
        testSetLevelsDir();
        GameLevel gameLevel2 = dataService.fetchGameLevel(gameLevel.getLevelName());

        assertThat(gameLevel2, is(gameLevel));
    }

    /**
     * Test of fetchGameSaves method, of class TextFileDataService.
     */
    @Test
    public void testFetchGameSaves() {
        testSetPlayer();
        List<GameSave> gameSaves = dataService.fetchGameSaves();

        assertTrue(gameSaves.isEmpty());
    }

    /**
     * Test of fetchGameSave method, of class TextFileDataService.
     */
    @Test
    public void testFetchGameSave_String() {
        testSaveGame_GameSave();
        GameSave gameSave1 = dataService.fetchGameSave(saveName);
        assertEquals(gameSave1, new GameSave(new Date(Long.parseLong(saveName)), gameLevel));
    }

    /**
     * Test of saveGame method, of class TextFileDataService.
     */
    @Test
    public void testSaveGame_GameSave() {
        testSetPlayer();
        dataService.saveGame(new GameSave(new Date(Long.parseLong(saveName)), gameLevel));
    }

    /**
     * Test of saveGame method, of class TextFileDataService.
     */
    @Test
    public void testSaveGame_GameLevel() {
        testSetGameLevel();
        testSetPlayer();
        dataService.saveGame(gameLevel);
    }

    /**
     * Test of fetchPlayers method, of class TextFileDataService.
     */
    @Test
    public void testFetchPlayers() {
        testSetPlayer();
        List<String> players = dataService.fetchPlayers();
        assertTrue(players.contains(player));
    }

    /**
     * Test of fetchUsers method, of class TextFileDataService.
     */
    @Test
    public void testFetchUsers() {
        testSetUser();
        List<String> users = dataService.fetchUsers();
        assertTrue(users.contains(user));
    }

    /**
     * Test of loadLevel method, of class TextFileDataService.
     */
    @Test
    public void testLoadLevel() {
        testSetLevelsDir();
        dataService.loadLevel(levelName);
    }

    /**
     * Test of loadSave method, of class TextFileDataService.
     */
    @Test
    public void testLoadSave() {
        testSaveGame_GameSave();
        dataService.loadSave(saveName);
    }

    /**
     * Test of fetchGameLevel method, of class TextFileDataService.
     */
    @Test
    public void testFetchGameLevel_String() {
        testSetLevelsDir();
        GameLevel gl = dataService.fetchGameLevel(levelName);
        assertThat(gl, is(gameLevel));
    }

    /**
     * Test of fetchGameLevel method, of class TextFileDataService.
     */
    @Test
    public void testFetchGameLevel_0args() {
        testSetGameLevel();
        GameLevel gl = dataService.fetchGameLevel();

        assertThat(gl, is(gameLevel));
    }

    /**
     * Test of fetchGameSave method, of class TextFileDataService.
     */
    @Test
    public void testFetchGameSave() {
        testSaveGame_GameSave();
        GameSave gs = dataService.fetchGameSave(saveName);
        assertTrue(gs != null);
    }

    /**
     * Test of saveGame method, of class TextFileDataService.
     */
    @Test
    public void testSaveGame_0args() {
        testSetPlayer();
        testLoadLevel();
        dataService.saveGame();
    }

}
