/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti;

import java.util.Arrays;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import otmkurssiprojekti.Level.TopDownLevel;

/**
 *
 * @author gjuho
 */
public class OTMKurssiprojekti extends Application {
    
    private TopDownLevel tdl;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        tdl = new TopDownLevel();
    }
    
    

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Adventure Game");
        primaryStage.setResizable(false);
        
        //Nappi
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        //Teksti
        Text t = new Text();
        t.setFont(Font.font("MONOSPACED"));
//        t.setText(Arrays.deepToString(tdl.getLevelData()));

        BorderPane hud = new BorderPane();
        hud.setBottom(btn);
        hud.setCenter(t);
        primaryStage.setScene(new Scene(hud, 1024, 1024));
        primaryStage.show();
    }

}
