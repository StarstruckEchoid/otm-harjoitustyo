/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects.gameinanimates;

import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;
import otmkurssiprojekti.level.gameobjects.interfaces.Mobile;

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
