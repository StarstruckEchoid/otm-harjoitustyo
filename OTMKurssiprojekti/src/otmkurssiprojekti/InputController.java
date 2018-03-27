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
        GameWindow window = this.outputController.window;
        switch (window) {
            case IN_GAME:
                handleInGame(e);
                break;
            case PAUSE_MENU:
                handlePauseMenu(e);
                break;
            case QUIT_GAME:
                handleQuitGame(e);
                break;
            case MAIN_MENU:
                handleMainMenu(e);
                break;
            case NEW_PLAYER:
                handleNewPlayer(e);
                break;
            case LOAD_PLAYER:
                handleLoadPlayer(e);
                break;
            case PLAYER_MENU:
                handlePlayerMenu(e);
                break;
            case NEW_GAME:
                handleNewGame(e);
                break;
            case LOAD_GAME:
                handleLoadGame(e);
                break;
            case SAVE_GAME:
                handleSaveGame(e);
                break;
            default:
                break;
        }

    }

    public void handleInGame(KeyEvent e) {
        KeyCode kc = e.getCode();

        GameLevel gamelvl = outputController.getGamelvl();
        switch (kc) {

            case W:
            case A:
            case S:
            case D:
                this.movePlayer(e);
                break;
            case ESCAPE:
                outputController.setDisplayToPauseMenu();
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
        outputController.setDisplayToInGameText();
    }

    private void handlePauseMenu(KeyEvent e) {
        KeyCode kc = e.getCode();
        switch (kc) {
            case ESCAPE:
                outputController.setDisplayToInGameText();
                break;
            case A:
                break;
            case S:
                break;
            case X:
                outputController.setDisplayToMainMenu();
                break;
            default:
                break;
        }
    }

    private void handleQuitGame(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void handleMainMenu(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void handleNewPlayer(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void handleLoadPlayer(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void handlePlayerMenu(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void handleNewGame(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void handleLoadGame(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void handleSaveGame(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
