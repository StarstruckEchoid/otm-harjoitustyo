/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen;

import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import static javafx.scene.text.Font.font;
import javafx.scene.text.Text;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import static otmkurssiprojekti.domain.gameobject.location.Direction.DOWN;
import static otmkurssiprojekti.domain.gameobject.location.Direction.LEFT;
import static otmkurssiprojekti.domain.gameobject.location.Direction.RIGHT;
import static otmkurssiprojekti.domain.gameobject.location.Direction.UP;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.userinterface.DungeonCrawler;
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
        this.gameLevel = main.getDataService().fetchGameLevel();
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        this.movePlayer(e);
        this.playerAttack(e);
        this.playerInteract(e);
        this.goToPauseMenu(e);
    }

    private void movePlayer(KeyEvent e) {
        switch (e.getCode()) {
            case W:
                gameLevel.movePlayer(UP);
                break;
            case A:
                gameLevel.movePlayer(LEFT);
                break;
            case S:
                gameLevel.movePlayer(DOWN);
                break;
            case D:
                gameLevel.movePlayer(RIGHT);
                break;
            default:
                break;
        }
    }

    private void playerAttack(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                gameLevel.playerAttack(UP);
                break;
            case DOWN:
                gameLevel.playerAttack(DOWN);
                break;
            case LEFT:
                gameLevel.playerAttack(LEFT);
                break;
            case RIGHT:
                gameLevel.playerAttack(RIGHT);
                break;
            default:
                break;
        }
    }

    private void playerInteract(KeyEvent e) {
        switch (e.getCode()) {
            case ENTER:
                gameLevel.playerInteract().ifPresent(addr -> {
                    try {
                        main.getDataService().swapLevel(addr);
                        switchTo(new LevelScreen(main));
                    } catch (IOException ex) {
                        switchTo(new ErrorScreen(main, ex));
                    }
                });
                break;
            default:
                break;
        };
    }

    private void goToPauseMenu(KeyEvent e) {
        switch (e.getCode()) {
            case ESCAPE:
                main.getDataService().setCurrentLevel(this.gameLevel);
                switchTo(new PauseScreen(main));
                break;
            default:
                break;

        }
    }

    @Override
    public Parent getVisualisation() {
        BorderPane hud = new BorderPane();
        //Kentän nimi.
        Text t = new Text(gameLevel.getLevelName());
        t.setFont(font("MONOSPACED"));
        hud.setTop(t);

        //Kentän data.
        Node render = RENDERER.getRender(gameLevel);
        hud.setCenter(render);

        //Pelaajan tiedot.
        Node stats = getPlayerStats();
        hud.setLeft(stats);

        return hud;
    }

    @Override
    public void doGameTick() {
        if (gameLevel.doGameTick()) {
            switchTo(new GameOverScreen(main));
        }
    }

    private Node getPlayerStats() {
        StringBuilder sb = new StringBuilder();
        PlayerCharacter pc = gameLevel.getPlayer();
        sb.append("XP:\t").append(pc.getPoints()).append("\n");
        sb.append("HP:\t").append(pc.getHp()).append("\n");
        sb.append("STR:\t").append(pc.getStr()).append("\n");
        sb.append("PER:\t").append(pc.getPer()).append("\n");
        sb.append("END:\t").append(pc.getEnd()).append("\n");
        sb.append("AGL:\t").append(pc.getAgl()).append("\n");
        return new Text(sb.toString());
    }

}
