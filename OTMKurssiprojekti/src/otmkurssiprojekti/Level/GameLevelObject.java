/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level;

import otmkurssiprojekti.Level.GameObjects.GameObject;

/**
 *
 * @author gjuho
 * @param <T>
 */
public class GameLevelObject<T extends GameObject> {

    private final T topDownObject;
    private final Coords coords;
    private Direction direction;

    public GameLevelObject() {
        this.topDownObject = null;
        this.coords = new Coords();
        this.direction = Direction.DOWN;
    }

    public GameLevelObject(T object, Coords coords, Direction direction) {
        this.topDownObject = object;
        this.coords = coords;
        this.direction = direction;
    }

    protected void move(Direction dir) {
        coords.add(dir.getCoords());
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Coords getCoords() {
        return coords;
    }

    public Direction getDirection() {
        return direction;
    }

    public T getTopDownObject() {
        return topDownObject;
    }

    @Override
    public String toString() {
        return topDownObject.toString();
    }

}
