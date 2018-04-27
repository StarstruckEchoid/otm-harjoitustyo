/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.File;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.dataaccessobject.dataobject.GameSave;
import otmkurssiprojekti.domain.level.GameLevel;

/**
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
    public List<GameSave> listGameSaves() {
        File[] gameSaves = directory.toFile().listFiles();
        List<GameSave> saves = new ArrayList<>();
        for (File f : gameSaves) {
            saves.add(loadSave(f));
        }
        return saves;
    }

    @Override
    public void saveGame(GameSave gameSave) { //A gamesave is written into a file whose name is the date of the save represented as a long. The contents of the file are the gamelevel.
        String name = Long.toString(gameSave.getSaveDate().getTime());
        GameLevel gameLevel = gameSave.getGameLevel();
        levelDao.saveLevel(gameLevel, name);
    }

    public GameSave loadSave(File file) { //A gamesave is constructed so that the date is the name of the file and the gamelevel is the contents.
        String name = file.getName();
        long time = Long.parseLong(name);
        Date date = new Date(time);

        GameLevel gameLevel = levelDao.loadLevel(file.toPath());

        return new GameSave(date, gameLevel);
    }

}
