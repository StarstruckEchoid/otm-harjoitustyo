/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.level;

import java.io.Serializable;
import java.util.List;
import otmkurssiprojekti.domain.gameobject.gameinanimates.ImmutableObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LinkObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.domain.gameobject.interfaces.GameObject;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
public interface GameLevel extends Serializable {

    /**
     * Returns the name of this level. NB! not necessarily the same as
     * toString().
     *
     * @return Name of the level.
     */
    String getLevelName();

    /**
     *
     * @return The player in this level.
     */
    PlayerCharacter getPlayer();

    /**
     *
     * @return The list of all GameObjects in the entire level.
     */
    List<GameObject> getGameObjects();

    /**
     * Sets player.
     *
     * @param player The player to overwrite current player value.
     */
    void setPlayer(PlayerCharacter player);

    /**
     * Moves the player in the given direction by one block.
     *
     * @param dir The direction in which to move.
     */
    void movePlayer(Direction dir);

    /**
     * Makes the player attack in a given direction, dealing damage to any
     * destructible in that direction.
     *
     * @param dir The direction in which the player will attack.
     */
    void playerAttack(Direction dir);

    /**
     * doGameTick performs actions in the game level.
     *
     * @return If at the end of these actions the player character is dead,
     * return true. Else return false.
     */
    boolean doGameTick();

    /**
     * Tells whether it's possible to move to these coordinates.
     *
     * @param coords The coordinates to be checked.
     * @return Return true if it's NOT possible to go here and false if it is
     * possible.
     */
    boolean isInaccessible(Coords coords);

    @Override
    String toString();

    @Override
    public boolean equals(Object obj);

    public List<NonPlayerCharacter> getNpcs();

    public List<ImmutableObject> getBlocks();

    public List<InteractiveObject> getInteractives();

    public List<LinkObject> getLevelLinks();

    public List<PointsBall> getPoints();

}
