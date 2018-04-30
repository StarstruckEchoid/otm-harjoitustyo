/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gamecharacter;

import otmkurssiprojekti.domain.gameobject.interfaces.Destructible;
import java.util.Random;
import otmkurssiprojekti.domain.gameobject.interfaces.GameObject;
import otmkurssiprojekti.domain.gameobject.interfaces.Hurtful;
import otmkurssiprojekti.domain.gameobject.interfaces.PointsSource;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 * A BasicStatsCharacter is a fancier version of a BasicGameCharacter. A
 * BasicStatsCharacter has, as the name suggests, attributes such as strength,
 * perception, endurance and agility, which affect combat.
 *
 * @author Juho Gröhn
 */
public abstract class BasicStatsCharacter extends BasicGameCharacter implements GameObject, Destructible, Hurtful, PointsSource {

    protected int hp;
    protected int str;
    protected int per;
    protected int end;
    protected int agl;

    /**
     *
     * @param hp The health points of a statsCharacter. Typically greater than
     * zero.
     * @param str Strength. Defines the damage dealt by an attack.
     * @param per Perception. In some implementations this increases chance of
     * scoring extra damage.
     * @param end Endurance. Defines how tough a statsCharacter is to kill.
     * @param agl Agility. In some implementations allows the statsCharacter to
     * avoid taking damage.
     * @param coords Coords. Where the statsCharacter is.
     * @param direction Direction. Where it is facing.
     */
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

    public int getHp() {
        return hp;
    }

    public int getStr() {
        return str;
    }

    public int getPer() {
        return per;
    }

    public int getEnd() {
        return end;
    }

    public int getAgl() {
        return agl;
    }

    @Override
    public void takeDamage(int dmg) {
        hp -= dmg;
    }

    @Override
    public void takeDamage(Hurtful ho) {
        int dmg = ho.hurt();
        //A StatsCharacter dodges, on average, half of their agility worth of damage on every blow.
        if (agl > 0) {
            int dodged = new Random().nextInt(this.agl);
            dmg -= dodged;
        }
        if (dmg > 0) {
            hp -= dmg;
        }
    }

    @Override
    public int hurt() {
        int dmg = str;
        //High perception increases damage dealt.
        if (per > 0) {
            int critBonus = new Random().nextInt(this.per);
            dmg += critBonus;
        }
        return dmg;
    }

    @Override
    public void hurt(Destructible d) {
        d.takeDamage(this);
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

    @Override
    public String toString() {
        return this.getId() + ": HP: " + this.getHp();
    }

    @Override
    public int getPoints() {
        return agl + end + per + str;
    }

}
