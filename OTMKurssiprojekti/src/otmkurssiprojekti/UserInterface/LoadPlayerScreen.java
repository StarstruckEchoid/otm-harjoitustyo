/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface;

import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import otmkurssiprojekti.OTMKurssiprojekti;

/**
 *
 * @author Juho Gröhn
 */
public class LoadPlayerScreen implements GameScreen {

    private final otmkurssiprojekti.OTMKurssiprojekti main;

    public LoadPlayerScreen(OTMKurssiprojekti main) {
        this.main = main;
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        KeyCode kc = e.getCode();
        switch (kc) {
            case ESCAPE:
                main.setGameScreen(new LoadUserScreen(main));
                break;
            default:
                break;
        }
    }

    @Override
    public Parent getVisualisation() {
        return new BorderPane(new Text("WIP"));
    }

    @Override
    public void doGameTick() {
    }

}
