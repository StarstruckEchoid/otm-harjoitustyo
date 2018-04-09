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
public interface GameData {

    //Getters
    public String getUser();

    public String getPlayer();

    public GameLevel getGameLevel();

    //Setters
    public void setUser(String user);

    public void setPlayer(String player);

    public void setGameLevel(GameLevel gameLevel);

}
