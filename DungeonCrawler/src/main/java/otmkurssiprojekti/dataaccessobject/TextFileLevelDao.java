/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.IOException;
import java.nio.charset.Charset;
import static java.nio.charset.Charset.forName;
import static java.nio.file.Files.write;
import java.nio.file.Path;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import java.util.List;
import java.util.Scanner;
import otmkurssiprojekti.domain.level.GameLevel;
import static otmkurssiprojekti.utilityclasses.StringGameLevels.makeGameLevel;
import static otmkurssiprojekti.utilityclasses.StringGameLevels.printGameLevel;

/**
 *
 * @author gjuho
 */
public class TextFileLevelDao extends AbstractLevelDao implements GameLevelDao {

    private static final Charset CHARSET = forName("UTF-8");

    public TextFileLevelDao(Path directory) {
        super(directory);
    }

    @Override
    public void saveLevel(GameLevel level, String name) throws IOException {
        Path levelPath = get(directory.toString(), name);
        List<String> textData = asList(printGameLevel(level)
                        .split("\n")
        );
        write(levelPath, textData, CHARSET);

    }

    @Override
    public GameLevel loadLevel(String levelName) throws IOException {
        StringBuilder lines = new StringBuilder();
        Scanner lineScanner = new Scanner(get(directory.toString(), levelName), CHARSET.name());
        while (lineScanner.hasNextLine()) {
            lines.append(lineScanner.nextLine()).append("\n");
        }

        GameLevel gameLevel = makeGameLevel(lines.toString());
        return gameLevel;
    }

}
