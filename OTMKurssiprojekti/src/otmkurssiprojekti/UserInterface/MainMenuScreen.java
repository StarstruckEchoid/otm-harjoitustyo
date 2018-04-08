/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface;

import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Juho Gröhn
 */
public class MainMenuScreen implements GameScreen {

    public MainMenuScreen() {
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Parent getVisualisation() {
        BorderPane main = new BorderPane();

        Text t = new Text();
        t.setFont(Font.font("MONOSPACED"));
        t.setTextAlignment(TextAlignment.CENTER);
        t.setText("Main Menu");

        Text s = new Text();
        s.setFont(Font.font("MONOSPACED"));
        s.setText("Press ENTER to start game");

        main.setTop(t);
        main.setCenter(s);

        return main;
    }

    @Override
    public void doGameTick() {
    }

}
