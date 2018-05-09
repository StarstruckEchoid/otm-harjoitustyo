/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen;

import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import otmkurssiprojekti.userinterface.DungeonCrawler;
import otmkurssiprojekti.userinterface.screen.GameScreen;
import otmkurssiprojekti.userinterface.screen.MainMenuScreen;
import otmkurssiprojekti.userinterface.screen.SwitchingScreen;

/**
 *
 * @author gjuho
 */
public class ErrorScreen extends SwitchingScreen implements GameScreen {

    private Exception ex;

    public ErrorScreen(DungeonCrawler main, Exception ex) {
        super(main);
        this.ex = ex;
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        switchTo(new MainMenuScreen(main));
    }

    @Override
    public Parent getVisualisation() {
        return new BorderPane(
                new Text(
                        "The game encountered an unexpected exception and was forced to close:\n"
                        + ex.getMessage() + "\n"
                        + "Press any key to continue."
                )
        );
    }

    @Override
    public void doGameTick() {
    }

}
