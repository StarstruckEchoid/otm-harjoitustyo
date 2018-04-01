/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti;

import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.UserInterface.InputController;
import otmkurssiprojekti.UserInterface.OutputController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import otmkurssiprojekti.Level.GameLevel;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.Level.GameObjects.*;

/**
 *
 * @author gjuho
 */
public class OTMKurssiprojekti extends Application {

    private GameLevel gamelvl;
    private Scene mainScene;
    private static InputController inControl;
    private static OutputController outControl;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        String levelName = "TestLevel";
        PlayerCharacter player = new PlayerCharacter(new Coords(0, 0, 0), 10, 10, Direction.DOWN, 10);
        List<NonPlayerCharacter> npcs = new ArrayList<>();
        List<ImmutableObject> blocks = new ArrayList<>();
        for (int x = 0; x < GameLevel.DIMENSIONS.x; x++) {
            for (int y = 0; y < GameLevel.DIMENSIONS.y; y++) {
                blocks.add(new ImmutableObject(',', false, true, new Coords(x, y, 4), Direction.DOWN));
            }
        }
        List<InteractiveObject> interactives = new ArrayList<>();
        List<LinkObject> levelLinks = new ArrayList<>();
        List<PointsObject> points = new ArrayList<>();

        gamelvl = new GameLevel(levelName, player, npcs, blocks, interactives, levelLinks, points);
        mainScene = new Scene(new BorderPane());
        outControl = new OutputController(mainScene, gamelvl);
        mainScene.setRoot(outControl.getInGameText());
        inControl = new InputController(outControl);
        outControl.setWindow(GameWindow.IN_GAME);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Adventure Game");
        primaryStage.setMaximized(true);
        //Näppäimistösyöte.
        mainScene.setOnKeyPressed(e -> inControl.handleKeyEvent(e));
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

}
