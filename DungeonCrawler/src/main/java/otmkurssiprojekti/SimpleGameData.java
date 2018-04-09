/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti;

import otmkurssiprojekti.Level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public class SimpleGameData implements GameData {

    private String user;
    private String player;
    private GameLevel gameLevel;

    public SimpleGameData() {
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
        return player+" in "+gameLevel.getLevelName();
    }
    
    

}
