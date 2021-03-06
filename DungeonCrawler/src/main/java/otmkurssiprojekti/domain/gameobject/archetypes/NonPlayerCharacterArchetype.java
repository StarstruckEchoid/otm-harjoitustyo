/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.archetypes;

import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.StatsCharacter;

/**
 * Archetypes for NonPlayerCharacters. Include the id and all statistics.
 *
 * @author Juho Gröhn
 * @see StatsCharacter
 */
public enum NonPlayerCharacterArchetype {
    //(id,lvl,hp,str,per,end,agl,behaviour)
    VILLAGER('%', 1, 10, 0, 0, 0, 10),
    RAT('r', 1, 3, 1, 0, 0, 20),
    FOLLOWER('f', 0, 10, 0, 0, 0, 80),
    DEER('d', 5, 10, 0, 0, 0, 30),
    BEE('b', 1, 1, 0, 0, 0, 90);

    private final char id;

    private final int level;
    private final int hp;

    private final int str;
    private final int per;
    private final int end;
    private final int agl;

    private NonPlayerCharacterArchetype(char id, int level, int hp, int str, int per, int end, int agl) {
        this.id = id;
        this.level = level;
        this.hp = hp;
        this.str = str;
        this.per = per;
        this.end = end;
        this.agl = agl;
    }

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

    @Override
    public String toString() {
        return Character.toString(getId());
    }

}
