/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import otmkurssiprojekti.UserInterface.GameScreen;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import otmkurssiprojekti.DataAccessObject.ByteFileLevelDao;
import otmkurssiprojekti.Level.GameLevel;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.Level.GameObjects.*;
import otmkurssiprojekti.Level.GameObjects.Archetypes.ImmutableObjectArchetype;
import otmkurssiprojekti.UserInterface.MainMenuScreen;

/**
 *
 * @author gjuho
 */
public class DungeonCrawler extends Application {

    //Initialize constants.
    public static final Path USER_DIR = Paths.get("users");

    static {
        //Create file USER_DIR if it does not yet exist.
        if (USER_DIR.toFile().exists() && USER_DIR.toFile().isDirectory()) {
            //Everything is okay.
        } else {
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
        if (LEVEL_DIR.toFile().exists() && LEVEL_DIR.toFile().isDirectory()) {
            //Everything is okay.
        } else {
            try {
                Files.createDirectory(LEVEL_DIR);
            } catch (IOException ex) {
                Logger.getLogger(DungeonCrawler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //PLACEHOLDER: Makes a new level.
        String levelName = FIRST_LEVEL;
        PlayerCharacter player = new PlayerCharacter(10, 1, 1, 1, 1, new Coords(3, 3, 3), Direction.DOWN);
        List<NonPlayerCharacter> npcs = new ArrayList<>();
        List<ImmutableObject> blocks = new ArrayList<>();
        for (int x = 0; x < GameLevel.DIMENSIONS.x; x++) {
            for (int y = 0; y < GameLevel.DIMENSIONS.y; y++) {
                blocks.add(new ImmutableObject(ImmutableObjectArchetype.GRASS, new Coords(x, y, 4), Direction.DOWN));
            }
        }
        List<InteractiveObject> interactives = new ArrayList<>();
        List<LinkObject> levelLinks = new ArrayList<>();
        List<PointsObject> points = new ArrayList<>();
        GameLevel gamelvl = new GameLevel(levelName, player, npcs, blocks, interactives, levelLinks, points);

        //Inserts it into LEVEL_DIR.
        new ByteFileLevelDao(LEVEL_DIR).saveLevel(gamelvl);
    }
    private static final Timer TIMER = new Timer();
    private static final int TICKS_PERIOD = 1_000; //Controls how often the game updates, eg. how often npcs move.
    private static final int FRAMES_PERIOD = 30; //Controls how often the screen updates. Reciprocal of frames per millisecond.

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
        TIMER.scheduleAtFixedRate(
                new TimerTask() {
            @Override
            public void run() {
                gameScreen.doGameTick(); //What to do.
            }
        },
                0, //Delay.
                TICKS_PERIOD); //Period.
        //Referesh the sreen.
        TIMER.scheduleAtFixedRate(
                new TimerTask() {
            @Override
            public void run() {
                mainScene.setRoot(gameScreen.getVisualisation());
            }
        }, 0, FRAMES_PERIOD);
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception {
        TIMER.cancel();
        super.stop();
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

}
