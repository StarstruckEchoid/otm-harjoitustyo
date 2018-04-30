/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter;

import otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.gamecharacter.BasicStatsCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public abstract class BasicNonPlayerCharacter extends BasicStatsCharacter implements NonPlayerCharacter {

    private final char id;
    private final int lvl;

    public BasicNonPlayerCharacter(char id, int lvl, int hp, int str, int per, int end, int agl, Coords coords, Direction direction) {
        super(hp, str, per, end, agl, coords, direction);
        this.id = id;
        this.lvl = lvl;
    }

    public BasicNonPlayerCharacter(NonPlayerCharacterArchetype npca, Coords coords, Direction direction) {
        super(
                npca.getHp(),
                npca.getStr(),
                npca.getEnd(),
                npca.getEnd(),
                npca.getAgl(),
                coords,
                direction
        );
        this.id = npca.getId();
        this.lvl = npca.getLevel();
    }

    @Override
    public int getPoints() {
        //The points given by an NPC on death is the level of the NPC.
        return lvl;
    }

    @Override
    public char getId() {
        return id;
    }

}
