/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import otmkurssiprojekti.level.BasicGameLevel;
import otmkurssiprojekti.level.GameLevel;
import otmkurssiprojekti.level.gameobjects.archetypes.ImmutableObjectArchetype;
import otmkurssiprojekti.level.gameobjects.gamecharacter.playercharacter.PlayerCharacter;
import otmkurssiprojekti.level.gameobjects.gameinanimates.ImmutableObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.InteractiveObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.LinkObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.PointsBall;
import otmkurssiprojekti.level.gameobjects.interfaces.NonPlayerCharacter;
import otmkurssiprojekti.level.gameobjects.interfaces.PointsSource;

/**
 *
 * @author gjuho
 */
public class TextFileLevelDao extends AbstractLevelDao implements GameLevelDao {

    public TextFileLevelDao(Path directory) {
        super(directory);
    }

    @Override
    public void saveLevel(GameLevel level) {

    }

    @Override
    public GameLevel loadLevel(Path levelPath) {
        try {
            String lines = Files.readAllLines(levelPath)
                    .stream()
                    .reduce("", (a, b) -> a + "\n" + b);
            GameLevel gameLevel = convertToGameLevel(lines);
            return gameLevel;
        } catch (IOException ex) {
            Logger.getLogger(TextFileLevelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private GameLevel convertToGameLevel(String string) {
        String[] fields = string.split("\n\n");
        String levelName = fields[0];
        PlayerCharacter player = makePlayerCharacter(fields[1]);
        List<NonPlayerCharacter> npcs = makeNPCList(fields[2]);
        List<ImmutableObject> blocks = makeBlockList(fields[3]);
        List<InteractiveObject> interactives = makeInteractiveObjectList(fields[4]);
        List<LinkObject> levelLinks = makeLevelLinkList(fields[5]);
        List<PointsBall> points = makePointsSourceList(fields[6]);

        return new BasicGameLevel(levelName, player, npcs, blocks, interactives, levelLinks, points);
    }

    private PlayerCharacter makePlayerCharacter(String field) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<NonPlayerCharacter> makeNPCList(String field) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<ImmutableObject> makeBlockList(String field) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<InteractiveObject> makeInteractiveObjectList(String field) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<LinkObject> makeLevelLinkList(String field) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<PointsBall> makePointsSourceList(String field) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
