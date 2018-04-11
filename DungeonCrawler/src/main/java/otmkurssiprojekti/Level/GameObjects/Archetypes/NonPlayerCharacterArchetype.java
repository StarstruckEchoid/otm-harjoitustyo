/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects.Archetypes;

/**
 *
 * @author Juho Gröhn
 */
public enum NonPlayerCharacterArchetype implements Archetype {
    VILLAGER('%', 0, 10, 0, 0, 0, 0, Behaviour.PASSIVE),
    RAT('r', 1, 3, 1, 0, 0, 1, Behaviour.HUNT);

    private final char id;
    private final int level;
    private final int hp;
    private final int str;
    private final int per;
    private final int end;
    private final int agl;
    private final Behaviour behaviour;

    private NonPlayerCharacterArchetype(char id, int level, int hp, int str, int per, int end, int agl, Behaviour behaviour) {
        this.id = id;
        this.level = level;
        this.hp = hp;
        this.str = str;
        this.per = per;
        this.end = end;
        this.agl = agl;
        this.behaviour = behaviour;
    }

    @Override
    public char getId() {
        return id;
    }

    public int getLevel() {
        return level;
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

    public Behaviour getBehaviour() {
        return behaviour;
    }

}
