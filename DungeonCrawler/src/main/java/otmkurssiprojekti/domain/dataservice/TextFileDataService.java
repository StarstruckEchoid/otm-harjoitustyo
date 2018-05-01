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
    
    private Path usersDir;
    private Path levelsDir;
    private Path userDir;
    private Path playerDir;
    private String gameSaveName;
    private GameLevel gameLevel;
    
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
    public void setGameSave(String saveName) {
        this.gameSaveName = saveName;
    }
    
    @Override
    public void setGameLevel(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }
    
    @Override
    public GameLevel fetchGameLevel(String levelName) {
        return new TextFileLevelDao(levelsDir).loadLevel(levelName);
    }
    
    @Override
    public List<GameSave> fetchGameSaves() {
        return new TextFileGameSaveDao(playerDir).listGameSaves();
    }
    
    @Override
    public GameSave fetchGameSave(String saveName) {
        return new TextFileGameSaveDao(playerDir).loadSave(saveName);
    }
    
    @Override
    public GameSave fetchGameSave() {
        return fetchGameSave(gameSaveName);
    }
    
    public void saveGame(GameSave gameSave) {
        new TextFileGameSaveDao(playerDir).saveGame(gameSave);
        this.setGameSave(Long.toString(gameSave.getSaveDate().getTime()));
    }
    
    @Override
    public void saveGame() {
        GameSave gameSave = new GameSave(new Date(System.currentTimeMillis()), gameLevel);
        saveGame(gameSave);
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
    
}
