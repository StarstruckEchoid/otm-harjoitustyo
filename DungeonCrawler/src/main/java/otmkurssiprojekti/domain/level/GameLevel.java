/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.level;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import otmkurssiprojekti.domain.gameobject.gameinanimates.Block;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LevelLink;
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
     * Makes the player interact with whatever interactive objects are at his
     * current coordinates.
     *
     * @return If the interaction leads to a level change request, return the
     * name of the level as a string. Else return empty.
     */
    Optional<String> playerInteract();

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

    /**
     *
     * @return The NPCs in this level.
     */
    public List<NonPlayerCharacter> getNonPlayerCharacters();

    /**
     *
     * @return The immutable objects in this level.
     */
    public List<Block> getBlocks();

    /**
     *
     * @return The interactive objects, such as doors or switches, of this
     * level.
     */
    public List<InteractiveObject> getInteractiveObjects();

    /**
     *
     * @return The link objects in this level.
     */
    public List<LevelLink> getLevelLinks();

    /**
     *
     * @return The PointsBall objects in this level.
     */
    public List<PointsBall> getPointsBalls();

}
