/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.userinterface.screen;

import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import otmkurssiprojekti.dataaccessobject.FileUserDao;
import otmkurssiprojekti.DungeonCrawler;

/**
 *
 * @author Juho Gröhn
 */
public class NewUserScreen extends SwitchingScreen{
    private final FileUserDao fudao;
    private final StringBuilder username;

    public NewUserScreen(DungeonCrawler main) {
        super(main);
        this.fudao = new FileUserDao(DungeonCrawler.USER_DIR);
        this.username = new StringBuilder();
    }

    @Override
    public void handleKeyEvent(KeyEvent e) {
        KeyCode kc = e.getCode();
        switch (kc) {
            case ESCAPE:
                switchTo(new MainMenuScreen(main));
                break;
            case ENTER:
                //Save user and continue to player selection.
                String userString = username.toString();
                Path userPath = Paths.get(DungeonCrawler.USER_DIR.toString(), userString);
                
                fudao.saveUser(username.toString());
                main.getGameData().setUser(username.toString());
                switchTo(new LoadPlayerScreen(main));
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
