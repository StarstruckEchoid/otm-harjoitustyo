/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects;

/**
 *
 * @author gjuho
 */
public abstract class GameObject {

    public abstract char getId();

    public abstract Boolean isDead();

    public abstract int getAttackStrength();

    public abstract void touch(GameObject t);

    public abstract void interact(GameObject t);

    public abstract void takeDamage(int dmg);

    @Override
    public String toString() {
        return "" + this.getId();
    }

}
