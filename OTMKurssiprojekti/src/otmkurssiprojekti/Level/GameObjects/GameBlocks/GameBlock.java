/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects.GameBlocks;

import otmkurssiprojekti.Level.GameObjects.TopDownObject;

/**
 *
 * @author Juho Gröhn
 */
public abstract class GameBlock extends TopDownObject {

    private char id;

    @Override
    public char getId() {
        return id;
    }

    @Override
    public Boolean isDead() {
        return false;
    }

    @Override
    public int getAttackStrength() {
        return 0;
    }

    //Most blocks do nothing upon touching or interaction.
    @Override
    public abstract void touch(TopDownObject t);

    @Override
    public abstract void interact(TopDownObject t);

    //Blocks do not take damage.
    @Override
    public void takeDamage(int dmg) {
    }

}
