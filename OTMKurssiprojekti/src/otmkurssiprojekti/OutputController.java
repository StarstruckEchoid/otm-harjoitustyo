/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import otmkurssiprojekti.Level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public class OutputController {
    
    private Scene scene;
    private GameLevel gamelvl;
    
    public OutputController(Scene scene, GameLevel gamelvl) {
        this.scene = scene;
        this.gamelvl = gamelvl;
    }
    
    public GameLevel getGamelvl() {
        return gamelvl;
    }
    
    public Parent getLevelTextRepresentation() {
        BorderPane hud = new BorderPane();
        Text t = new Text();
        t.setFont(Font.font("MONOSPACED"));

        //Kentän data.
        String display
                = FormatConverter.projectionToDenseString(
                        FormatConverter.project(
                                FormatConverter.levelDataToMatrix(
                                        gamelvl.getLevelData()
                                )
                        )
                );
        
        t.setText(display);
        
        hud.setCenter(t);
        
        return hud;
    }
    
    public void setDisplayToLevelText() {
        Parent level = this.getLevelTextRepresentation();
        this.scene.setRoot(level);
    }
    
    public Parent getMainMenu(){
        BorderPane main = new BorderPane();
        
        Text t = new Text();
        t.setFont(Font.font("VERDANA"));
        t.setText("Main Menu");
        
        Button btn = new Button();
        btn.setText("START");
        
        main.setTop(t);
        main.setCenter(btn);
        
        return main;
    }
    
    public void setDisplayToMainMenu(){
        Parent mainmenu = this.getMainMenu();
        this.scene.setRoot(mainmenu);
    }
    
}
