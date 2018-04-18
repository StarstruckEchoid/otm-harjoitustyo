/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects;

import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class LinkObject implements GameObject {

    private final char id;
    private static final boolean TRANSPARENT = true;
    private static final boolean SOLID = false;
    private final Coords coords;
    private static final Direction DIRECTION = Direction.UP;

    private final String linkAddress;

    public LinkObject(char id, Coords coords, String linkAddress) {
        this.id = id;
        this.coords = coords;
        this.linkAddress = linkAddress;
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

    //Special method.
    public String getLinkAddress() {
        return linkAddress;
    }

    @Override
    public void move(Direction dir) {
        //Level links do not move.
    }

    @Override
    public void turn(Direction dir) {
        //Level links do not turn.
    }

}
