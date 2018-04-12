/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface.Renderer;

import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import otmkurssiprojekti.Level.GameLevel;
import otmkurssiprojekti.UtilityClasses.FormatConverter;


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
