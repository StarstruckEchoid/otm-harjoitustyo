/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface;

import java.nio.file.Paths;
import java.util.Date;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import otmkurssiprojekti.DataAccessObject.ByteFileGameSaveDao;
import otmkurssiprojekti.DataAccessObject.ByteFileLevelDao;
import otmkurssiprojekti.DataAccessObject.FileUserDao;
import otmkurssiprojekti.DataAccessObject.GameLevelDao;
import otmkurssiprojekti.DataAccessObject.GameSaveDao;
import otmkurssiprojekti.DataAccessObject.UserDao;
import otmkurssiprojekti.DungeonCrawler;
import otmkurssiprojekti.GameSave;
import otmkurssiprojekti.Level.GameLevel;
import otmkurssiprojekti.Level.GameObjects.Archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.Level.GameObjects.PlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
public class NewPlayerScreen extends SwitchingScreen {

    int pointer = 0;
    StringBuilder playerName;
    PlayerCharacterArchetype[] pcaArr = PlayerCharacterArchetype.values();
    int pcaArrPointer = 0;

    public NewPlayerScreen(DungeonCrawler main) {
        super(main);
        playerName = new StringBuilder();
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        switch (e.getCode()) {
            case W:
                if (pointer > 0) {
                    pointer--;
                }
                break;
            case S:
                if (pointer < 1) {
                    pointer++;
                }
                break;
            case ENTER:
                initialiseNewGame();
                break;
            case ESCAPE:
                switchTo(new LoadPlayerScreen(main));
            default:
                break;

        }

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

    @Override
    public Parent getVisualisation() {
        BorderPane visual = new BorderPane();

        //TITLE
        Text title = new Text("Create your character");
        title.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(title, Pos.CENTER);
        visual.setTop(title);

        //OPTIONS
        Text opts = new Text();

        StringBuilder sb = new StringBuilder();
        if (pointer == 0) {
            sb.append(">");
        }
        sb.append(playerName.toString()).append("\n");
        if (pointer == 1) {
            sb.append(">");
        }
        sb.append(pcaArr[pcaArrPointer].getName()).append("\n");

        opts.setText(sb.toString());
        opts.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(opts, Pos.CENTER);
        visual.setCenter(opts);

        //LEGEND
        Text legend = new Text(
                "W: up\t"
                + "A:left\t"
                + "S:down\t"
                + "D:right\t"
                + "ESC:back\t"
                + "ENTER:select"
        );
        legend.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(legend, Pos.CENTER);
        visual.setBottom(legend);

        return visual;
    }

    @Override
    public void doGameTick() {

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
            case A:
                if (pcaArrPointer > 0) {
                    pcaArrPointer--;
                }
                break;
            case D:
                if (pcaArrPointer < pcaArr.length - 1) {
                    pcaArrPointer++;
                }
                break;
            default:
                break;
        }
    }

    private void initialiseNewGame() {
        //Create player character.
        PlayerCharacter playerCharacter = new PlayerCharacter(pcaArr[pcaArrPointer], new Coords(0, 0, 0), Direction.DOWN);
        //Load first level.
        GameLevelDao gameLevelDao = new ByteFileLevelDao(DungeonCrawler.LEVEL_DIR);
        GameLevel startingLevel = gameLevelDao.loadLevel(Paths.get(DungeonCrawler.LEVEL_DIR.toString(), DungeonCrawler.FIRST_LEVEL));
        //Put player in it.
        startingLevel.setPlayer(playerCharacter);

        //Set player in main to player name.
        String player = playerName.toString();
        main.getGameData().setPlayer(player);

        //Save player by name in the folder <USER_DIR>/<user>/
        UserDao playerDao = new FileUserDao( //Using UserDao to save players. A bit hacky, but should get the job done.
                Paths.get(
                        DungeonCrawler.USER_DIR.toString(),
                        main.getGameData().getUser()
                )
        );
        playerDao.saveUser(player);

        //Save the game in the folder <USER_DIR>/<user>/<player>/
        GameSaveDao gameSaveDao = new ByteFileGameSaveDao(
                Paths.get(
                        DungeonCrawler.USER_DIR.toString(),
                        main.getGameData().getUser(),
                        main.getGameData().getPlayer()
                )
        );
        gameSaveDao.saveGame(new GameSave(new Date(System.currentTimeMillis()), startingLevel));

        //Set level in main to starting level.
        main.getGameData().setGameLevel(startingLevel);

        //Switch the window to LevelScreen.
        switchTo(new LevelScreen(main));
    }

}