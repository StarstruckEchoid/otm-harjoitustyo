/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import otmkurssiprojekti.Level.Direction;
import otmkurssiprojekti.Level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public class InputController {

    private final OutputController outputController;

    public InputController(OutputController outputController) {
        this.outputController = outputController;
    }

    public void handleKeyEvent(KeyEvent e) {
        KeyCode kc = e.getCode();
        GameLevel gamelvl = outputController.getGamelvl();
        switch (kc) {

            case W:
            case A:
            case S:
            case D:
                this.movePlayer(e);
                break;
            case M:
                outputController.setDisplayToMainMenu();
                break;
            default:
                break;
        }
    }

    private void movePlayer(KeyEvent e) {
        KeyCode kc = e.getCode();
        GameLevel gamelvl = outputController.getGamelvl();
        switch (kc) {
            case W:
                gamelvl.movePlayer(Direction.UP);
                break;
            case A:
                gamelvl.movePlayer(Direction.LEFT);
                break;
            case S:
                gamelvl.movePlayer(Direction.DOWN);
                break;
            case D:
                gamelvl.movePlayer(Direction.RIGHT);
                break;
            default:
                break;
        }
        outputController.setDisplayToLevelText();
    }

}
