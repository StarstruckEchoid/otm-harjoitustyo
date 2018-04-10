/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface;

import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import otmkurssiprojekti.DungeonCrawler;

/**
 *
 * @author Juho Gröhn
 */
public class LoadGameScreen extends SwitchingScreen {

    public LoadGameScreen(DungeonCrawler main) {
        super(main);
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {

    }

    @Override
    public Parent getVisualisation() {
        return new BorderPane(new Text("WIP"));
    }

    @Override
    public void doGameTick() {

    }

}
