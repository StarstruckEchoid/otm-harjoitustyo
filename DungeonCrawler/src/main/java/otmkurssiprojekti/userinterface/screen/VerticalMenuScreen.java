/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen;

import java.io.IOException;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import otmkurssiprojekti.userinterface.DungeonCrawler;

/**
 *
 * @author Juho Gröhn
 */
public abstract class VerticalMenuScreen extends SwitchingScreen {
    
    protected int pointer = 0;
    
    public VerticalMenuScreen(DungeonCrawler main) {
        super(main);
    }
    
    @Override
    public void handleKeyEvent(KeyEvent e) {
        try {
            switch (e.getCode()) {
                case UP:
                    movePointer(-1);
                    break;
                case DOWN:
                    movePointer(+1);
                    break;
                case ESCAPE:
                    switchTo(getReturnScreen());
                    break;
                case ENTER:
                    doEnterAction(pointer);
                    break;
                default:
                    break;
            }
        } catch (IOException ioe) {
            switchTo(new ErrorScreen(main, ioe));
        }
    }
    
    @Override
    public Parent getVisualisation() {
        BorderPane visual = new BorderPane();

        //TITLE
        Text title = getAsText(getTitle());
        visual.setTop(title);

        //OPTIONS
        Text opts = getAsText(optsToString(getOptsList()));
        visual.setCenter(opts);

        //LEGEND
        Text legend = getAsText(getLegend());
        visual.setBottom(legend);
        
        return visual;
    }
    
    @Override
    public void doGameTick() {
        //Nothing happens while in a menu screen.
    }

    //Private helper methods.
    private void movePointer(int i) {
        if (i < 0 && pointer > 0) {
            pointer--;
        } else if (i > 0 && pointer + 1 < getOptsList().size()) {
            pointer++;
        }
    }
    
    private Text getAsText(String string) {
        Text text = new Text(string);
        text.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(text, Pos.CENTER);
        return text;
    }
    
    private String optsToString(List<Object> opts) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < opts.size(); i++) {
            String opt = opts.get(i).toString();
            if (i == pointer) {
                sb.append(">").append(opt).append("<");
            } else {
                sb.append(opt);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    //Abstract methods.
    protected abstract void doEnterAction(int index) throws IOException;
    
    protected abstract String getTitle();
    
    protected abstract List<Object> getOptsList();
    
    protected String getLegend() {
        return "UP: up\t"
                + "DOWN: down\t"
                + "ESC: back\t"
                + "ENTER: select";
    }
    
    protected abstract GameScreen getReturnScreen() throws IOException;
}
