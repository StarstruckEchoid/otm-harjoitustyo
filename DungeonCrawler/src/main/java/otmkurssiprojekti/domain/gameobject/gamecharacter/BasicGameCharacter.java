/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gamecharacter;

import otmkurssiprojekti.domain.gameobject.gameinanimates.BasicMobileObject;
import otmkurssiprojekti.domain.gameobject.interfaces.Destructible;
import otmkurssiprojekti.domain.gameobject.interfaces.Mobile;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author gjuho
 */
public abstract class BasicGameCharacter extends BasicMobileObject implements Mobile, Destructible {

    private static final boolean TRANSPARENT = true;
    private static final boolean SOLID = false;

    public BasicGameCharacter(Coords coords, Direction direction) {
        super(coords, direction);
    }

    @Override
    public boolean isTransparent() {
        return TRANSPARENT;
    }

    @Override
    public boolean isSolid() {
        return SOLID;
    }

}
