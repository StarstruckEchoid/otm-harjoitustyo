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
import otmkurssiprojekti.DungeonCrawler;

/**
 *
 * @author Juho Gröhn
 */
public class PauseScreen implements GameScreen {

    private final otmkurssiprojekti.DungeonCrawler main;

    public PauseScreen(DungeonCrawler main) {
        this.main = main;
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        KeyCode kc = e.getCode();
        switch (kc) {
            case ESCAPE:
                main.setGameScreen(new LevelScreen(main));
                break;
            case A:
                break;
            case S:
                break;
            case Q:
                main.setGameScreen(new MainMenuScreen(main));
                break;
            default:
                break;
        }
    }

    @Override
    public Parent getVisualisation() {
        BorderPane visual = new BorderPane();

        Text title = new Text("Paused");
        title.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(title, Pos.CENTER);
        visual.setTop(title);

        Text opts = new Text(
                "Press ESC to return to game\n"
                + "Press Q to exit to main menu"
        );
        opts.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(opts, Pos.CENTER);
        visual.setCenter(opts);

        return visual;
    }

    @Override
    public void doGameTick() {
    }

}
