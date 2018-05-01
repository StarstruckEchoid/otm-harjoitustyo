/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen.daousers;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import otmkurssiprojekti.userinterface.DungeonCrawler;
import otmkurssiprojekti.userinterface.screen.GameScreen;
import otmkurssiprojekti.userinterface.screen.LoadPlayerScreen;
import otmkurssiprojekti.userinterface.screen.MainMenuScreen;
import otmkurssiprojekti.userinterface.screen.VerticalMenuScreen;

/**
 *
 * @author Juho Gröhn
 */
public class NewUserScreen extends VerticalMenuScreen {
    private final StringBuilder username;

    public NewUserScreen(DungeonCrawler main) {
        super(main);
        this.username = new StringBuilder();
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        super.handleKeyEvent(e);
        KeyCode kc = e.getCode();
        switch (kc) {
            case BACK_SPACE:
                username.deleteCharAt(username.length() - 1);
                break;
            default:
                if (kc.isLetterKey()) {
                    username.append(kc.toString());
                }
                break;
        }
    }

    @Override
    protected void doEnterAction(int index) {
        //Save user and continue to player selection.
        main.getDataService().setUser(username.toString());
        switchTo(new LoadPlayerScreen(main));
    }

    @Override
    protected List<Object> getOptsList() {
        List<Object> ret = new ArrayList<>();
        ret.add(username);
        return ret;
    }

    @Override
    protected String getTitle() {
        return "Create new user";
    }

    @Override
    protected GameScreen getReturnScreen() {
        return new MainMenuScreen(main);
    }

}
