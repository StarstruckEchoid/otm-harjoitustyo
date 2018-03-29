/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects.GameBlocks;

import otmkurssiprojekti.Level.GameObjects.GameObject;

/**
 *
 * @author Juho Gröhn
 */
public abstract class GameBlock extends GameObject {

    private final char id;

    public GameBlock(char id) {
        this.id = id;
    }

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
    public abstract void touch(GameObject t);

    @Override
    public abstract void interact(GameObject t);

    //Blocks do not take damage.
    @Override
    public void takeDamage(int dmg) {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameBlock other = (GameBlock) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
