/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.interfaces;

/**
 *
 * @author gjuho
 */
public interface Hurtful {

    /**
     * Gives the damage this Hurtful will do.
     *
     * @return The damage as an integer.
     */
    public int hurt();

    /**
     * Deals damage to a destructible object.
     *
     * @param d The destructible to be hurt.
     */
    public void hurt(Destructible d);
}
