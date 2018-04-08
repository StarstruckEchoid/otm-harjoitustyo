/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
            case Q:
                otmkurssiprojekti.OTMKurssiprojekti.setGameScreen(new MainMenuScreen());
                break;
            default:
                break;
        }
    }

    @Override
    public Parent getVisualisation() {
        BorderPane main = new BorderPane();

        Text title = new Text("Paused");
        title.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(title, Pos.CENTER);
        main.setTop(title);

        Text opts = new Text(
                "Press ESC to return to game\n"
                + "Press Q to exit to main menu"
        );
        opts.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(opts, Pos.CENTER);
        main.setCenter(opts);

        return main;
    }

    @Override
    public void doGameTick() {
    }

}
