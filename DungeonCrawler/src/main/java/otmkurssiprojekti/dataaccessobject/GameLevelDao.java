/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.util.List;
import otmkurssiprojekti.domain.level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public interface GameLevelDao {

    public List<GameLevel> listGameLevels();

    /**
     * Saves a GameLevel by its default name.
     *
     * @param level The GameLevel to be saved.
     */
    public void saveLevel(GameLevel level);

    /**
     * Saves a GameLevel by a specific name.
     *
     * @param level The level to be saved.
     * @param name The name by which it is saved.
     */
    public void saveLevel(GameLevel level, String name);

    /**
     *
     * @param levelName The name of the level to be loaded, as determined by its
     * file name.
     * @return Returns the GameLevel stored in the file by this name.
     */
    public GameLevel loadLevel(String levelName);
}
