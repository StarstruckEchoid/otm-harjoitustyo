/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.DataAccessObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import otmkurssiprojekti.GameSave;
import otmkurssiprojekti.Level.GameLevel;
import otmkurssiprojekti.UtilityClasses.Serializer;

public class ByteFileGameSaveDao implements GameSaveDao {

    private final Path directory;
    private final ByteFileLevelDao bfldao;

    public ByteFileGameSaveDao(Path directory) {
        this.directory = directory;
        bfldao = new ByteFileLevelDao(directory);
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
        bfldao.saveLevel(gameSave.getGameLevel(), name);
    }

    public GameSave loadSave(File file) { //A gamesave is constructed so that the date is the name of the file and the gamelevel is the contents.
        String name = file.getName();
        long time = Long.parseLong(name);
        Date date = new Date(time);
        
        GameLevel gameLevel = bfldao.loadLevel(file.toPath());
        
        return new GameSave(date, gameLevel);
    }

}
