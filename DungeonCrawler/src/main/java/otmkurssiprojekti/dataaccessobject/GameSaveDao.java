/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.IOException;
import java.util.List;
import otmkurssiprojekti.dataaccessobject.dataobject.GameSave;

/**
 * A data access object interface for saving and loading GameSaves.
 *
 * @author gjuho
 * @see GameSave
 */
public interface GameSaveDao {

    public List<GameSave> listGameSaves() throws IOException;

    public void saveGame(GameSave gameSave) throws IOException;

    public GameSave loadSave(String name) throws IOException;
}
