/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.interfaces;

import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author gjuho
 */
public interface GameObject extends java.io.Serializable {

    /**
     * Returns the char associated with this GameObject. Can function as a means
     * to tell types of GameObjects apart in a memory efficient way.
     *
     * @return
     */
    public char getId();

    /**
     * Returns whether this GameObject is transparent or not. Some rendering
     * classes may draw GameObjects that are under transparent GameObjects.
     *
     * @return
     */
    public boolean isTransparent();

    /**
     * Returns whether this GameObject is solid or not. Most GameObjects can not
     * move through solid objects.
     *
     * @return
     */
    public boolean isSolid();

    /**
     * Return the coordinates of this GameObject.
     *
     * @return
     */
    public Coords getCoords();

    /**
     * Returns the direction this GameObject is facing.
     *
     * @return
     */
    public Direction getDirection();
}
