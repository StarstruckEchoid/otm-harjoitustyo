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
 * @author Juho Gröhn
 */
public interface Mobile extends GameObject {

    /**
     * Moves Mobile to these coordinates.
     *
     * @param c The coords in which to move.
     */
    public void move(Coords c);

    /**
     * Moves Mobile one block in this direction.
     *
     * @param d The direction in which to move. Typically by one block.
     */
    public void move(Direction d);

    /**
     * Makes Mobile face this direction.
     *
     * @param d The direction in which to turn.
     */
    public void turn(Direction d);
}
