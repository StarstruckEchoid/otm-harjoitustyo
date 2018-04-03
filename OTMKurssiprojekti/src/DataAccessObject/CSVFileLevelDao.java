/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import otmkurssiprojekti.Level.GameLevel;
import otmkurssiprojekti.UtilityClasses.CSVConverter;

/**
 *
 * @author Juho Gröhn
 */
public class CSVFileLevelDao implements LevelDao {

    @Override
    public GameLevel loadLevel(Path user, String levelName) {

        File levelFile = new File(user.toFile(), levelName);
        String levelData = CSVFileLevelDao.extractLines(levelFile);

        GameLevel glvl = CSVConverter.CSVToGameLevel(levelData);
        return glvl;
    }

    @Override
    public void saveLevel(GameLevel level) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static String extractLines(File file) {
        try {
            StringBuilder levelData = new StringBuilder();
            Files.lines(file.toPath())
                    .forEach(l -> levelData.append(l).append("\n"));
            return levelData.toString();
        } catch (IOException ex) {
            return "";
        }
    }

}
