/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject.dataobject;

import java.util.Date;
import java.util.Objects;
import otmkurssiprojekti.domain.level.GameLevel;

/**
 *
 * @author gjuho
 */
public class GameSave {

    private final Date saveDate;
    private final GameLevel gameLevel;

    public GameSave(Date saveDate, GameLevel gameLevel) {
        this.saveDate = saveDate;
        this.gameLevel = gameLevel;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public GameLevel getGameLevel() {
        return gameLevel;
    }

    @Override
    public String toString() {
        return saveDate.toString() + " in " + gameLevel.getLevelName();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.saveDate);
        hash = 47 * hash + Objects.hashCode(this.gameLevel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameSave other = (GameSave) obj;
        if (!Objects.equals(this.saveDate, other.saveDate)) {
            return false;
        }
        if (!Objects.equals(this.gameLevel, other.gameLevel)) {
            return false;
        }
        return true;
    }

}
