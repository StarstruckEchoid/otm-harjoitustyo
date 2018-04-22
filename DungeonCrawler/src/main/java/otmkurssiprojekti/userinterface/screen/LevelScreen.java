/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import otmkurssiprojekti.DungeonCrawler;
import otmkurssiprojekti.level.GameLevel;
import otmkurssiprojekti.level.gameobjects.location.Direction;
import otmkurssiprojekti.userinterface.renderer.Renderer;
import otmkurssiprojekti.userinterface.renderer.TextRenderer;

/**
 *
 * @author Juho Gröhn
 */
public class LevelScreen extends SwitchingScreen {

    private static final Renderer RENDERER = new TextRenderer();
    private final GameLevel gameLevel;

    public LevelScreen(DungeonCrawler main) {
        super(main);
        this.gameLevel = main.getGameData().getGameLevel();
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        this.movePlayer(e);
        this.playerAttack(e);
        switch (e.getCode()) {
            case ESCAPE:
                switchTo(new PauseScreen(main));
                break;
            default:
                break;

        }
    }

    private void movePlayer(KeyEvent e) {
        switch (e.getCode()) {
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

    private void playerAttack(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                gameLevel.playerAttack(Direction.UP);
                break;
            case DOWN:
                gameLevel.playerAttack(Direction.DOWN);
                break;
            case LEFT:
                gameLevel.playerAttack(Direction.LEFT);
                break;
            case RIGHT:
                gameLevel.playerAttack(Direction.RIGHT);
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
        Node render = RENDERER.getRender(gameLevel);
        hud.setCenter(render);

        return hud;
    }

    @Override
    public void doGameTick() {
        if (gameLevel.doGameTick()) {
            switchTo(new GameOverScreen(main));
        }
    }

}
