/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects;

import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.Level.GameObjects.Archetypes.Behaviour;

/**
 *
 * @author Juho Gröhn
 */
public class NonPlayerCharacter implements GameCharacter, PointsSource {

    private final char id;
    private static final boolean TRANSPARENT = true; //All npcs are transparent as they do not fill the entire block they are standing on.
    private static final boolean SOLID = false;
    private final Coords coords;

    private final boolean essential;
    private final int lvl;
    private final int str;
    private final int end;

    private Behaviour behaviour;
    private Direction direction;
    private int hp;

    public NonPlayerCharacter(char id, Coords coords, boolean essential, int lvl, int str, int end, Behaviour behaviour, Direction direction, int hp) {
        this.id = id;
        this.coords = coords;
        this.essential = essential;
        this.lvl = lvl;
        this.str = str;
        this.end = end;
        this.behaviour = behaviour;
        this.direction = direction;
        this.hp = hp;
    }

    public NonPlayerCharacter(char id, Coords coords, boolean essential, int lvl, int str, int end) {
        this.id = id;
        this.coords = coords;
        this.essential = essential;
        this.lvl = lvl;
        this.str = str;
        this.end = end;
    }

    @Override
    public void takeDamage(int dmg) {
        //Endurance reduces the amount of damage taken.
        dmg -= end;
        if (dmg > 0) {
            hp -= dmg;
        }

    }

    @Override
    public int getAttackStrength() {
        return str;
    }

    @Override
    public boolean isDead() {
        //Essential characters do not die regardless of what their hp might be.
        if (essential) {
            return false;
        }
        return hp <= 0;
    }

    //Getters
    @Override
    public char getId() {
        return id;
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

    public Behaviour getBehaviour() {
        return behaviour;
    }

    //Setters
    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    //Others
    @Override
    public void move(Direction dir) {
        this.coords.add(dir.getCoords());
    }

    @Override
    public void turn(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int getPoints() {
        //The points given by an NPC on death is the level of the NPC.
        return lvl;
    }

}
