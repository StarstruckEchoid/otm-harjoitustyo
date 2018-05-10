/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen;

import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import static javafx.scene.text.Font.font;
import javafx.scene.text.Text;
import otmkurssiprojekti.userinterface.DungeonCrawler;

/**
 *
 * @author Juho Gröhn
 */
public class GameOverScreen extends SwitchingScreen {
    
    public GameOverScreen(DungeonCrawler main) {
        super(main);
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        switchTo(new MainMenuScreen(main));
    }
    
    @Override
    public Parent getVisualisation() {
        BorderPane bp = new BorderPane();
        Text t = new Text("Game over.\nPress any key to continue.");
        t.setFont(font("MONOSPACED"));
        bp.setCenter(t);
        return bp;
    }
    
    @Override
    public void doGameTick() {
        
    }
    
}
