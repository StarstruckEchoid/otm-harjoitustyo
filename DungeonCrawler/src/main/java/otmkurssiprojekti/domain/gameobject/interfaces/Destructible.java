/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.interfaces;

/**
 *
 * @author Juho Gröhn
 */
public interface Destructible extends GameObject {

    /**
     * The response of this Destructible upon taking damage.
     *
     * @param dmg How much damage this Destructible will take.
     */
    public void takeDamage(int dmg);

    /**
     * React to taking damage from a Hurtful object.
     *
     * @param ho The hurtful in question.
     * @see Hurtful
     */
    public void takeDamage(Hurtful ho);

    /**
     *
     * @return Returns whether the Destructible is 'dead' or not. Usually this
     * is equivalent to health being below zero.
     */
    public boolean isDead();
}
