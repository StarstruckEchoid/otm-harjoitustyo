/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import otmkurssiprojekti.DataAccessObject.ByteFileGameSaveDao;
import otmkurssiprojekti.DataAccessObject.GameSaveDao;
import otmkurssiprojekti.DungeonCrawler;
import otmkurssiprojekti.GameSave;

/**
 *
 * @author Juho Gröhn
 */
public class LoadGameScreen extends VerticalMenuScreen {
    
    private final GameSaveDao gsdao;
    private final List<GameSave> saves;
    
    public LoadGameScreen(DungeonCrawler main) {
        super(main);
        gsdao = new ByteFileGameSaveDao(Paths.get( //Osoite, josta pelitallennukset katsotaan, on <USER_DIR>/<user>/<player>/
                DungeonCrawler.USER_DIR.toString(),
                main.getGameData().getUser(),
                main.getGameData().getPlayer()
        ));
        saves = gsdao.listGameSaves();
    }
    
    @Override
    protected void doEnterAction(int index) {
        main.getGameData().setGameLevel(saves.get(index).getGameLevel());
        switchTo(new LevelScreen(main));
    }
    
    @Override
    protected List<String> getOptsList() {
        List<String> ret = new ArrayList<>();
        for (GameSave save : saves) {
            ret.add(save.toString());
        }
        return ret;
    }
    
    @Override
    protected String getTitleText() {
        return "Load game";
    }
    
    @Override
    protected GameScreen getReturnScreen() {
        return new LoadPlayerScreen(main);
    }
    
}
