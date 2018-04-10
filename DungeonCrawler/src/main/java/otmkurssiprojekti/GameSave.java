/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti;

import java.util.Date;

/**
 *
 * @author gjuho
 */
public class GameSave{

    private final Date saveDate;
    private final GameData gameData;

    public GameSave() {
        this.saveDate = null;
        this.gameData = null;
    }

    public GameSave(Date saveDate, GameData gameData) {
        this.saveDate = saveDate;
        this.gameData = gameData;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public GameData getGameData() {
        return gameData;
    }

    @Override
    public String toString() {
        return saveDate.toString() + ": " + gameData.toString();
    }

}
