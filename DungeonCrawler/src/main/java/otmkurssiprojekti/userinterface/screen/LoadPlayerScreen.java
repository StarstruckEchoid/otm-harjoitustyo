/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen;

import otmkurssiprojekti.userinterface.screen.daousers.NewPlayerScreen;
import otmkurssiprojekti.userinterface.screen.daousers.LoadUserScreen;
import otmkurssiprojekti.userinterface.screen.daousers.LoadGameScreen;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.userinterface.DungeonCrawler;

/**
 *
 * @author Juho Gröhn
 */
public class LoadPlayerScreen extends VerticalMenuScreen {

    private final List<String> players;

    public LoadPlayerScreen(DungeonCrawler main) {
        super(main);

        this.players = main.getDataService().fetchPlayers();
    }

    @Override
    protected void doEnterAction(int index) {
        if (index == 0) {
            switchTo(new NewPlayerScreen(main));
        } else {
            String player = players.get(index - 1);
            main.getDataService().setPlayer(player);
            switchTo(new LoadGameScreen(main));
        }
    }

    @Override
    protected List<Object> getOptsList() {
        List<Object> optsList = new ArrayList<>();
        optsList.add("<new player>");
        optsList.addAll(players);
        return optsList;
    }

    @Override
    protected String getTitle() {
        return "Select player";
    }

    @Override
    protected GameScreen getReturnScreen() {
        return new LoadUserScreen(main);
    }

}
