/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface;

import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Juho Gröhn
 */
public class PauseScreen implements GameScreen {

    public PauseScreen() {
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        KeyCode kc = e.getCode();
        switch (kc) {
            case ESCAPE:
                otmkurssiprojekti.OTMKurssiprojekti.setGameScreen(new LevelScreen());
                break;
            case A:
                break;
            case S:
                break;
            case X:
                otmkurssiprojekti.OTMKurssiprojekti.setGameScreen(new MainMenuScreen());
                break;
            default:
                break;
        }
    }

    @Override
    public Parent getVisualisation() {
        BorderPane main = new BorderPane();

        Text t = new Text();
        t.setFont(Font.font("MONOSPACED"));
        t.setTextAlignment(TextAlignment.CENTER);
        t.setText("Paused");

        VBox options = new VBox();
        options.getChildren().addAll(
                new Text("Press ESC to return to game."),
                new Text("Press S to save game."),
                new Text("Press A to load game."),
                new Text("Press X to exit to main menu.")
        );

        main.setTop(t);
        main.setCenter(options);

        return main;
    }

    @Override
    public void doGameTick() {
    }

}
