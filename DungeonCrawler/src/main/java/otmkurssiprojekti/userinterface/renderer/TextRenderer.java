/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.renderer;

import static java.lang.String.valueOf;
import javafx.scene.Node;
import static javafx.scene.text.Font.font;
import javafx.scene.text.Text;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.FlatCoords;
import otmkurssiprojekti.domain.level.GameLevel;

public class TextRenderer implements Renderer {

    public static final FlatCoords RENDER_SIZE = new FlatCoords(16, 16);

    @Override
    public Node getRender(GameLevel gameLevel) {
        Text levelContents = new Text(
                projectionToDenseString(gameLevelToMatrix(gameLevel))
        );
        levelContents.setFont(font("MONOSPACED"));
        return levelContents;
    }

    private static char[][] gameLevelToMatrix(GameLevel gameLevel) {
        char[][] matrix = new char[RENDER_SIZE.getY()][RENDER_SIZE.getX()];

        gameLevel.getGameObjects().forEach((go) -> {
            FlatCoords c = go.getCoords();
            int x = c.getX();
            int y = c.getY();
            matrix[y][x] = go.getId();
        });

        //Player character is always on top, no matter what.
        PlayerCharacter pc = gameLevel.getPlayer();
        Coords pcCoords = pc.getCoords();
        matrix[pcCoords.getY()][pcCoords.getX()] = pc.getId();

        return matrix;
    }

    private static String projectionToDenseString(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = matrix.length; i > 0; i--) {
            char[] row = matrix[i - 1];
            sb.append(valueOf(row));
            sb.append('\n');
        }
        return sb.toString();
    }

}
