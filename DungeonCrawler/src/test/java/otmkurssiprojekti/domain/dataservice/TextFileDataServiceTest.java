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
    private final String gameSave = "24000330022";
    private final GameLevel gameLevel = new BasicGameLevel(
            "test",
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
     * Test of setGameSave method, of class TextFileDataService.
     */
    @Test
    public void testSetGameSave1() {
        testSetPlayer();
        dataService.setGameSave(gameSave);
    }

    @Test
    public void testSetGameSave2() {
        testSetPlayer();
        dataService.setGameSave("axxr");
    }

    /**
     * Test of setGameLevel method, of class TextFileDataService.
     */
    @Test
    public void testSetGameLevel() {
        dataService.setGameLevel(gameLevel);
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
        GameSave gameSave1 = dataService.fetchGameSave(gameSave);
        assertEquals(gameSave1, new GameSave(new Date(Long.parseLong(gameSave)), gameLevel));
    }

    /**
     * Test of fetchGameSave method, of class TextFileDataService.
     */
    @Test
    public void testFetchGameSave_0args() {
        testSaveGame_GameSave();
        GameSave gameSave1 = dataService.fetchGameSave();

        assertEquals(gameSave1, new GameSave(new Date(Long.parseLong(gameSave)), gameLevel));
    }

    /**
     * Test of saveGame method, of class TextFileDataService.
     */
    @Test
    public void testSaveGame_GameSave() {
        testSetPlayer();
        dataService.saveGame(new GameSave(new Date(Long.parseLong(gameSave)), gameLevel));
    }

    /**
     * Test of saveGame method, of class TextFileDataService.
     */
    @Test
    public void testSaveGame_0args() {
        testSetGameLevel();
        testSetPlayer();
        dataService.saveGame();
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

}
