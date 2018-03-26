/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level;

import otmkurssiprojekti.Level.GameObjects.TopDownObject;

/**
 *
 * @author gjuho
 * @param <T>
 */
public class TopDownLevelObject<T extends TopDownObject> {
    
    private T topDownObject;
    private Coords coords;
    private Direction direction;

    public TopDownLevelObject() {
        this.topDownObject = null;
    }

    public TopDownLevelObject(T object, Coords coords, Direction direction) {
        this.topDownObject = object;
        this.coords = coords;
        this.direction = direction;
    }
    
    public void move(Direction dir) {
        coords.add(dir.getCoords());
    }

    public Coords getCoords() {
        return coords;
    }

    public T getTopDownObject() {
        return topDownObject;
    }

    @Override
    public String toString() {
        return topDownObject.toString();
    }
    
    
    
}
