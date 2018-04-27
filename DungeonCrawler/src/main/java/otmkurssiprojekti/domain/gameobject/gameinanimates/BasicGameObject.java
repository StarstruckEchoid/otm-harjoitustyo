/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gameinanimates;

import otmkurssiprojekti.domain.gameobject.interfaces.GameObject;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author gjuho
 */
public abstract class BasicGameObject implements GameObject {

    protected Coords coords;
    protected Direction direction;

    public BasicGameObject(Coords coords, Direction direction) {
        this.coords = coords;
        this.direction = direction;
    }

    @Override
    public Coords getCoords() {
        return coords;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

}
