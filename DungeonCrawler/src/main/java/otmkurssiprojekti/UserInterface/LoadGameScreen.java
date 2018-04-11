/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface;

import java.nio.file.Paths;
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
public class LoadGameScreen extends SwitchingScreen {

    private final GameSaveDao gsdao;
    private final List<GameSave> saves;

    public LoadGameScreen(DungeonCrawler main) {
        super(main);
        gsdao = new ByteFileGameSaveDao(Paths.get(
                DungeonCrawler.USER_DIR.toString(),
                main.getGameData().getUser(),
                main.getGameData().getPlayer()
        ));
        saves = gsdao.listGameSaves();
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {

    }

    @Override
    public Parent getVisualisation() {
        return new BorderPane(new Text("WIP"));
    }

    @Override
    public void doGameTick() {

    }

}
