/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen.daousers;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import otmkurssiprojekti.dataaccessobject.FileUserDao;
import otmkurssiprojekti.dataaccessobject.GameLevelDao;
import otmkurssiprojekti.dataaccessobject.GameSaveDao;
import otmkurssiprojekti.dataaccessobject.TextFileGameSaveDao;
import otmkurssiprojekti.dataaccessobject.TextFileLevelDao;
import otmkurssiprojekti.dataaccessobject.UserDao;
import otmkurssiprojekti.userinterface.DungeonCrawler;
import otmkurssiprojekti.dataaccessobject.dataobject.GameSave;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.userinterface.screen.GameScreen;
import otmkurssiprojekti.userinterface.screen.LevelScreen;
import otmkurssiprojekti.userinterface.screen.LoadPlayerScreen;
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

    private void initialiseNewGame() {
        //Load first level.
        GameLevel startingLevel = initialiseLevel();
        //Put player in it.
        initialisePlayer(startingLevel);
        //Set player in main to player name.
        String player = playerName.toString();
        main.getGameData().setPlayer(player);
        //Save player by name to disc.
        savePlayer(player);
        //Save the game to disc.
        saveGame(startingLevel);
        //Set level in main to starting level.
        main.getGameData().setGameLevel(startingLevel);
        //Switch the window to LevelScreen.
        switchTo(new LevelScreen(main));
    }

    private GameLevel initialiseLevel() {
        GameLevelDao gameLevelDao = new TextFileLevelDao(DungeonCrawler.LEVEL_DIR);
        return gameLevelDao.loadLevel(Paths.get(DungeonCrawler.LEVEL_DIR.toString(), DungeonCrawler.FIRST_LEVEL));

    }

    private void initialisePlayer(GameLevel gameLevel) {
        BasicPlayerCharacter playerCharacter = new BasicPlayerCharacter(
                pcaArr[pcaArrPointer],
                gameLevel.getPlayer().getCoords(),
                Direction.DOWN
        );

        gameLevel.setPlayer(playerCharacter);
    }

    private void savePlayer(String playerName) {
        //Save player by name in the folder <USER_DIR>/<user>/
        UserDao playerDao = new FileUserDao(//Using UserDao to save players. A bit hacky, but should get the job done.
                Paths.get(
                        DungeonCrawler.USER_DIR.toString(),
                        main.getGameData().getUser()
                )
        );
        playerDao.saveUser(playerName);
    }

    private void saveGame(GameLevel gameLevel) {
        //Save the game in the folder <USER_DIR>/<user>/<player>/
        GameSaveDao gameSaveDao = new TextFileGameSaveDao(
                Paths.get(
                        DungeonCrawler.USER_DIR.toString(),
                        main.getGameData().getUser(),
                        main.getGameData().getPlayer()
                )
        );
        gameSaveDao.saveGame(new GameSave(new Date(System.currentTimeMillis()), gameLevel));
    }

    @Override
    protected void doEnterAction(int index) {
        initialiseNewGame();
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
