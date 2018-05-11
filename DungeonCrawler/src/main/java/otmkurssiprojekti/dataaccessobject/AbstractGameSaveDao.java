/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.File;
import java.io.IOException;
import static java.lang.Long.parseLong;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.dataaccessobject.dataobject.GameSave;
import otmkurssiprojekti.domain.level.GameLevel;

/**
 * Defines the methods of GameSaveDao that all file-and-folder based
 * GameSaveDaos use.
 *
 * @author Juho Gröhn
 */
public abstract class AbstractGameSaveDao implements GameSaveDao {

    private final Path directory;
    private final GameLevelDao levelDao;

    public AbstractGameSaveDao(Path directory, GameLevelDao levelDao) {
        this.directory = directory;
        this.levelDao = levelDao;
    }

    @Override
    public List<GameSave> listGameSaves() throws IOException {
        File[] gameSaves = directory.toFile().listFiles();
        List<GameSave> saves = new ArrayList<>();
        for (File f : gameSaves) {
            saves.add(loadSave(f.getName()));
        }
        return saves;
    }

    @Override
    public void saveGame(GameSave gameSave) throws IOException { //A gamesave is written into a file whose name is the date of the save represented as a long. The contents of the file are the gamelevel.
        String name = Long.toString(gameSave.getSaveDate().getTime());
        GameLevel gameLevel = gameSave.getGameLevel();
        levelDao.saveLevel(gameLevel, name);
    }

    @Override
    public GameSave loadSave(String name) throws IOException { //A gamesave is constructed so that the date is the name of the file and the gamelevel is the contents.
        long time = parseLong(name);
        Date date = new Date(time);
        GameLevel gameLevel = levelDao.loadLevel(name);
        return new GameSave(date, gameLevel);
    }

}
