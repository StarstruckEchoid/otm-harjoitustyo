/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level;

/**
 *
 * @author gjuho
 */
public enum Direction {
    UP(new Coords(0, 1, 0)),
    DOWN(new Coords(0, -1, 0)),
    LEFT(new Coords(-1, 0, 0)),
    RIGHT(new Coords(1, 0, 0)),
    IN(new Coords(0, 0, -1)),
    OUT(new Coords(0, 0, 1));

    private final Coords coords;

    private Direction(Coords coords) {
        this.coords = coords;
    }

    public Coords getCoords() {
        return coords;
    }

}
