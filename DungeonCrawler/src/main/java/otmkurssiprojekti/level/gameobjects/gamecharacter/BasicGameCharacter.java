/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects.gamecharacter;

import otmkurssiprojekti.level.gameobjects.BasicMobileObject;
import otmkurssiprojekti.level.gameobjects.interfaces.Destructible;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;
import otmkurssiprojekti.level.gameobjects.interfaces.Mobile;

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
