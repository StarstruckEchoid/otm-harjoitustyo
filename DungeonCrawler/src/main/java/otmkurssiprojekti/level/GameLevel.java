/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level;

import java.io.Serializable;
import java.util.List;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;
import otmkurssiprojekti.level.gameobjects.interfaces.GameObject;
import otmkurssiprojekti.level.gameobjects.gamecharacter.playercharacter.PlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
public interface GameLevel extends Serializable {

    String getLevelName();

    PlayerCharacter getPlayerCharacter();

    @Deprecated
    GameObject[][][] getLevelData();

    List<GameObject> getGameObjects();

    @Deprecated
    boolean isOccupied(Coords coords);

    void setPlayer(PlayerCharacter player);

    void movePlayer(Direction dir);

    void doGameTick();

    @Override
    String toString();

    @Override
    boolean equals(Object obj);

}
