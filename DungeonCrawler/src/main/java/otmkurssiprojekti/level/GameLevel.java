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
import otmkurssiprojekti.level.gameobjects.interfaces.Hurtful;

/**
 *
 * @author Juho Gröhn
 */
public interface GameLevel extends Serializable {

    String getLevelName();

    PlayerCharacter getPlayerCharacter();

    List<GameObject> getGameObjects();

    void setPlayer(PlayerCharacter player);

    void movePlayer(Direction dir);

    void playerAttack(Direction dir);

    /**
     * doGameTick performs actions in the game level. If at the end of these
     * actions the player character is dead, return true. Else return false.
     *
     * @return
     */
    boolean doGameTick();

    boolean isInaccessible(Coords coords);

    @Override
    String toString();

    @Override
    boolean equals(Object obj);

}
