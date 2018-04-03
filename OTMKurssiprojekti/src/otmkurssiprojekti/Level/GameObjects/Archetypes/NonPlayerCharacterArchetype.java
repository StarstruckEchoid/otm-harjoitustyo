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
    VILLAGER('%', false, 0, 0, 0, Behaviour.PASSIVE, 10);

    private final char id;
    private final boolean essential;
    private final int level;
    private final int str;
    private final int end;
    private final Behaviour behaviour;
    private final int hp;

    private NonPlayerCharacterArchetype(char id, boolean essential, int level, int str, int end, Behaviour behaviour, int hp) {
        this.id = id;
        this.essential = essential;
        this.level = level;
        this.str = str;
        this.end = end;
        this.behaviour = behaviour;
        this.hp = hp;
    }

    @Override
    public char getId() {
        return id;
    }

    public boolean isEssential() {
        return essential;
    }

    public int getLevel() {
        return level;
    }

    public int getStr() {
        return str;
    }

    public int getEnd() {
        return end;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public int getHp() {
        return hp;
    }

}
