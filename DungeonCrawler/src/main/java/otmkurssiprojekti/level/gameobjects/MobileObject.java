/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects;

import otmkurssiprojekti.level.gameobjects.archetypes.Behaviour;
import otmkurssiprojekti.level.gameobjects.location.Coords;

/**
 *
 * @author Juho Gröhn
 */
public interface MobileObject extends GameObject {

    Behaviour getBehaviour();

    int getSlowness();

    public void setCoords(Coords coords);

    public void doDamage(GameCharacter gc);

}
