/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.dataservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.dataaccessobject.GameLevelDao;
import otmkurssiprojekti.dataaccessobject.TextFileLevelDao;
import otmkurssiprojekti.dataaccessobject.dataobject.GameSave;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.utilityclasses.TextFileGameLevels;

/**
 *
 * @author Juho Gröhn
 */
public class TextFileDataServiceTest {

    private String levelsDirName;
    private String usersDirName;
    private final String user = "TestUser";
    private final String player = "TestPlayer";
    private final String saveName = "24000330022";
    private final String levelName = "Starting_Level.txt";
    private final GameLevel gameLevel = TextFileGameLevels.makeGameLevel(
            levelName + "\n"
            + "\n"
            + "10;1;1;1;1;3,3,0;0\n"
            + "\n"
            + "%;7,10,0\n"
            + "r;8,8,0\n"
            + "d;4,9,0\n"
            + "¨;1,1,0\n"
            + "\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,000000000000\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,000,,000,,,00\n"
            + "0,,0,,,,0,,,,,,0\n"
            + "0,,0,,,,0,,,,,,0\n"
            + "000000000,,,,,,0\n"
            + "0,,,,,,,0,,,,,,0\n"
            + "0,,,0,,,0,,,,,,0\n"
            + "0,,,0,,,0,,,,,,0\n"
            + "0,,,00000,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "000000000000,,,0\n"
            + "\n"
            + "EMPTY\n"
            + "\n"
            + "[;14,14,0;Second_Level.txt\n"
            + "\n"
            + "EMPTY"
    );
    private final String otherLevelName = "Other_Level.txt";
    private final GameLevel otherLevel = TextFileGameLevels.makeGameLevel(
            otherLevelName + "\n"
            + "\n"
            + "200;1;1;1;1;2,2,2;0\n"
            + "\n"
            + "EMPTY\n"
            + "\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,...,,,,,,0\n"
            + "0,,,,,...,,,,,,0\n"
            + "0,,,,,...,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "0,,,,,,,,,,,,,,0\n"
            + "\n"
            + "EMPTY\n"
            + "\n"
            + "EMPTY\n"
            + "\n"
            + "EMPTY"
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
        GameLevelDao dao = new TextFileLevelDao(levelsDir);
        dao.saveLevel(gameLevel);
        dao.saveLevel(otherLevel);
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
        try {
            dataService.setLevelsDir(levelsDirName);

            assertThat(dataService.levelsDir.toString(), is(levelsDirName));
        } catch (IOException | NullPointerException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testSetLevelsDir_invalid() {
        try {
            dataService.setLevelsDir(null);
            fail();
        } catch (IOException | NullPointerException ex) {
            //Failed as expected.
        }
    }

    /**
     * Test of setUsersDir method, of class TextFileDataService.
     */
    @Test
    public void testSetUsersDir() {
        try {
            dataService.setUsersDir(usersDirName);

            assertThat(dataService.usersDir.toString(), is(usersDirName));
        } catch (IOException | NullPointerException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testSetUsersDir_invalid() {
        try {
            dataService.setUsersDir(null);
            fail();
        } catch (IOException | NullPointerException ex) {
            //Failed as expected.
        }

    }

    /**
     * Test of setUser method, of class TextFileDataService.
     */
    @Test
    public void testSetUser() {
        try {
            testSetUsersDir();
            dataService.setUser(user);

            assertThat(dataService.userDir.toString(), is(usersDirName + File.separator + user));
        } catch (IOException | NullPointerException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testSetUser_invalid() {
        try {
            dataService.setUser(user);
            fail();
        } catch (IOException | NullPointerException e) {
            //Failed as expected.
        }
    }

    /**
     * Test of setPlayer method, of class TextFileDataService.
     */
    @Test
    public void testSetPlayer() {
        try {
            testSetUser();
            dataService.setPlayer(player);

            assertThat(dataService.playerDir.toString(), is(usersDirName + File.separator + user + File.separator + player));
        } catch (IOException | NullPointerException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testSetPlayer_invalid() {
        try {
            dataService.setPlayer(player);

            fail();
        } catch (IOException | NullPointerException ex) {
            //Failed as expected.
        }
    }

    @Test
    public void testSetGameLevel1() throws IOException {
        testSetLevelsDir();
        dataService.swapLevel(gameLevel.getLevelName());

        assertTrue(dataService.currentLevel != null);
        assertThat(dataService.currentLevel, is(gameLevel));
    }

    @Test
    public void testSetGameLevel2() throws IOException {
        testSetLevelsDir();
        dataService.swapLevel(otherLevel.getLevelName());

        assertTrue(dataService.currentLevel != null);
        assertThat(dataService.currentLevel, is(otherLevel));
    }

    @Test
    public void testFetchGameLevel() throws IOException {
        testSetLevelsDir();
        GameLevel gameLevel2 = dataService.fetchGameLevel(gameLevel.getLevelName());

        assertThat(gameLevel2, is(gameLevel));
    }

    @Test
    public void testFetchGameSaves1() throws IOException {
        testSetPlayer();
        List<GameSave> gameSaves = dataService.fetchGameSaves();

        assertTrue(gameSaves.isEmpty());
    }

    @Test
    public void testFetchGameSaves2() throws IOException {
        testSetPlayer();
        GameSave gameSave = new GameSave(new Date(Long.parseLong(saveName)), gameLevel);
        dataService.saveGame(gameSave);
        List<GameSave> gameSaves = dataService.fetchGameSaves();

        assertTrue(gameSaves.contains(gameSave));
    }

    /**
     * Test of saveGame method, of class TextFileDataService.
     */
    @Test
    public void testSaveGame_GameSave() throws IOException {
        testSetGameLevel1();
        testSetPlayer();
        dataService.saveGame(new GameSave(new Date(Long.parseLong(saveName)), gameLevel));

        assertThat(dataService.currentLevel, is(gameLevel));
    }

    /**
     * Test of saveGame method, of class TextFileDataService.
     */
    @Test
    public void testSaveGame_GameLevel1() throws IOException {
        testSetGameLevel1();
        testSetPlayer();
        dataService.saveGame(gameLevel);

        assertTrue(dataService.fetchGameSaves().removeIf(gs -> gs.getGameLevel().equals(gameLevel)));
    }

    /**
     * Test of saveGame method, of class TextFileDataService.
     */
    @Test
    public void testSaveGame_GameLevel2() throws IOException {
        testSetGameLevel1();
        testSetPlayer();
        gameLevel.movePlayer(Direction.LEFT);
        dataService.saveGame(gameLevel);

        assertTrue(dataService.fetchGameSaves().removeIf(gs -> gs.getGameLevel().equals(gameLevel)));
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

    @Test
    public void testSwapLevel_String() throws IOException {
        testSetLevelsDir();
        dataService.swapLevel(levelName);

        assertThat(dataService.fetchGameLevel(), is(gameLevel));
    }

    @Test
    public void testFetchGameLevel_String() throws IOException {
        testSetLevelsDir();
        GameLevel gl = dataService.fetchGameLevel(levelName);

        assertThat(gl, is(gameLevel));
    }

    @Test
    public void testFetchGameLevel_0args() throws IOException {
        testSetGameLevel1();
        GameLevel gl = dataService.fetchGameLevel();

        assertThat(gl, is(gameLevel));
    }

    @Test
    public void testToString() throws IOException {
        testSetCurrentLevel_String();
        testSaveGame_GameSave();
        String actual = dataService.toString();
        String expected
                = "levelsDir: " + levelsDirName + "\n"
                + "usersDir: " + usersDirName + "\n"
                + "userDir: " + usersDirName + File.separator + user + "\n"
                + "playerDir: " + usersDirName + File.separator + user + File.separator + player + "\n"
                + "currentLevel: " + levelName;

        assertThat(actual, is(expected));
    }

    @Test
    public void simulateDungeonCrawler() throws IOException {
        String usersDir = Files.createTempDirectory("usersTest2").toString();
        Path levelsPath = Files.createTempDirectory("levelsTest2");
        new TextFileLevelDao(levelsPath).saveLevel(gameLevel);
        String levelsDir = levelsPath.toString();

        dataService.setUsersDir(usersDir);
        dataService.setLevelsDir(levelsDir);
        dataService.swapLevel(levelName);

        assertThat(dataService.currentLevel, is(gameLevel));
    }

    /**
     * Test of setCurrentLevel method, of class TextFileDataService.
     */
    @Test
    public void testSetCurrentLevel_String() throws IOException {
        testSetLevelsDir();
        dataService.setCurrentLevel(levelName);
        assertThat(dataService.currentLevel, is(gameLevel));
    }

    /**
     * Test of setCurrentLevel method, of class TextFileDataService.
     */
    @Test
    public void testSetCurrentLevel_GameLevel() {
        dataService.setCurrentLevel(gameLevel);
        assertThat(dataService.currentLevel, is(gameLevel));
    }

    /**
     * Test of swapLevel method, of class TextFileDataService.
     */
    @Test
    public void testSwapLevel() throws IOException {
        testSetGameLevel1();
        dataService.swapLevel(otherLevel.getLevelName());
        assertThat(dataService.currentLevel.getLevelName(), is(otherLevel.getLevelName()));
        PlayerCharacter pc = dataService.currentLevel.getPlayer();
        assertThat(pc.getCoords(), is(new Coords(2, 2, 2)));
        assertThat(pc.getHp(), is(10));
        assertThat(pc.getPoints(), is(0));
    }

    @Test
    public void testFetchGameSaves() throws IOException {
        testSaveGame_GameSave();
        List<GameSave> fetched = dataService.fetchGameSaves();

        assertTrue(fetched.size() == 1);
        assertFalse(fetched.removeIf(i -> i == null));
    }

}
