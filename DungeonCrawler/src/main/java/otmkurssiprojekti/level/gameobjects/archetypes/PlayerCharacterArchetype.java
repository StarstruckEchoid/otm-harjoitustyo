/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects.archetypes;

/**
 *
 * @author Juho Gröhn
 */
public enum PlayerCharacterArchetype {
    SOLDIER("Soldier", 2, 2, 2, 2),
    WARRIOR("Warrior", 5, 1, 2, 0),
    THIEF("Thief", 1, 3, 0, 4),
    ASSASSIN("Assassin", 4, 4, 0, 0);

    private final String name;
    private final int str;
    private final int per;
    private final int end;
    private final int agl;

    private PlayerCharacterArchetype(String name, int str, int per, int end, int agl) {
        this.name = name;
        this.str = str;
        this.per = per;
        this.end = end;
        this.agl = agl;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return 10 * end;
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
        return this.getName();
    }

}
