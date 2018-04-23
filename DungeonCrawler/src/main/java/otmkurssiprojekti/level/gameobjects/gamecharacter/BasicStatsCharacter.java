/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects.gamecharacter;

import otmkurssiprojekti.level.gameobjects.interfaces.Destructible;
import java.util.Random;
import otmkurssiprojekti.level.gameobjects.interfaces.GameObject;
import otmkurssiprojekti.level.gameobjects.interfaces.Hurtful;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public abstract class BasicStatsCharacter extends BasicGameCharacter implements GameObject, Destructible, Hurtful {

    protected int hp;
    protected int str;
    protected int per;
    protected int end;
    protected int agl;

    public BasicStatsCharacter(int hp, int str, int per, int end, int agl, Coords coords, Direction direction) {
        super(coords, direction);
        this.hp = hp;
        this.str = str;
        this.per = per;
        this.end = end;
        this.agl = agl;
    }

    @Override
    public boolean isDead() {
        return hp < 0;
    }

    @Override
    public void takeDamage(int dmg) {
        hp -= dmg;
    }

    @Deprecated
    public int getAttackDamage() {
        return str;
    }

    @Deprecated
    public int getCriticalChance() {
        return per;
    }

    @Deprecated
    public int getSlowness() {
        int slowness = 100;
        slowness -= agl;
        slowness /= 10;
        if (slowness <= 0) {
            return 1;
        } else if (slowness > 10) {
            return 10;
        }
        return slowness;
    }

    @Override
    public void takeDamage(Hurtful ho) {
        int baseDam = ho.hurt();

        //Real Damage is base damage after damage threshold.
        int realDam = baseDam - end;
        if (realDam > 0) {
            hp -= realDam;
        }
        //Critical damage is the entire base damage, but occurs randomly and only when opponent perception is higher than player agility.
        int critChance = 100;
        critChance -= agl;
        int random = new Random().nextInt(100);
        if (random < critChance) {
            hp -= baseDam;
        }
    }

    @Override
    public int hurt() {
        return str;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.hp;
        hash = 83 * hash + this.str;
        hash = 83 * hash + this.per;
        hash = 83 * hash + this.end;
        hash = 83 * hash + this.agl;
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
        return this.hashCode() == obj.hashCode();
    }

}
