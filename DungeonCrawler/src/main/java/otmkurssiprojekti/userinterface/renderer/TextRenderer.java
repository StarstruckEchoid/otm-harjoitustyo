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
import otmkurssiprojekti.utilityclasses.FormatConverter;


public class TextRenderer implements Renderer {

    @Override
    public Node getRender(GameLevel gameLevel) {
        Text t = new Text();
        String display
                = FormatConverter.projectionToDenseString(
                        FormatConverter.project(
                                FormatConverter.levelDataToMatrix(
                                        gameLevel.getLevelData()
                                )
                        )
                );

        t.setText(display);
        t.setFont(Font.font("MONOSPACED"));
        return t;
    }
    
}
