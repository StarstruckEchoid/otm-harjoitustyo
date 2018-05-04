/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen.daousers;

import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.userinterface.DungeonCrawler;
import otmkurssiprojekti.dataaccessobject.dataobject.GameSave;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.userinterface.screen.GameScreen;
import otmkurssiprojekti.userinterface.screen.LevelScreen;
import otmkurssiprojekti.userinterface.screen.PauseScreen;
import otmkurssiprojekti.userinterface.screen.VerticalMenuScreen;

/**
 *
 * @author Juho Gröhn
 */
public class SaveGameScreen extends VerticalMenuScreen {

    private final List<GameSave> saves;

    public SaveGameScreen(DungeonCrawler main) {
        super(main);
        saves = main.getDataService().fetchGameSaves();
    }

    @Override
    protected void doEnterAction(int index) {
        if (index == 0) {
            GameLevel gameLevel = main.getDataService().fetchGameLevel();
            main.getDataService().saveGame(gameLevel);
            switchTo(new LevelScreen(main));
        }

    }

    @Override
    protected List<Object> getOptsList() {
        List<Object> ret = new ArrayList<>();
        ret.add("<new save>");
        saves.forEach((save) -> {
            ret.add(save.toString());
        });
        return ret;
    }

    @Override
    protected String getTitle() {
        return "Save game";
    }

    @Override
    protected GameScreen getReturnScreen() {
        return new PauseScreen(main);
    }

}
