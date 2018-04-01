/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects;

import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class ImmutableObject implements GameObject {

    private final char id;
    private final boolean transparent;
    private final boolean solid;
    private final Coords coords;
    private final Direction direction;

    public ImmutableObject(char id, boolean transparent, boolean solid, Coords coords, Direction direction) {
        this.id = id;
        this.transparent = transparent;
        this.solid = solid;
        this.coords = coords;
        this.direction = direction;
    }

    @Override
    public char getId() {
        return id;
    }

    @Override
    public boolean isTransparent() {
        return transparent;
    }

    @Override
    public boolean isSolid() {
        return solid;
    }

    @Override
    public Coords getCoords() {
        return coords;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void move(Direction dir) {
        //Immutable objects never move.
    }

    @Override
    public void turn(Direction dir) {
        //Immutable objects never turn.
    }

}
