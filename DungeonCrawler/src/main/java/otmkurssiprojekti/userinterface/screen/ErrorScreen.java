/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen;

import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import static javafx.scene.text.Font.font;
import javafx.scene.text.Text;
import otmkurssiprojekti.userinterface.DungeonCrawler;

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
        Text errorMessage = new Text(
                "The game encountered an unexpected exception and was forced to close:\n"
                + ex.toString() + "\n"
                + "Press any key to continue."
        );
        errorMessage.setFont(font("MONOSPACED"));
        return new BorderPane(errorMessage);
    }

    @Override
    public void doGameTick() {
    }

}
