/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level;

import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.PlayerCharacter;
import otmkurssiprojekti.Level.GameObjects.NonPlayerCharacter;
import otmkurssiprojekti.Level.GameObjects.ImmutableObject;
import java.util.*;
import otmkurssiprojekti.Level.GameObjects.*;

/**
 *
 * @author gjuho
 */
public class GameLevel {

    public static final Coords DIMENSIONS = new Coords(16, 16, 8); //The level is a box from origin to DIMENSIONS Coords exclusive. Ie. The level has a size 16x16x8.
    private final String levelName;
    private final PlayerCharacter player;
    private final List<NonPlayerCharacter> npcs;
    private final List<ImmutableObject> blocks;
    private final List<InteractiveObject> interactives;
    private final List<LinkObject> levelLinks;
    private final List<PointsObject> points;

    public GameLevel(String levelName, PlayerCharacter player, List<NonPlayerCharacter> npcs, List<ImmutableObject> blocks, List<InteractiveObject> interactives, List<LinkObject> levelLinks, List<PointsObject> points) {
        this.levelName = levelName;
        this.player = player;
        this.npcs = npcs;
        this.blocks = blocks;
        this.interactives = interactives;
        this.levelLinks = levelLinks;
        this.points = points;
    }

    //Getters
    public String getLevelName() {
        return levelName;
    }

    public PlayerCharacter getPlayer() {
        return player;
    }

    public List<NonPlayerCharacter> getNpcs() {
        return npcs;
    }

    public List<ImmutableObject> getBlocks() {
        return blocks;
    }

    public List<InteractiveObject> getInteractives() {
        return interactives;
    }

    public List<LinkObject> getLevelLinks() {
        return levelLinks;
    }

    public List<PointsObject> getPoints() {
        return points;
    }

    //Others
    private static Boolean containsCoords(Coords coords) {
        return coords.greaterThanOrEqualTo(new Coords(0, 0, 0)) && coords.lesserThan(DIMENSIONS);
    }

    public void movePlayer(Direction dir) {
        Coords playerCoords = this.player.getCoords();
        Coords newCoords = playerCoords.sum(dir.getCoords());
        if (GameLevel.containsCoords(newCoords)) {
            player.move(dir);
        }
    }

    public GameObject[][][] getLevelData() {
        GameObject[][][] levelData = new GameObject[DIMENSIONS.z][DIMENSIONS.y][DIMENSIONS.x];
        //Everything in one big list.
        List<GameObject> allObjects = new ArrayList<>();
        allObjects.add(player);
        allObjects.addAll(npcs);
        allObjects.addAll(blocks);
        allObjects.addAll(interactives);
        allObjects.addAll(levelLinks);
        allObjects.addAll(points);
        //Convert list into matrix.
        for (GameObject gobj : allObjects) {
            Coords gobjc = gobj.getCoords();
            if (GameLevel.containsCoords(gobjc)) {
                levelData[gobjc.z][gobjc.y][gobjc.x] = gobj;
            }
        }
        return levelData;
    }

}
