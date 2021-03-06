/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen.daousers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.dataaccessobject.dataobject.GameSave;
import otmkurssiprojekti.userinterface.DungeonCrawler;
import otmkurssiprojekti.userinterface.screen.GameScreen;
import otmkurssiprojekti.userinterface.screen.LevelScreen;
import otmkurssiprojekti.userinterface.screen.VerticalMenuScreen;

/**
 *
 * @author Juho Gröhn
 */
public class LoadGameScreen extends VerticalMenuScreen {

    private final List<GameSave> saves;

    public LoadGameScreen(DungeonCrawler main) throws IOException {
        super(main);
        saves = main.getDataService().fetchGameSaves();
    }

    @Override
    protected void doEnterAction(int index) {
        GameSave gameSave = saves.get(index);
        main.getDataService().setCurrentLevel(gameSave.getGameLevel());
        switchTo(new LevelScreen(main));
    }

    @Override
    protected List<Object> getOptsList() {
        List<Object> ret = new ArrayList<>();
        ret.addAll(saves);
        return ret;
    }

    @Override

    protected String getTitle() {
        return "Load game";
    }

    @Override
    protected GameScreen getReturnScreen() throws IOException {
        return new LoadPlayerScreen(main);
    }

}
