/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import otmkurssiprojekti.dataaccessobject.ByteFileGameSaveDao;
import otmkurssiprojekti.dataaccessobject.GameSaveDao;
import otmkurssiprojekti.DungeonCrawler;
import otmkurssiprojekti.GameSave;

/**
 *
 * @author Juho Gröhn
 */
public class SaveGameScreen extends VerticalMenuScreen {

    private final GameSaveDao gsdao;
    private final List<GameSave> saves;

    public SaveGameScreen(DungeonCrawler main) {
        super(main);
        gsdao = new ByteFileGameSaveDao(Paths.get(//The address where game saves are looked up is <USER_DIR>/<user>/<player>/
                DungeonCrawler.USER_DIR.toString(),
                main.getGameData().getUser(),
                main.getGameData().getPlayer()
        ));
        saves = gsdao.listGameSaves();
    }

    @Override
    protected void doEnterAction(int index) {
        if (index == 0) {
            gsdao.saveGame(new GameSave(new Date(System.currentTimeMillis()), main.getGameData().getGameLevel()));
            switchTo(new LevelScreen(main));
        }

    }

    @Override
    protected List<String> getOptsList() {
        List<String> ret = new ArrayList<>();
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
