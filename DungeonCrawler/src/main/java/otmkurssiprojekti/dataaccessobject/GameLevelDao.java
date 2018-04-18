/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.nio.file.Path;
import java.util.List;
import otmkurssiprojekti.level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public interface GameLevelDao {

    public List<GameLevel> listGameLevels();

    public void saveLevel(GameLevel level);

    public GameLevel loadLevel(Path levelPath);
}
