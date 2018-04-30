/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import otmkurssiprojekti.userinterface.screen.GameScreen;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import otmkurssiprojekti.dataaccessobject.dataobject.GameData;
import otmkurssiprojekti.dataaccessobject.dataobject.SimpleGameData;
import otmkurssiprojekti.dataaccessobject.TextFileLevelDao;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.userinterface.screen.MainMenuScreen;
import otmkurssiprojekti.utilityclasses.TextFileGameLevels;

/**
 *
 * @author gjuho
 */
public class DungeonCrawler extends Application {

    //Initialize constants.
    public static final Path USER_DIR = Paths.get("users");

    static {
        //Create file USER_DIR if it does not yet exist.
        if (!USER_DIR.toFile().exists() || !USER_DIR.toFile().isDirectory()) {
            try {
                Files.createDirectory(USER_DIR);
            } catch (IOException ex) {
                Logger.getLogger(DungeonCrawler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public static final String FIRST_LEVEL = "Starting Level";
    public static final Path LEVEL_DIR = Paths.get("levels");

    static {
        //Create file LEVEL_DIR if it does not yet exist.
        if (!LEVEL_DIR.toFile().exists() || !LEVEL_DIR.toFile().isDirectory()) {
            try {
                Files.createDirectory(LEVEL_DIR);
            } catch (IOException ex) {
                Logger.getLogger(DungeonCrawler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //PLACEHOLDER CODE: Makes a new level.
        GameLevel gameLvl = TextFileGameLevels.makeGameLevel(
                "Starting Level\n"
                + "\n"
                + "10;1;1;1;1;3,3,0;0\n"
                + "\n"
                + "%;7,10,0\n"
                + "r;8,8,0\n"
                + "d;4,9,0\n"
                + "¨;1,1,0\n"
                + "\n"
                + "0,,,,,,,,,,,,,,0\n"
                + "0,,,,,,,,,,,,,,0\n"
                + "0,,,000000000000\n"
                + "0,,,,,,,,,,,,,,0\n"
                + "0,,,,,,,,,,,,,,0\n"
                + "0,,000,,000,,,00\n"
                + "0,,0,,,,0,,,,,,0\n"
                + "0,,0,,,,0,,,,,,0\n"
                + "000000000,,,,,,0\n"
                + "0,,,,,,,0,,,,,,0\n"
                + "0,,,0,,,0,,,,,,0\n"
                + "0,,,0,,,0,,,,,,0\n"
                + "0,,,00000,,,,,,0\n"
                + "0,,,,,,,,,,,,,,0\n"
                + "0,,,,,,,,,,,,,,0\n"
                + "000000000000,,,0\n"
                + "\n"
                + "EMPTY\n"
                + "\n"
                + "EMPTY\n"
                + "\n"
                + "EMPTY"
        );

        //Inserts it into LEVEL_DIR.
        new TextFileLevelDao(LEVEL_DIR).saveLevel(gameLvl);
    }
    private static final Timer TIMER = new Timer();
    private static final int TICKS_PERIOD = 500; //Controls how often the game updates, eg. how often npcs move.
    private static final int FRAMES_PERIOD = 50; //Controls how often the screen updates. Reciprocal of frames per millisecond.

    private GameData gameData;
    private GameScreen gameScreen;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        this.setGameData(new SimpleGameData(null, null, null));
        this.setGameScreen(new MainMenuScreen(this));
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Adventure Game");
        primaryStage.setMaximized(true);
        Scene mainScene = new Scene(gameScreen.getVisualisation());

        //Keyboard input.
        mainScene.setOnKeyPressed(e -> gameScreen.handleKeyEvent(e));
        //Refresh the game.
        initGameTicks();
        //Referesh the sreen.
        initRendering(mainScene);

        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    @Override
    public void stop() {
        TIMER.cancel();
        try {
            super.stop();
            System.exit(0);
        } catch (Exception ex) {
            Logger.getLogger(DungeonCrawler.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        } finally {
            System.exit(2);
        }
    }
    //Getters and setters.

    public GameData getGameData() {
        return gameData;
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    //private methods.
    private void initRendering(Scene mainScene) {
        TIMER.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mainScene.setRoot(gameScreen.getVisualisation());
            }
        }, 0, FRAMES_PERIOD);
    }

    private void initGameTicks() {
        TIMER.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameScreen.doGameTick(); //What to do.
            }
        }, 0, TICKS_PERIOD);
    }

}
