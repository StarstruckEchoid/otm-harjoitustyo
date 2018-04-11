/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects;

import java.util.Objects;
import java.util.Random;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class PlayerCharacter implements StatsCharacter {

    private static final char ID = '@';
    private static final boolean TRANSPARENT = true;
    private static final boolean SOLID = false;
    private final Coords coords;

    private int hp;

    private int str;
    private int per;
    private int end;
    private int agl;

    private Direction direction;

    public PlayerCharacter(Coords coords) {
        this.coords = coords;
    }

    public PlayerCharacter(Coords coords, int hp, int str, int per, int end, int agl, Direction direction) {
        this.coords = coords;
        this.hp = hp;
        this.str = str;
        this.per = per;
        this.end = end;
        this.agl = agl;
        this.direction = direction;
    }

    @Override
    public void takeDamage(int dmg) {
        //takeDamage with int variable can not be blocked.
        hp -= dmg;
    }

    @Override
    public boolean isDead() {
        return hp <= 0;
    }

    @Override
    public char getId() {
        return ID;
    }

    @Override
    public boolean isTransparent() {
        return TRANSPARENT;
    }

    @Override
    public boolean isSolid() {
        return SOLID;
    }

    @Override
    public Coords getCoords() {
        return coords;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void move(Direction dir) {
        this.coords.add(dir.getCoords());
    }

    @Override
    public void turn(Direction direction) {
        this.direction = direction;
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

    @Override
    public int getAttackDamage() {
        return str;
    }

    @Override
    public int getCriticalChance() {
        return per;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.coords);
        hash = 59 * hash + this.str;
        hash = 59 * hash + this.end;
        hash = 59 * hash + Objects.hashCode(this.direction);
        hash = 59 * hash + this.hp;
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
        final PlayerCharacter other = (PlayerCharacter) obj;
        if (this.str != other.str) {
            return false;
        }
        if (this.end != other.end) {
            return false;
        }
        if (this.hp != other.hp) {
            return false;
        }
        if (!Objects.equals(this.coords, other.coords)) {
            return false;
        }
        if (this.direction != other.direction) {
            return false;
        }
        return true;
    }

}
