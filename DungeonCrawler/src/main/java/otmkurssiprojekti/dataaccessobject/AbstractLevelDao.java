/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.domain.level.GameLevel;

/**
 *
 * @author gjuho
 */
public abstract class AbstractLevelDao implements GameLevelDao {

    protected final Path directory;

    public AbstractLevelDao(Path directory) {
        this.directory = directory;
    }

    @Override
    public List<GameLevel> listGameLevels() throws IOException {
        File[] gameLevels = directory.toFile().listFiles();
        List<GameLevel> levels = new ArrayList<>();
        for (File f : gameLevels) {
            levels.add(loadLevel(f.getName()));
        }
        return levels;
    }

    @Override
    public void saveLevel(GameLevel level) throws IOException {
        this.saveLevel(level, level.getLevelName());
    }

}
