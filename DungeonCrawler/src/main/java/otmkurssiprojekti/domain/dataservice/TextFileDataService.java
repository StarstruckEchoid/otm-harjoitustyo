/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.dataservice;

import java.io.IOException;
import static java.lang.System.currentTimeMillis;
import static java.nio.file.Files.createDirectory;
import static java.nio.file.Files.exists;
import java.nio.file.Path;
import static java.nio.file.Paths.get;
import java.util.Date;
import java.util.List;
import otmkurssiprojekti.dataaccessobject.BasicFileDao;
import otmkurssiprojekti.dataaccessobject.GameLevelDao;
import otmkurssiprojekti.dataaccessobject.GameSaveDao;
import otmkurssiprojekti.dataaccessobject.TextFileGameSaveDao;
import otmkurssiprojekti.dataaccessobject.TextFileLevelDao;
import otmkurssiprojekti.dataaccessobject.dataobject.GameSave;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import otmkurssiprojekti.domain.level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public class TextFileDataService implements DataService {

    protected Path usersDir;
    protected Path levelsDir;
    protected Path userDir;
    protected Path playerDir;
    protected GameLevel currentLevel;

    public TextFileDataService() {
    }

    @Override
    public void setLevelsDir(String levelsDir) throws IOException, NullPointerException {
        Path path = get(levelsDir);
        createDirectoryIfAbsent(path);
        this.levelsDir = path;
    }

    @Override
    public void setUsersDir(String usersDir) throws IOException, NullPointerException {
        Path path = get(usersDir);
        createDirectoryIfAbsent(path);
        this.usersDir = path;
    }

    @Override
    public void setUser(String userName) throws IOException, NullPointerException {
        Path path = get(this.usersDir.toString(), userName);
        createDirectoryIfAbsent(path);
        this.userDir = path;
    }

    @Override
    public void setPlayer(String playerName) throws IOException, NullPointerException {
        Path path = get(this.userDir.toString(), playerName);
        createDirectoryIfAbsent(path);
        this.playerDir = path;
    }

    @Override
    public void setCurrentLevel(String levelName) throws IOException {
        this.currentLevel = this.makeLevelDao().loadLevel(levelName);
    }

    @Override
    public void setCurrentLevel(GameLevel gameLevel) {
        this.currentLevel = gameLevel;
    }

    @Override
    public void swapLevel(String levelName) throws IOException {
        GameLevel newLevel = this.makeLevelDao().loadLevel(levelName);
        PlayerCharacter currentPlayer;
        if (this.currentLevel != null) {
            currentPlayer = this.currentLevel.getPlayer();
        } else {
            currentPlayer = newLevel.getPlayer();
        }
        currentPlayer.move(newLevel.getPlayer().getCoords());
        newLevel.setPlayer(currentPlayer);
        this.currentLevel = newLevel;
    }

    protected GameLevel fetchGameLevel(String levelName) throws IOException {
        return this.makeLevelDao().loadLevel(levelName);
    }

    @Override
    public GameLevel fetchGameLevel() {
        return currentLevel;
    }

    @Override
    public List<GameSave> fetchGameSaves() throws IOException {
        return this.makeSaveDao().listGameSaves();
    }

    protected void saveGame(GameSave gameSave) throws IOException {
        this.makeSaveDao().saveGame(gameSave);
    }

    @Override
    public void saveGame(GameLevel gameLevel) throws IOException {
        GameSave gameSave = new GameSave(new Date(currentTimeMillis()), gameLevel);
        saveGame(gameSave);
    }

    private void saveGame() throws IOException {
        saveGame(currentLevel);
    }

    @Override
    public List<String> fetchPlayers() {
        return new BasicFileDao(userDir).loadFiles();
    }

    @Override
    public List<String> fetchUsers() {
        return new BasicFileDao(usersDir).loadFiles();
    }

    private void createDirectoryIfAbsent(Path path) throws IOException {
        if (!exists(path)) {
            createDirectory(path);

        }
    }

    @Override
    public String toString() {
        return "levelsDir: " + safeToString(levelsDir) + "\n"
                + "usersDir: " + safeToString(usersDir) + "\n"
                + "userDir: " + safeToString(userDir) + "\n"
                + "playerDir: " + safeToString(playerDir) + "\n"
                + "currentLevel: " + (currentLevel == null ? "null" : currentLevel.getLevelName());

    }

    private static String safeToString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString();
    }

    private GameLevelDao makeLevelDao() {
        return new TextFileLevelDao(levelsDir);
    }

    private GameSaveDao makeSaveDao() {
        return new TextFileGameSaveDao(playerDir);
    }

}
