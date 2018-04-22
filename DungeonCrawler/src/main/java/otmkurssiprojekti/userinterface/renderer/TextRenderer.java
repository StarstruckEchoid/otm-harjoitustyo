/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.renderer;

import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import otmkurssiprojekti.level.GameLevel;
import otmkurssiprojekti.level.gameobjects.location.FlatCoords;
import otmkurssiprojekti.utilityclasses.FormatConverter;

public class TextRenderer implements Renderer {

    public static final FlatCoords RENDER_SIZE = new FlatCoords(16, 16);

    @Override
    public Node getRender(GameLevel gameLevel) {
        Text t = new Text(
                FormatConverter.projectionToDenseString(gameLevelToMatrix(gameLevel))
        );
        t.setFont(Font.font("MONOSPACED"));
        return t;
    }

    public static char[][] gameLevelToMatrix(GameLevel gameLevel) {
        char[][] matrix = new char[RENDER_SIZE.getY()][RENDER_SIZE.getX()];

        gameLevel.getGameObjects().forEach((go) -> {
            FlatCoords c = go.getCoords();
            int x = c.getX();
            int y = c.getY();
            matrix[y][x] = go.getId();
        });
        return matrix;
    }

}
