/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.IOException;
import java.util.List;
import otmkurssiprojekti.domain.level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public interface GameLevelDao {

    public List<GameLevel> listGameLevels() throws IOException;

    /**
     * Saves a GameLevel by its default name.
     *
     * @param level The GameLevel to be saved.
     * @throws java.io.IOException If level could not be saved, throw this. This
     * may happen if, for example, the save directory is null.
     */
    public void saveLevel(GameLevel level) throws IOException;

    /**
     * Saves a GameLevel by a specific name.
     *
     * @param level The level to be saved.
     * @param name The name by which it is saved.
     * @throws java.io.IOException If level could not be saved, throw this. This
     * may happen if, for example, the save directory is null.
     */
    public void saveLevel(GameLevel level, String name) throws IOException;

    /**
     *
     * @param levelName The name of the level to be loaded, as determined by its
     * file name.
     * @return Returns the GameLevel stored in the file by this name.
     * @throws java.io.IOException If level could not be saved, throw this. This
     * may happen if, for example, the load directory is null.
     */
    public GameLevel loadLevel(String levelName) throws IOException;
}
