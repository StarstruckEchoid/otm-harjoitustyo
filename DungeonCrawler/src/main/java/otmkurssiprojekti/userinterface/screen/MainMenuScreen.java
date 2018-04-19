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
public class MainMenuScreen extends VerticalMenuScreen {

    public MainMenuScreen(DungeonCrawler main) {
        super(main);
    }

    @Override
    protected void doEnterAction(int index) {
        switch (index) {
            case 0:
                switchTo(new LoadUserScreen(main));
                break;
            case 1:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    @Override
    protected List<Object> getOptsList() {
        List<Object> ret = new ArrayList<>();
        ret.add("START");
        ret.add("EXIT");
        return ret;
    }

    @Override
    protected String getTitle() {
        return "DUNGEON CRAWLER";
    }

    @Override
    protected GameScreen getReturnScreen() {
        return new MainMenuScreen(main);
    }

}
