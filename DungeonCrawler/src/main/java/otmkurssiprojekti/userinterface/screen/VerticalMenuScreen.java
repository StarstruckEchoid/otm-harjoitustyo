/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import otmkurssiprojekti.DungeonCrawler;

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
        KeyCode kc = e.getCode();
        switch (kc) {
            case W:
                if (pointer > 0) {
                    pointer--;
                }
                break;
            case S:
                if (pointer + 1 < getOptsList().size()) {
                    pointer++;
                }
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

    }

    @Override
    public Parent getVisualisation() {
        BorderPane visual = new BorderPane();

        //TITLE
        Text title = new Text(getTitleText());
        title.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(title, Pos.CENTER);
        visual.setTop(title);

        //OPTIONS
        Text opts = new Text();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getOptsList().size(); i++) {
            if (i == pointer) {
                sb.append(">").append(getOptsList().get(i)).append("<");
            } else {
                sb.append(getOptsList().get(i));
            }
            sb.append("\n");
        }

        opts.setText(sb.toString());
        opts.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(opts, Pos.CENTER);
        visual.setCenter(opts);

        //LEGEND
        Text legend = new Text(
                "W: up\t"
                + "S:down\t"
                + "ESC:back\t"
                + "ENTER:select"
        );
        legend.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(legend, Pos.CENTER);
        visual.setBottom(legend);

        return visual;
    }

    @Override
    public void doGameTick() {
        //Nothing happens while in a menu screen.
    }

    protected abstract void doEnterAction(int index);

    protected abstract List<String> getOptsList();

    protected abstract String getTitleText();

    protected abstract GameScreen getReturnScreen();
}
