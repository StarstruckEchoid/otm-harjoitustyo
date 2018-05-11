/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.level;

import java.util.*;
import otmkurssiprojekti.domain.gameobject.gameinanimates.Block;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LevelLink;
import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.interfaces.GameObject;
import otmkurssiprojekti.domain.gameobject.interfaces.Mobile;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import static otmkurssiprojekti.utilityclasses.StringGameLevels.printGameLevel;

/**
 * The most basic implementation of a GameLevel.
 *
 * @author gjuho
 */
public class BasicGameLevel implements GameLevel {

    /**
     * The level is a box from origin to DIMENSIONS Coords exclusive. Ie. The
     * level has a size 16x16x8.
     */
    public static final Coords DIMENSIONS = new Coords(16, 16, 8);

    private final String levelName;
    private PlayerCharacter player;
    private final List<NonPlayerCharacter> npcs;
    private final List<Block> blocks;
    private final List<InteractiveObject> interactives;
    private final List<LevelLink> levelLinks;
    private final List<PointsBall> points;

    public BasicGameLevel(String levelName, PlayerCharacter player, List<NonPlayerCharacter> npcs, List<Block> blocks, List<InteractiveObject> interactives, List<LevelLink> levelLinks, List<PointsBall> points) {
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

    @Override
    public List<NonPlayerCharacter> getNonPlayerCharacters() {
        return npcs;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public List<InteractiveObject> getInteractiveObjects() {
        return interactives;
    }

    @Override
    public List<LevelLink> getLevelLinks() {
        return levelLinks;
    }

    @Override
    public List<PointsBall> getPointsBalls() {
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
        return !hasCoords(coords) || this.hasSolidBlockAt(coords);
    }

    protected static Boolean hasCoords(Coords coords) {
        return coords.greaterThanOrEqualTo(new Coords(0, 0, 0)) && coords.lesserThan(DIMENSIONS);
    }

    protected Boolean hasSolidBlockAt(Coords coords) {
        List<GameObject> possiblySolidBlocks = new ArrayList<>();
        possiblySolidBlocks.addAll(blocks);
        for (InteractiveObject io : interactives) {
            possiblySolidBlocks.add(io);
            possiblySolidBlocks.addAll(io.getChildren());
        }
        return possiblySolidBlocks.stream()
                .filter(b -> b.getCoords().toFlatCoords().equals(coords.toFlatCoords()))
                .anyMatch(b -> b.isSolid());
    }

    @Override
    public void movePlayer(Direction dir) {
        moveMobile(player, dir);
        playerTouch();
    }

    public void moveMobile(Mobile mobile, Direction dir) {
        Coords mobileCoords = mobile.getCoords();
        Coords newCoords = mobileCoords.sum(dir.getCoords());
        if (!this.isInaccessible(newCoords)) {
            mobile.move(dir);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.levelName);
        hash = 37 * hash + Objects.hashCode(this.player);
        hash = 37 * hash + Objects.hashCode(this.blocks);
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
        if (obj instanceof GameLevel) {
            GameLevel gobj = (GameLevel) obj;
            return gobj.getLevelName().equals(this.getLevelName())
                    && gobj.getGameObjects().equals(this.getGameObjects());

        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return printGameLevel(this);
    }

    @Override
    public boolean doGameTick() {
        npcs.forEach(npc -> npc.act(this));
//        npcs.removeIf(npc -> npc.isDead());
        return this.player.isDead();
    }

    @Override
    public List<GameObject> getGameObjects() {
        List<GameObject> all = new ArrayList<>();
        all.addAll(blocks);
        for (InteractiveObject io : interactives) {
            all.add(io);
            all.addAll(io.getChildren());
        }
        all.addAll(levelLinks);
        all.addAll(npcs);
        all.addAll(points);
        return all;
    }

    @Override
    public void playerAttack(Direction dir) {
        Coords pcCoords = this.player.getCoords();
        Coords coords = this.player.getCoords().sum(dir.getCoords());
        this.npcs.stream()
                .filter(npc -> npc.getCoords().equals(pcCoords) || npc.getCoords().equals(coords))
                .forEach(npc -> this.player.hurt(npc));
        this.npcs.removeIf(npc -> npc.isDead());
    }

    @Override
    public Optional<String> playerInteract() {
        Coords pcCoords = this.player.getCoords();
        this.interactives.stream()
                .filter(i -> i.getCoords().equals(pcCoords))
                .forEach(i -> i.reactToPress());
        return this.levelLinks.stream()
                .filter(link -> link.getCoords().toFlatCoords().equals(pcCoords.toFlatCoords()))
                .map(link -> link.getLinkAddress())
                .findFirst();
    }

    private void playerTouch() {
        Coords pcCoords = this.player.getCoords();
        this.interactives.stream()
                .filter(i -> i.getCoords().equals(pcCoords))
                .forEach(i -> i.reactToTouch());
        this.points.removeIf(pb -> this.player.collectPoint(pb));
    }

}
