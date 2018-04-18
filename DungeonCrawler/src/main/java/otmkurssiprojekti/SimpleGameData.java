/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti;

import java.util.Objects;
import otmkurssiprojekti.level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public class SimpleGameData implements GameData {

    private String user;
    private String player;
    private GameLevel gameLevel;

    public SimpleGameData() {
        this.user = null;
        this.player = null;
        this.gameLevel = null;
    }

    public SimpleGameData(String user, String player, GameLevel gameLevel) {
        this.user = user;
        this.player = player;
        this.gameLevel = gameLevel;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public void setGameLevel(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public GameLevel getGameLevel() {
        return gameLevel;
    }

    @Override
    public String toString() {
        return player + " in " + gameLevel.getLevelName();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.user);
        hash = 71 * hash + Objects.hashCode(this.player);
        hash = 71 * hash + Objects.hashCode(this.gameLevel);
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
        final SimpleGameData other = (SimpleGameData) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.player, other.player)) {
            return false;
        }
        if (!Objects.equals(this.gameLevel, other.gameLevel)) {
            return false;
        }
        return true;
    }
    
    

}
