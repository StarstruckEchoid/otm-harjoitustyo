/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen.daousers;

import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.userinterface.DungeonCrawler;
import otmkurssiprojekti.userinterface.screen.GameScreen;
import otmkurssiprojekti.userinterface.screen.MainMenuScreen;
import otmkurssiprojekti.userinterface.screen.VerticalMenuScreen;

/**
 *
 * @author Juho Gröhn
 */
public class LoadUserScreen extends VerticalMenuScreen {

    private final List<String> userNames;

//    private int pointer = 0;
    public LoadUserScreen(DungeonCrawler main) {
        super(main);
        userNames = main.getDataService().fetchUsers();
    }

    @Override
    protected void doEnterAction(int index) {
        if (index == 0) {
            switchTo(new NewUserScreen(main));
        } else {
            String userName = userNames.get(index - 1);
            main.getDataService().setUser(userName);
            switchTo(new LoadPlayerScreen(main));
        }
    }

    @Override
    protected List<Object> getOptsList() {
        List<Object> optsList = new ArrayList<>();
        optsList.add("<new user>");
        optsList.addAll(userNames);
        return optsList;
    }

    @Override
    protected String getTitle() {
        return "Select user";
    }

    @Override
    protected GameScreen getReturnScreen() {
        return new MainMenuScreen(main);
    }

}
