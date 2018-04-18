/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen;

import otmkurssiprojekti.DungeonCrawler;

/**
 *
 * @author Juho Gröhn
 */
public abstract class SwitchingScreen implements GameScreen {
    
    protected final DungeonCrawler main;
    
    public SwitchingScreen(DungeonCrawler main) {
        this.main = main;
    }
    
    public void switchTo(GameScreen screen) {
        main.setGameScreen(screen);
    }
}
