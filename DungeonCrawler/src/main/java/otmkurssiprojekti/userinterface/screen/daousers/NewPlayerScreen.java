/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen.daousers;

import otmkurssiprojekti.userinterface.screen.ErrorScreen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import otmkurssiprojekti.userinterface.DungeonCrawler;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.userinterface.screen.GameScreen;
import otmkurssiprojekti.userinterface.screen.LevelScreen;
import otmkurssiprojekti.userinterface.screen.VerticalMenuScreen;

/**
 *
 * @author Juho Gröhn
 */
public class NewPlayerScreen extends VerticalMenuScreen {
    
    StringBuilder playerName;
    PlayerCharacterArchetype[] pcaArr = PlayerCharacterArchetype.values();
    PlayerCharacterArchetype pca = pcaArr[0];
    int pcaArrPointer = 0;
    
    public NewPlayerScreen(DungeonCrawler main) {
        super(main);
        playerName = new StringBuilder();
    }
    
    @Override
    public void handleKeyEvent(KeyEvent e) {
        super.handleKeyEvent(e);
        switch (pointer) {
            case 0:
                handleNameEdit(e);
                break;
            case 1:
                handleArchetypeEdit(e);
                break;
            default:
                break;
        }
    }
    
    private void handleNameEdit(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc.equals(KeyCode.BACK_SPACE)) {
            playerName.deleteCharAt(playerName.length() - 1);
        } else if (kc.isLetterKey()) {
            playerName.append(kc.toString());
        }
    }
    
    private void handleArchetypeEdit(KeyEvent e) {
        switch (e.getCode()) {
            case LEFT:
                if (pcaArrPointer > 0) {
                    pcaArrPointer--;
                }
                break;
            case RIGHT:
                if (pcaArrPointer < pcaArr.length - 1) {
                    pcaArrPointer++;
                }
                break;
            default:
                break;
        }
        pca = pcaArr[pcaArrPointer];
    }
    
    private void initialiseNewGame() throws IOException {
        GameLevel startingLevel = main.getDataService().fetchGameLevel();
        main.getDataService().setPlayer(playerName.toString());
        initialisePlayer(startingLevel);
        main.getDataService().saveGame(startingLevel);
        switchTo(new LevelScreen(main));
    }
    
    private void initialisePlayer(GameLevel gameLevel) {
        BasicPlayerCharacter playerCharacter = new BasicPlayerCharacter(
                pcaArr[pcaArrPointer],
                gameLevel.getPlayer().getCoords(),
                Direction.DOWN
        );
        gameLevel.setPlayer(playerCharacter);
    }
    
    @Override
    protected void doEnterAction(int index) {
        try {
            initialiseNewGame();
        } catch (IOException ex) {
            switchTo(new ErrorScreen(main, ex));
        }
    }
    
    @Override
    protected List<Object> getOptsList() {
        List<Object> opts = new ArrayList<>();
        opts.add(playerName);
        opts.add(pca);
        return opts;
    }
    
    @Override
    protected String getTitle() {
        return "Create your character";
    }
    
    @Override
    protected GameScreen getReturnScreen() {
        return new LoadPlayerScreen(main);
    }
    
}
