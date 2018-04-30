/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.interfaces;

import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 * The most fundamental building block of a GameLevel. Everything in a GameLevel
 * except the name is a GameObject of some kind.
 *
 * @author gjuho
 */
public interface GameObject extends java.io.Serializable {

    /**
     * Returns the char associated with this GameObject. Can function as a means
     * to tell types of GameObjects apart in a memory efficient way.
     *
     * @return The identity as a single character.
     */
    public char getId();

    /**
     * Returns whether this GameObject is transparent or not. Some rendering
     * classes may draw GameObjects that are under transparent GameObjects.
     *
     * @return Returns transparency. True for transparent; false for opaque.
     */
    public boolean isTransparent();

    /**
     * Returns whether this GameObject is solid or not. Most GameObjects can not
     * move through solid objects.
     *
     * @return Returns solidity. True for solid.
     */
    public boolean isSolid();

    /**
     * Return the coordinates of this GameObject.
     *
     * @return Returns the coordinates of this object.
     */
    public Coords getCoords();

    /**
     * Returns the direction this GameObject is facing.
     *
     * @return Returns the direction this object is facing.
     */
    public Direction getDirection();
}
