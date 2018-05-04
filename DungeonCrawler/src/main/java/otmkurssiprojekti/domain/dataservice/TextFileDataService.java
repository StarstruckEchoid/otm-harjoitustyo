/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.dataservice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import otmkurssiprojekti.dataaccessobject.BasicFileDao;
import otmkurssiprojekti.dataaccessobject.TextFileGameSaveDao;
import otmkurssiprojekti.dataaccessobject.TextFileLevelDao;
import otmkurssiprojekti.dataaccessobject.dataobject.GameSave;
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
    public void setLevelsDir(String levelsDir) {
        Path path = Paths.get(levelsDir);
        createDirectoryIfAbsent(path);
        this.levelsDir = path;
    }

    @Override
    public void setUsersDir(String usersDir) {
        Path path = Paths.get(usersDir);
        createDirectoryIfAbsent(path);
        this.usersDir = path;
    }

    @Override
    public void setUser(String userName) {
        Path path = Paths.get(this.usersDir.toString(), userName);
        createDirectoryIfAbsent(path);
        this.userDir = path;
    }

    @Override
    public void setPlayer(String playerName) {
        Path path = Paths.get(this.userDir.toString(), playerName);
        createDirectoryIfAbsent(path);
        this.playerDir = path;
    }

    @Override
    public void loadLevel(String levelName) {
        this.currentLevel = fetchGameLevel(levelName);
    }

    public void loadSave(String saveName) {
        this.currentLevel = fetchGameSave(saveName).getGameLevel();
    }

    @Override
    public GameLevel fetchGameLevel(String levelName) {
        return new TextFileLevelDao(levelsDir).loadLevel(levelName);
    }

    @Override
    public GameLevel fetchGameLevel() {
        return currentLevel;
    }

    @Override
    public List<GameSave> fetchGameSaves() {
        return new TextFileGameSaveDao(playerDir).listGameSaves();
    }

    @Override
    public GameSave fetchGameSave(String saveName) {
        return new TextFileGameSaveDao(playerDir).loadSave(saveName);
    }

    public void saveGame(GameSave gameSave) {
        new TextFileGameSaveDao(playerDir).saveGame(gameSave);
        this.currentLevel = gameSave.getGameLevel();
    }

    @Override
    public void saveGame(GameLevel gameLevel) {
        GameSave gameSave = new GameSave(new Date(System.currentTimeMillis()), gameLevel);
        saveGame(gameSave);
    }

    public void saveGame() {
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

    private void createDirectoryIfAbsent(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException ex) {
                Logger.getLogger(TextFileDataService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String toString() {
        return "levelsDir: " + safeToString(levelsDir) + "\n"
                + "usersDir: " + safeToString(usersDir) + "\n"
                + "userDir: " + safeToString(userDir) + "\n"
                + "playerDir: " + safeToString(playerDir) + "\n"
                + "currentLevel: " + safeToString(currentLevel);

    }

    private static String safeToString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString();
    }

}
