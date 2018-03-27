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
import otmkurssiprojekti.Level.GameLevelObject;

/**
 *
 * @author Juho Gröhn
 */
public class InputController {
    
    private OutputController outputController;

    public InputController(OutputController outputController) {
        this.outputController = outputController;
    }

    public void handleKeyEvent(KeyEvent e) {
        KeyCode kc = e.getCode();
        GameLevel gamelvl = outputController.getGamelvl();
        GameLevelObject pc = gamelvl.getPlayer();
        switch (kc) {
            case W:
                pc.move(Direction.UP);
                break;
            case A:
                pc.move(Direction.LEFT);
                break;
            case S:
                pc.move(Direction.DOWN);
                break;
            case D:
                pc.move(Direction.RIGHT);
                break;
            default:
                break;
        }
        outputController.setDisplayToLevelText();
    }

}
