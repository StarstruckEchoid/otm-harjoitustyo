/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen;

import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.DungeonCrawler;

/**
 *
 * @author Juho Gröhn
 */
public class PauseScreen extends VerticalMenuScreen {

    public PauseScreen(DungeonCrawler main) {
        super(main);
    }

    @Override
    protected void doEnterAction(int index) {
        switch (index) {
            case 0:
                switchTo(new LevelScreen(main));
                break;
            case 1:
                switchTo(new SaveGameScreen(main));
                break;
            case 2:
                switchTo(new LoadGameScreen(main));
                break;
            case 3:
                switchTo(new MainMenuScreen(main));
            default:
                break;
        }
    }

    @Override
    protected List<Object> getOptsList() {
        List<Object> ret = new ArrayList<>();
        ret.add("Continue");
        ret.add("Save Game");
        ret.add("Load Game");
        ret.add("Quit Game");
        return ret;
    }

    @Override
    protected String getTitle() {
        return "Paused";
    }

    @Override
    protected GameScreen getReturnScreen() {
        return new LevelScreen(main);
    }

}
