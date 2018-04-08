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
import otmkurssiprojekti.OTMKurssiprojekti;

/**
 *
 * @author Juho Gröhn
 */
public class MainMenuScreen implements GameScreen {

    private final otmkurssiprojekti.OTMKurssiprojekti main;

    public MainMenuScreen(OTMKurssiprojekti main) {
        this.main = main;
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        KeyCode kc = e.getCode();
        switch (kc) {
            case ENTER:
                main.setGameScreen(new LoadUserScreen(main));
                break;
            case Q:
                System.exit(0);
                break;
            default:
                break;
        }
    }

    @Override
    public Parent getVisualisation() {
        BorderPane visual = new BorderPane();

        Text title = new Text("Main Menu");
        title.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(title, Pos.CENTER);
        visual.setTop(title);

        Text opts = new Text(
                "Press ENTER to start game\n"
                + "Press Q to quit game");
        opts.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(opts, Pos.CENTER);
        visual.setCenter(opts);

        return visual;
    }

    @Override
    public void doGameTick() {
    }

}
