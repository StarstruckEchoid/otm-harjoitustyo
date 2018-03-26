/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level;

import otmkurssiprojekti.Level.GameObjects.TopDownObject;
import otmkurssiprojekti.Level.GameObjects.GameCharacters.PlayerCharacter;
import java.util.List;
import otmkurssiprojekti.Level.GameObjects.GameCharacters.GameCharacter;

/**
 *
 * @author gjuho
 */
public class TopDownLevel {

    private TopDownLevelObject<PlayerCharacter> player;
    private List<TopDownLevelObject<GameCharacter>> npcs;

    public TopDownLevel() {
        this.player = new TopDownLevelObject<>();
    }

    public TopDownLevel(TopDownLevelObject<PlayerCharacter> player, List<TopDownLevelObject<GameCharacter>> npcs) {
        this.player = player;
        this.npcs = npcs;
    }

    public TopDownObject[][][] getLevelData() {
        TopDownObject[][][] levelData = new TopDownObject[16][16][8];
        //Player.
        Coords playerCoords = player.getCoords();
        levelData[playerCoords.z][playerCoords.y][playerCoords.x] = player.getTopDownObject();
        //Others characters.
        for(TopDownLevelObject npc : npcs){
            Coords npcc = npc.getCoords();
            levelData[npcc.z][npcc.y][npcc.x] = npc.getTopDownObject();
        }
        return levelData;
    }

}
