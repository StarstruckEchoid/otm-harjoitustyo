/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects;

import java.util.Random;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public abstract class BasicStatsCharacter extends BasicGameCharacter implements StatsCharacter {

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

    @Override
    public int getAttackDamage() {
        return str;
    }

    @Override
    public int getCriticalChance() {
        return per;
    }

    @Override
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
    public void doDamage(GameCharacter gc) {
        if (gc instanceof StatsCharacter) {
            gc.takeDamage((StatsCharacter) this);
        }
    }

    @Override
    public void takeDamage(StatsCharacter sc) {
        int baseDam = sc.getAttackDamage();

        //Real Damage is base damage after damage threshold.
        int realDam = baseDam - end;
        if (realDam > 0) {
            hp -= realDam;
        }
        //Critical damage is the entire base damage, but occurs randomly and only when opponent perception is higher than player agility.
        int critChance = sc.getCriticalChance();
        critChance -= agl;
        int random = new Random().nextInt(100);
        if (random < critChance) {
            hp -= baseDam;
        }
    }
}
