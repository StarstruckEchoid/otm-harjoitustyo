/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.level;

import java.io.Serializable;
import java.util.List;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.domain.gameobject.interfaces.GameObject;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
public interface GameLevel extends Serializable {

    String getLevelName();

    BasicPlayerCharacter getPlayer();

    List<GameObject> getGameObjects();

    void setPlayer(BasicPlayerCharacter player);

    void movePlayer(Direction dir);

    void playerAttack(Direction dir);

    /**
     * doGameTick performs actions in the game level.
     *
     * @return If at the end of these actions the player character is dead,
     * return true. Else return false.
     */
    boolean doGameTick();

    boolean isInaccessible(Coords coords);

    @Override
    String toString();

    @Override
    public boolean equals(Object obj);

}
