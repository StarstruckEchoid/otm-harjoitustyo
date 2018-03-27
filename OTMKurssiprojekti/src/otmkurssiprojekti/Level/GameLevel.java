/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level;

import java.util.ArrayList;
import otmkurssiprojekti.Level.GameObjects.GameObject;
import otmkurssiprojekti.Level.GameObjects.GameCharacters.PlayerCharacter;
import java.util.List;
import otmkurssiprojekti.Level.GameObjects.GameCharacters.GameCharacter;

/**
 *
 * @author gjuho
 */
public class GameLevel {

    private GameLevelObject<PlayerCharacter> player;
    private List<GameLevelObject<GameCharacter>> npcs;

    public GameLevel() {
        this.player = new GameLevelObject(
                new PlayerCharacter(10, 10, 10),
                new Coords(0, 0, 0),
                Direction.UP
        );
        this.npcs = new ArrayList<>();
    }

    public GameLevelObject<PlayerCharacter> getPlayer() {
        return player;
    }

    public GameLevel(GameLevelObject<PlayerCharacter> player, List<GameLevelObject<GameCharacter>> npcs) {
        this.player = player;
        this.npcs = npcs;
    }

    public GameObject[][][] getLevelData() {
        GameObject[][][] levelData = new GameObject[8][16][16];
        //Player.
        Coords playerCoords = player.getCoords();
        levelData[playerCoords.z][playerCoords.y][playerCoords.x] = player.getTopDownObject();
        //Others characters.
        for (GameLevelObject npc : npcs) {
            Coords npcc = npc.getCoords();
            levelData[npcc.z][npcc.y][npcc.x] = npc.getTopDownObject();
        }
        return levelData;
    }

}
