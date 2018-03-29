/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level;

import java.util.*;
import otmkurssiprojekti.Level.GameObjects.*;
import otmkurssiprojekti.Level.GameObjects.GameBlocks.*;
import otmkurssiprojekti.Level.GameObjects.GameCharacters.*;

/**
 *
 * @author gjuho
 */
public class GameLevel {

    private static Coords dimensions = new Coords(16, 16, 8); //The level is a box from origin to dimensions Coords exclusive. Ie. The level has a size 16x16x8.
    private final GameLevelObject<PlayerCharacter> player;
    private final List<GameLevelObject<GameCharacter>> npcs;
    private final List<GameLevelObject<GameBlock>> blocks;

    public GameLevel() {
        this.player = new GameLevelObject(
                new PlayerCharacter(10, 10, 10),
                new Coords(0, 0, 0),
                Direction.UP
        );
        this.npcs = new ArrayList<>();
        this.blocks = new ArrayList<>();
    }

    public GameLevel(GameLevelObject<PlayerCharacter> player, List<GameLevelObject<GameCharacter>> npcs, List<GameLevelObject<GameBlock>> blocks) {
        this.player = player;
        this.npcs = npcs;
        this.blocks = blocks;
    }

    public static Coords getDimensions() {
        return dimensions;
    }

    public GameLevelObject<PlayerCharacter> getPlayer() {
        return player;
    }

    public List<GameLevelObject<GameCharacter>> getNpcs() {
        return npcs;
    }

    public List<GameLevelObject<GameBlock>> getBlocks() {
        return blocks;
    }

    private static Boolean containsCoords(Coords coords) {
        return coords.greaterThanOrEqualTo(new Coords(0, 0, 0)) && coords.lesserThan(dimensions);
    }

    public void movePlayer(Direction dir) {
        Coords playerCoords = this.player.getCoords();
        Coords newCoords = playerCoords.sum(dir.getCoords());
        if (GameLevel.containsCoords(newCoords)) {
            player.move(dir);
        }
    }

    public GameObject[][][] getLevelData() {
        GameObject[][][] levelData = new GameObject[8][16][16];
        //Player.
        Coords playerCoords = player.getCoords();
        levelData[playerCoords.z][playerCoords.y][playerCoords.x] = player.getTopDownObject();
        //Others characters.
        for (GameLevelObject npc : npcs) {
            Coords npcc = npc.getCoords();
            if (GameLevel.containsCoords(npcc)) {
                levelData[npcc.z][npcc.y][npcc.x] = npc.getTopDownObject();
            }
        }

        for (GameLevelObject block : blocks) {
            Coords blockc = block.getCoords();
            if (GameLevel.containsCoords(blockc)) {
                levelData[blockc.z][blockc.y][blockc.x] = block.getTopDownObject();
            }
        }
        return levelData;
    }

}
