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
import otmkurssiprojekti.DataAccessObject.UserDao;
import otmkurssiprojekti.DungeonCrawler;

/**
 *
 * @author Juho Gröhn
 */
public class LoadUserScreen extends SwitchingScreen {

    private final UserDao fudao;
    private final Path[] users;

    private int pointer = 0;

    public LoadUserScreen(DungeonCrawler main) {
        super(main);
        fudao = new FileUserDao(DungeonCrawler.USER_DIR);
        users = fudao.loadUsers().stream()
                .toArray(Path[]::new);
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
                if (pointer < users.length) {
                    pointer++;
                }
                break;
            case ESCAPE:
                switchTo(new MainMenuScreen(main));
                break;
            case ENTER:
                if (pointer == 0) {
                    switchTo(new NewUserScreen(main));
                } else {
                    Path user = users[pointer - 1];
                    main.getGameData().setUser(user.getFileName().toString());
                    switchTo(new LoadPlayerScreen(main, user));
                }
                break;
            default:
                break;
        }

    }

    @Override
    public Parent getVisualisation() {
        BorderPane visual = new BorderPane();

        //TITLE
        Text title = new Text("Select User");
        title.setFont(Font.font("MONOSPACED"));
        BorderPane.setAlignment(title, Pos.CENTER);
        visual.setTop(title);

        //OPTIONS
        Text opts = new Text();

        List<String> optsList = new ArrayList<>();
        optsList.add("<new user>");
        for (Path user : users) {
            optsList.add(user.getFileName().toString());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < optsList.size(); i++) {
            if (i == pointer) {
                sb.append(">").append(optsList.get(i)).append("<");
            } else {
                sb.append(optsList.get(i));
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
    }

}
