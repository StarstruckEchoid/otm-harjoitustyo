/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.location;

/**
 *
 * @author gjuho
 */
public enum Direction implements java.io.Serializable {
    UP(new Coords(0, 1, 0), "UP"),
    DOWN(new Coords(0, -1, 0), "DOWN"),
    LEFT(new Coords(-1, 0, 0), "LEFT"),
    RIGHT(new Coords(1, 0, 0), "RIGHT"),
    IN(new Coords(0, 0, -1), "IN"),
    OUT(new Coords(0, 0, 1), "OUT");

    private final Coords coords;
    private final String name;

    private Direction(Coords coords, String name) {
        this.coords = coords;
        this.name = name;
    }

    public Coords getCoords() {
        return coords;
    }

    public String getName() {
        return name;
    }

}
