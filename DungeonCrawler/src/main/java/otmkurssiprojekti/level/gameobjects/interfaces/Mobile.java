/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects.interfaces;

import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public interface Mobile extends GameObject {

    /**
     * Moves Mobile to these coordinates.
     *
     * @param c
     */
    public void move(Coords c);

    /**
     * Moves Mobile one block in this direction.
     *
     * @param d
     */
    public void move(Direction d);

    /**
     * Makes Mobile face this direction.
     *
     * @param d
     */
    public void turn(Direction d);
}
