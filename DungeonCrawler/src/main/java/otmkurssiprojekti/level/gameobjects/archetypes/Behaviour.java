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
@Deprecated
public enum Behaviour {
    PASSIVE("PASSIVE"),
    FOLLOW("FOLLOW"),
    HUNT("HUNT"),
    FLEE("FLEE"),
    PATROL("PATROL");

    private final String name;

    private Behaviour(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
