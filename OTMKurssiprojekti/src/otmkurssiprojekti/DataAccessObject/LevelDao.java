/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.DataAccessObject;

import java.nio.file.Path;
import otmkurssiprojekti.Level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public interface LevelDao {
    public GameLevel loadLevel(Path user, String levelName);
    public void saveLevel(Path user, GameLevel level);
}
