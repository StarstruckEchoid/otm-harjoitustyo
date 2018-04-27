/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import otmkurssiprojekti.domain.level.BasicGameLevel;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.utilityclasses.TextFileGameLevels;

/**
 *
 * @author gjuho
 */
public class TextFileLevelDao extends AbstractLevelDao implements GameLevelDao {

    public TextFileLevelDao(Path directory) {
        super(directory);
    }

    @Override
    public void saveLevel(GameLevel level) {
        try {
            Path levelPath = Paths.get(directory.toString(), level.getLevelName());
            if (level instanceof BasicGameLevel) {
                String textData = TextFileGameLevels.printGameLevel((BasicGameLevel) level);
                Files.write(levelPath, textData.getBytes());
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IOException ex) {
            Logger.getLogger(ByteFileLevelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GameLevel loadLevel(Path levelPath) {
        try {
            String lines = Files.readAllLines(levelPath)
                    .stream()
                    .reduce("", (a, b) -> a + b + "\n");
            GameLevel gameLevel = TextFileGameLevels.makeGameLevel(lines);
            return gameLevel;
        } catch (IOException ex) {
            Logger.getLogger(TextFileLevelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
