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
import otmkurssiprojekti.level.gameobjects.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.level.gameobjects.gamecharacter.playercharacter.PlayerCharacter;
import otmkurssiprojekti.level.gameobjects.gameinanimates.ImmutableObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.InteractiveObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.LinkObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.PointsBall;
import otmkurssiprojekti.level.gameobjects.interfaces.NonPlayerCharacter;
import otmkurssiprojekti.level.gameobjects.interfaces.PointsSource;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;

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
        String[] attrs = field.split(";");
        PlayerCharacterArchetype pca = makePCArcheType(attrs[0]);
        Coords coords = makeCoords(attrs[1]);

        return new PlayerCharacter(pca, coords, Direction.DOWN);
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

    private <T implements enum> makePCArcheType(T, String attr) {
        for (T pca : T.values()) {
            if (pca.getName().equals(attr)) {
                return pca;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Converts string of the form [0-9]+,[0-9]+,[0-9]+ into coords. Example:
     * "0,9,11" -> new Coords(0, 9, 11). "-1,3,22" -> new Coords(-1, 3, 22)
     *
     * @param attr
     * @return
     */
    public Coords makeCoords(String attr) {
        if (!attr.matches("[0-9]+,[0-9]+,[0-9]+")) {
            throw new IllegalArgumentException();
        }

        String[] attrs = attr.split(",");
        int x = Integer.parseInt(attrs[0]);
        int y = Integer.parseInt(attrs[1]);
        int z = Integer.parseInt(attrs[2]);

        return new Coords(x, y, z);
    }

}
