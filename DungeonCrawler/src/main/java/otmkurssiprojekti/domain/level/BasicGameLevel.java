/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.level;

import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.gameinanimates.ImmutableObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LinkObject;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.GameObject;
import java.util.*;
import otmkurssiprojekti.domain.gameobject.interfaces.Mobile;
import otmkurssiprojekti.domain.gameobject.interfaces.NonPlayerCharacter;

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
    public PlayerCharacter getPlayer() {
        return this.player;
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

    public List<PointsBall> getPoints() {
        return points;
    }

    //Setter
    @Override
    public void setPlayer(PlayerCharacter player) {
        this.player = player;
    }

    //Others
    @Override
    public boolean isInaccessible(Coords coords) {
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
        moveMobile(player, dir);
    }

    public void moveMobile(Mobile mobile, Direction dir) {
        Coords mobileCoords = mobile.getCoords();
        Coords newCoords = mobileCoords.sum(dir.getCoords());
        if (!this.isInaccessible(newCoords)) {
            mobile.move(dir);
        }
    }

    @Deprecated
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
    public boolean doGameTick() {
        npcs.forEach(npc -> npc.act(this));
        npcs.removeIf(i -> i.isDead());
        return this.player.isDead();
    }

    @Override
    public List<GameObject> getGameObjects() {
        List<GameObject> all = new ArrayList<>();
        all.add(player);
        all.addAll(blocks);
        all.addAll(interactives);
        all.addAll(levelLinks);
        all.addAll(npcs);
        all.addAll(points);
        return all;
    }

    @Override
    public void playerAttack(Direction dir) {
        Coords coords = this.player
                .getCoords()
                .sum(dir.getCoords());
        this.npcs.stream()
                .filter(npc -> npc.getCoords().equals(coords))
                .forEach(npc -> npc.takeDamage(this.player));
    }

}