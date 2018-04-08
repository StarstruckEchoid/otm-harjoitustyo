/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import otmkurssiprojekti.DataAccessObject.FileUserDao;

/**
 *
 * @author Juho Gröhn
 */
public class NewUserScreen implements GameScreen {

    private static final FileUserDao FUDAO = new FileUserDao(otmkurssiprojekti.OTMKurssiprojekti.USER_DIR);
    private final StringBuilder username = new StringBuilder();

    public NewUserScreen() {
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        KeyCode kc = e.getCode();
        switch (kc) {
            case ESCAPE:
                otmkurssiprojekti.OTMKurssiprojekti.setGameScreen(new MainMenuScreen());
                break;
            case ENTER:
                FUDAO.saveUser(username.toString());
                otmkurssiprojekti.OTMKurssiprojekti.setGameScreen(new LoadUserScreen());
                break;
            case BACK_SPACE:
                username.deleteCharAt(username.length() - 1);
                break;
            default:
                if (kc.isLetterKey()) {
                    username.append(kc.toString());
                }
                break;
        }
    }

    @Override
    public Parent getVisualisation() {
        BorderPane main = new BorderPane();

        //TITLE
        Text title = new Text("Create new user User");
        title.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(title, Pos.CENTER);
        main.setTop(title);

        //OPTIONS
        Text opts = new Text("Username: " + username.toString());
        opts.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(opts, Pos.CENTER);
        main.setCenter(opts);

        //LEGEND
        Text legend = new Text(
                "BACKSPACE: backspace\t"
                + "ESC:back\t"
                + "ENTER:enter name"
        );
        legend.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(legend, Pos.CENTER);
        main.setBottom(legend);

        return main;
    }

    @Override
    public void doGameTick() {
    }

}
