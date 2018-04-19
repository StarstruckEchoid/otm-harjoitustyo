/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects.interfaces;

/**
 *
 * @author Juho Gröhn
 */
public interface Destructible extends GameObject {

    /**
     * The response of this Destructible upon taking damage.
     *
     * @param dmg
     */
    public void takeDamage(int dmg);

    public void takeDamage(Hurtful ho);

    public boolean isDead();
}
