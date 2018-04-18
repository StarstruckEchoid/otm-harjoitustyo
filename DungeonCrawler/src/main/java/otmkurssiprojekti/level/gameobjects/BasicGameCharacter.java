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
public abstract class BasicGameCharacter implements GameCharacter {

    private Coords coords;
    private Direction direction;

    public BasicGameCharacter(Coords coords, Direction direction) {
        this.coords = coords;
        this.direction = direction;
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public Coords getCoords() {
        return this.coords;
    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    @Override
    public void move(Direction dir) {
        this.coords.add(dir.getCoords());
    }

    @Override
    public void turn(Direction dir) {
        this.direction = dir;
    }

}
