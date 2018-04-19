/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects;

import otmkurssiprojekti.level.gameobjects.interfaces.PointsSource;
import otmkurssiprojekti.level.gameobjects.interfaces.GameObject;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class PointsBall implements GameObject, PointsSource {

    public final char id;
    public static final boolean TRANSPARENT = true; //Points objects don't take an entire block.
    public static final boolean SOLID = false; //Points objects can be walked on.
    public final Coords coords;
    public static final Direction DIRECTION = Direction.UP; //All points objects point up. This is a stylistic choice.
    public final int points;

    public PointsBall(char id, Coords coords, int points) {
        this.id = id;
        this.coords = coords;
        this.points = points;
    }

    @Override
    public char getId() {
        return id;
    }

    @Override
    public boolean isTransparent() {
        return TRANSPARENT;
    }

    @Override
    public boolean isSolid() {
        return SOLID;
    }

    @Override
    public Coords getCoords() {
        return coords;
    }

    @Override
    public Direction getDirection() {
        return DIRECTION;
    }

    @Override
    public int getPoints() {
        return points;
    }

}
