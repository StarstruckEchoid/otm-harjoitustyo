/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface;

import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Juho Gröhn
 */
public interface GameScreen {
    public void handleKeyEvent(KeyEvent e);
    public Parent getVisualisation();
    public void doGameTick();
}
