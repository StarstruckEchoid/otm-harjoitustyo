/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.utilityclasses.TextFileGameLevels;

/**
 *
 * @author gjuho
 */
public class TextFileLevelDao extends AbstractLevelDao implements GameLevelDao {

    private static final Charset CHARSET = Charset.forName("UTF-8");

    public TextFileLevelDao(Path directory) {
        super(directory);
    }

    @Override
    public void saveLevel(GameLevel level, String name) throws IOException {
        Path levelPath = Paths.get(directory.toString(), name);
        List<String> textData = Arrays.asList(
                TextFileGameLevels.printGameLevel(level)
                        .split("\n")
        );
        Files.write(levelPath, textData, CHARSET);

    }

    @Override
    public GameLevel loadLevel(String levelName) throws IOException {
        StringBuilder lines = new StringBuilder();
        Scanner lineScanner = new Scanner(Paths.get(directory.toString(), levelName), CHARSET.name());
        while (lineScanner.hasNextLine()) {
            lines.append(lineScanner.nextLine()).append("\n");
        }
//            String lines = Files.readAllLines(Paths.get(directory.toString(), levelName), CHARSET)
//                    .stream()
//                    .reduce("", (a, b) -> a + b + "\n");

        GameLevel gameLevel = TextFileGameLevels.makeGameLevel(lines.toString());
        return gameLevel;
    }

}
