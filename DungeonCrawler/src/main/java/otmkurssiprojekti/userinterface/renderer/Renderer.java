/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.renderer;

import javafx.scene.Node;
import otmkurssiprojekti.level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public interface Renderer {
    Node getRender(GameLevel gameLevel);
}
