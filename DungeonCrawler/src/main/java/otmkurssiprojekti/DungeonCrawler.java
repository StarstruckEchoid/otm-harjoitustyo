/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti;

import otmkurssiprojekti.level.gameobjects.gameinanimates.LinkObject;
import otmkurssiprojekti.level.gameobjects.gamecharacter.playercharacter.PlayerCharacter;
import otmkurssiprojekti.level.gameobjects.gameinanimates.InteractiveObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.ImmutableObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.PointsBall;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import otmkurssiprojekti.userinterface.screen.GameScreen;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import otmkurssiprojekti.dataaccessobject.ByteFileLevelDao;
import otmkurssiprojekti.level.BasicGameLevel;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;
import otmkurssiprojekti.level.gameobjects.archetypes.ImmutableObjectArchetype;
import otmkurssiprojekti.level.gameobjects.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.level.gameobjects.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.level.gameobjects.interfaces.NonPlayerCharacter;
import otmkurssiprojekti.userinterface.screen.MainMenuScreen;

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
        BasicGameLevel gameLvl;
        String levelName = FIRST_LEVEL;
        PlayerCharacter player = new PlayerCharacter(10, 1, 1, 1, 1, new Coords(3, 3, 0), Direction.DOWN);
        List<NonPlayerCharacter> npcs = new ArrayList<>();
        //Add some npcs.
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.VILLAGER, new Coords(7, 10, 0), Direction.DOWN));
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.RAT, new Coords(8, 8, 0), Direction.DOWN));
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.DEER, new Coords(4, 9, 0), Direction.DOWN));
        npcs.add(new HostileNonPlayerCharacter(NonPlayerCharacterArchetype.FLY, new Coords(1, 1, 0), Direction.DOWN));
        List<ImmutableObject> blocks = new ArrayList<>();
        for (int x = 0; x < BasicGameLevel.DIMENSIONS.getX(); x++) {
            for (int y = 0; y < BasicGameLevel.DIMENSIONS.getY(); y++) {
                if (x == 0 || x == BasicGameLevel.DIMENSIONS.getX() - 1) {
                    //Some solid blocks.
                    blocks.add(new ImmutableObject(ImmutableObjectArchetype.STONE_WALL, new Coords(x, y, 1), Direction.DOWN));
                } else {
                    //Non-solid blocks.
                    blocks.add(new ImmutableObject(ImmutableObjectArchetype.GRASS, new Coords(x, y, 1), Direction.DOWN));
                }
            }
        }
        List<InteractiveObject> interactives = new ArrayList<>();
        List<LinkObject> levelLinks = new ArrayList<>();
        List<PointsBall> points = new ArrayList<>();
        BasicGameLevel gamelvl = new BasicGameLevel(levelName, player, npcs, blocks, interactives, levelLinks, points);

        //Inserts it into LEVEL_DIR.
        new ByteFileLevelDao(LEVEL_DIR).saveLevel(gamelvl);
    }
    private static final Timer TIMER = new Timer();
    private static final int TICKS_PERIOD = 50; //Controls how often the game updates, eg. how often npcs move.
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
