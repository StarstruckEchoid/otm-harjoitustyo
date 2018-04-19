/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level;

import otmkurssiprojekti.level.gameobjects.gamecharacter.playercharacter.PlayerCharacter;
import otmkurssiprojekti.level.gameobjects.interfaces.GameObject;
import otmkurssiprojekti.level.gameobjects.location.*;
import otmkurssiprojekti.level.gameobjects.*;
import java.util.*;
import otmkurssiprojekti.level.gameobjects.interfaces.NonPlayerCharacter;

/**
 *
 * @author gjuho
 */
public class BasicGameLevel implements GameLevel {
    
    public static final Coords DIMENSIONS = new Coords(16, 16, 8); //The level is a box from origin to DIMENSIONS Coords exclusive. Ie. The level has a size 16x16x8.

    private final String levelName;
    private PlayerCharacter player;
    private final List<NonPlayerCharacter> npcs;
    private final List<ImmutableObject> blocks;
    private final List<InteractiveObject> interactives;
    private final List<LinkObject> levelLinks;
    private final List<PointsBall> points;
    
    public BasicGameLevel() {
        this.levelName = null;
        this.player = null;
        this.npcs = null;
        this.blocks = null;
        this.interactives = null;
        this.levelLinks = null;
        this.points = null;
    }
    
    public BasicGameLevel(String levelName, PlayerCharacter player, List<NonPlayerCharacter> npcs, List<ImmutableObject> blocks, List<InteractiveObject> interactives, List<LinkObject> levelLinks, List<PointsBall> points) {
        this.levelName = levelName;
        this.player = player;
        this.npcs = npcs;
        this.blocks = blocks;
        this.interactives = interactives;
        this.levelLinks = levelLinks;
        this.points = points;
    }

    //Getters
    @Override
    public String getLevelName() {
        return levelName;
    }
    
    @Override
    public PlayerCharacter getPlayerCharacter() {
        return this.player;
    }

    //Setter
    @Override
    public void setPlayer(PlayerCharacter player) {
        this.player = player;
    }

    //Others
    @Override
    public boolean isOccupied(Coords coords) {
        return !BasicGameLevel.hasCoords(coords) || this.hasSolidBlockAt(coords);
    }
    
    protected static Boolean hasCoords(Coords coords) {
        return coords.greaterThanOrEqualTo(new Coords(0, 0, 0)) && coords.lesserThan(DIMENSIONS);
    }
    
    protected Boolean hasSolidBlockAt(Coords coords) {
        List<GameObject> possiblySolidBlocks = new ArrayList<>();
        possiblySolidBlocks.addAll(blocks);
        possiblySolidBlocks.addAll(interactives);
        return possiblySolidBlocks.stream()
                .filter(b -> b.getCoords().equals(coords))
                .anyMatch(b -> b.isSolid());
    }
    
    @Override
    public void movePlayer(Direction dir) {
        Coords playerCoords = this.player.getCoords();
        Coords newCoords = playerCoords.sum(dir.getCoords());
        if (!this.isOccupied(newCoords)) {
            player.move(dir);
        }
    }
    
    @Override
    public GameObject[][][] getLevelData() {
        GameObject[][][] levelData = new GameObject[DIMENSIONS.getZ()][DIMENSIONS.getY()][DIMENSIONS.getX()];
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
            if (BasicGameLevel.hasCoords(gobjc)) {
                levelData[gobjc.getZ()][gobjc.getY()][gobjc.getX()] = gobj;
            }
        }
        return levelData;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.levelName);
        hash = 89 * hash + Objects.hashCode(this.blocks);
        hash = 89 * hash + Objects.hashCode(this.levelLinks);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BasicGameLevel other = (BasicGameLevel) obj;
        if (!Objects.equals(this.levelName, other.levelName)) {
            return false;
        }
        if (!Objects.equals(this.blocks, other.blocks)) {
            return false;
        }
        return Objects.equals(this.levelLinks, other.levelLinks);
    }
    
    @Override
    public String toString() {
        return this.levelName;
    }
    
    @Override
    public void doGameTick() {
        npcs.forEach(npc -> npc.act(this));
    }
    
}
