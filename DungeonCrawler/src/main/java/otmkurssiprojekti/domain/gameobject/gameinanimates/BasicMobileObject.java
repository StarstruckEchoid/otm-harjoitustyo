/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gameinanimates;

import otmkurssiprojekti.domain.gameobject.interfaces.Mobile;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author gjuho
 */
public abstract class BasicMobileObject extends BasicGameObject implements Mobile {

    public BasicMobileObject(Coords coords, Direction direction) {
        super(coords, direction);
    }

    @Override
    public void move(Coords c) {
        this.coords = c;
    }

    @Override
    public void move(Direction d) {
        this.coords.add(d.getCoords());
    }

    @Override
    public void turn(Direction d) {
        this.direction = d;
    }

}
