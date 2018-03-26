/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects.GameCharacters;

import otmkurssiprojekti.Level.GameObjects.TopDownObject;

/**
 *
 * @author Juho Gröhn
 */
public abstract class GameCharacter extends TopDownObject {
    int hp;
    int str;
    int end;

    public GameCharacter(int hp, int str, int end) {
        this.hp = hp;
        this.str = str;
        this.end = end;
    }

    @Override
    public abstract char getId();

    @Override
    public Boolean isDead() {
        return hp < 0;
    }

    @Override
    public int getAttackStrength() {
        //Strength is the measure of damage.
        return str;
    }

    //Some NPCs damage any TDO that interacts with them. Others do nothing.
    @Override
    public abstract void touch(TopDownObject t);

    //Some NPCs, like traders, can be interacted with. Others, like enemies, can not.
    @Override
    public abstract void interact(TopDownObject t);

    @Override
    public void takeDamage(int dmg) {
        //Endurance reduces incoming damage and may even negate it entirely.
        dmg -= end;
        if (dmg > 0) {
            hp -= dmg;
        }
    }

}
