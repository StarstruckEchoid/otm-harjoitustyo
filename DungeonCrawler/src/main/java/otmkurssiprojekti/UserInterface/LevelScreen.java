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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import otmkurssiprojekti.DungeonCrawler;
import otmkurssiprojekti.Level.GameLevel;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.UtilityClasses.FormatConverter;

/**
 *
 * @author Juho Gröhn
 */
public class LevelScreen extends SwitchingScreen {

    private final GameLevel gameLevel;

    public LevelScreen(DungeonCrawler main) {
        super(main);
        this.gameLevel = main.getGameData().getGameLevel();
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        KeyCode kc = e.getCode();
        switch (kc) {

            case W:
            case A:
            case S:
            case D:
                this.movePlayer(e);
                break;
            case ESCAPE:
                switchTo(new PauseScreen(main));
                break;
            default:
                break;

        }
    }

    private void movePlayer(KeyEvent e) {
        KeyCode kc = e.getCode();
        switch (kc) {
            case W:
                gameLevel.movePlayer(Direction.UP);
                break;
            case A:
                gameLevel.movePlayer(Direction.LEFT);
                break;
            case S:
                gameLevel.movePlayer(Direction.DOWN);
                break;
            case D:
                gameLevel.movePlayer(Direction.RIGHT);
                break;
            default:
                break;
        }
    }

    @Override
    public Parent getVisualisation() {
        BorderPane hud = new BorderPane();
        Text t = new Text();
        t.setFont(Font.font("MONOSPACED"));

        //Kentän data.
        String display
                = FormatConverter.projectionToDenseString(
                        FormatConverter.project(
                                FormatConverter.levelDataToMatrix(
                                        gameLevel.getLevelData()
                                )
                        )
                );

        t.setText(display);

        hud.setCenter(t);

        return hud;
    }

    @Override
    public void doGameTick() {
    }

}
