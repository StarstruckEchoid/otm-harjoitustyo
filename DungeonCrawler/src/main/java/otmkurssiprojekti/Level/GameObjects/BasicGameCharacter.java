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
public abstract class BasicGameCharacter implements GameCharacter {

    private Coords coords;
    private Direction direction;

    public BasicGameCharacter(Coords coords, Direction direction) {
        this.coords = coords;
        this.direction = direction;
    }

    @Override
    public abstract char getId();

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

    @Override
    public abstract void takeDamage(int dmg);

    @Override
    public abstract boolean isDead();

}
