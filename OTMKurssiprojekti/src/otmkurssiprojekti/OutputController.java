/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import otmkurssiprojekti.Level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public class OutputController {

    private Scene scene;
    private GameLevel gamelvl;
    protected GameWindow window;

    public OutputController(Scene scene, GameLevel gamelvl) {
        this.scene = scene;
        this.gamelvl = gamelvl;
        this.window = GameWindow.MAIN_MENU;
    }

    public GameLevel getGamelvl() {
        return gamelvl;
    }

    protected GameWindow getWindow() {
        return window;
    }

    protected void setWindow(GameWindow window) {
        this.window = window;
    }

    public void setDisplayToInGameText() {
        Parent level = this.getInGameText();
        this.scene.setRoot(level);
        this.setWindow(GameWindow.IN_GAME);
    }

    public void setDisplayToMainMenu() {
        Parent mainmenu = this.getMainMenu();
        this.scene.setRoot(mainmenu);
        this.setWindow(GameWindow.MAIN_MENU);
    }

    void setDisplayToPauseMenu() {
        Parent pausemenu = this.getPauseMenu();
        this.scene.setRoot(pausemenu);
        this.setWindow(GameWindow.PAUSE_MENU);
    }

    public Parent getInGameText() {
        BorderPane hud = new BorderPane();
        Text t = new Text();
        t.setFont(Font.font("MONOSPACED"));

        //Kentän data.
        String display
                = FormatConverter.projectionToDenseString(
                        FormatConverter.project(
                                FormatConverter.levelDataToMatrix(
                                        gamelvl.getLevelData()
                                )
                        )
                );

        t.setText(display);

        hud.setCenter(t);

        return hud;
    }

    public Parent getMainMenu() {
        BorderPane main = new BorderPane();

        Text t = new Text();
        t.setFont(Font.font("MONOSPACED"));
        t.setTextAlignment(TextAlignment.CENTER);
        t.setText("Main Menu");

        Text s = new Text();
        s.setFont(Font.font("MONOSPACED"));
        s.setText("Press ENTER to start game");

        main.setTop(t);
        main.setCenter(s);

        return main;
    }

    private Parent getPauseMenu() {
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

}
