/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.DungeonCrawler;

/**
 *
 * @author Juho Gröhn
 */
public class LoadPlayerScreen extends VerticalMenuScreen {

    private final Path user;
    private final File[] players;

    public LoadPlayerScreen(DungeonCrawler main) {
        super(main);
        this.user = Paths.get(
                DungeonCrawler.USER_DIR.toString(),
                main.getGameData().getUser()
        );
        this.players = user.toFile().listFiles();
    }

    @Override
    protected void doEnterAction(int index) {
        if (index == 0) {
            switchTo(new NewPlayerScreen(main));
        } else {
            File player = players[index - 1];
            main.getGameData().setPlayer(player.getName());
            switchTo(new LoadGameScreen(main));
        }
    }

    @Override
    protected List<String> getOptsList() {
        List<String> optsList = new ArrayList<>();
        optsList.add("<new player>");
        for (File player : players) {
            optsList.add(player.getName());
        }
        return optsList;
    }

    @Override
    protected String getTitleText() {
        return "Select player";
    }

    @Override
    protected GameScreen getReturnScreen() {
        return new LoadUserScreen(main);
    }

}
