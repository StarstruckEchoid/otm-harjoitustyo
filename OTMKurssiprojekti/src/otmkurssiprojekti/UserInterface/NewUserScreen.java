/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UserInterface;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import otmkurssiprojekti.DataAccessObject.FileUserDao;
import otmkurssiprojekti.OTMKurssiprojekti;

/**
 *
 * @author Juho Gröhn
 */
public class NewUserScreen implements GameScreen {

    private final otmkurssiprojekti.OTMKurssiprojekti main;
    private final FileUserDao fudao;
    private final StringBuilder username;

    public NewUserScreen(OTMKurssiprojekti main) {
        this.main = main;
        this.fudao = new FileUserDao(OTMKurssiprojekti.USER_DIR);
        this.username = new StringBuilder();
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        KeyCode kc = e.getCode();
        switch (kc) {
            case ESCAPE:
                main.setGameScreen(new MainMenuScreen(main));
                break;
            case ENTER:
                fudao.saveUser(username.toString());
                main.getGameData().setUser(username.toString());
                main.setGameScreen(new LoadPlayerScreen(main));
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
        BorderPane visual = new BorderPane();

        //TITLE
        Text title = new Text("Create new user User");
        title.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(title, Pos.CENTER);
        visual.setTop(title);

        //OPTIONS
        Text opts = new Text("Username: " + username.toString());
        opts.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(opts, Pos.CENTER);
        visual.setCenter(opts);

        //LEGEND
        Text legend = new Text(
                "BACKSPACE: backspace\t"
                + "ESC:back\t"
                + "ENTER:enter name"
        );
        legend.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(legend, Pos.CENTER);
        visual.setBottom(legend);

        return visual;
    }

    @Override
    public void doGameTick() {
    }

}
