/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import otmkurssiprojekti.domain.dataservice.DataService;
import otmkurssiprojekti.domain.dataservice.TextFileDataService;
import otmkurssiprojekti.userinterface.screen.GameScreen;
import otmkurssiprojekti.userinterface.screen.MainMenuScreen;

/**
 *
 * @author gjuho
 */
public class DungeonCrawler extends Application {

    //Initialize constants.
    public static final String USERS_DIR = "users";
    public static final String LEVELS_DIR = "levels";
    public static final String FIRST_LEVEL = "Starting_Level.txt";
    private static final DataService DATA_SERVICE = new TextFileDataService();

    private static final Timer TIMER = new Timer();
    private static final int TICKS_PERIOD = 500; //Controls how often the game updates, eg. how often npcs move.
    private static final int FRAMES_PERIOD = 50; //Controls how often the screen updates. Reciprocal of frames per millisecond.
    private static final int DEBUG_PERIOD = 2000; //Controls how often debug actions are performed.
    private GameScreen gameScreen;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        DATA_SERVICE.setUsersDir(USERS_DIR);
        DATA_SERVICE.setLevelsDir(LEVELS_DIR);
        DATA_SERVICE.swapLevel(FIRST_LEVEL);
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
    public void stop() throws Exception {
        TIMER.cancel();
        super.stop();

    }
    //Getters and setters.

    public DataService getDataService() {
        return DATA_SERVICE;
    }

    /**
     * Gets GameScreen.
     *
     * @return The current GameScreen of DungeonCrawler.
     * @see GameScreen
     */
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    /**
     * Sets the game screen. This method allows any UI class that knows main to
     * change the game screen displayed. Mostly used by other GameScreens.
     *
     * @param gameScreen The GameScreen to be changed into.
     */
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
