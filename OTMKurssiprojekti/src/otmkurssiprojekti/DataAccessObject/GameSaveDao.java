/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.DataAccessObject;

import java.util.List;
import otmkurssiprojekti.GameSave;

/**
 *
 * @author gjuho
 */
public interface GameSaveDao {
    public List<GameSave> listGameSaves();
    public void saveGame(GameSave gameSave);
}
