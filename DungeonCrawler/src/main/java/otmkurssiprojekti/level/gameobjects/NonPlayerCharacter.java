/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects;

import java.util.Objects;
import otmkurssiprojekti.level.gameobjects.archetypes.Behaviour;
import otmkurssiprojekti.level.gameobjects.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class NonPlayerCharacter extends BasicStatsCharacter implements PointsSource, StatsCharacter {

    private Behaviour behaviour;
    private final char id;
    private final int lvl;

    public NonPlayerCharacter(Behaviour behaviour, char id, int lvl, int hp, int str, int per, int end, int agl, Coords coords, Direction direction) {
        super(hp, str, per, end, agl, coords, direction);
        this.behaviour = behaviour;
        this.id = id;
        this.lvl = lvl;
    }

    public NonPlayerCharacter(NonPlayerCharacterArchetype npca, Coords coords, Direction direction) {
        super(
                npca.getHp(),
                npca.getStr(),
                npca.getEnd(),
                npca.getEnd(),
                npca.getAgl(),
                coords,
                direction
        );
        this.behaviour = npca.getBehaviour();
        this.id = npca.getId();
        this.lvl = npca.getLevel();
    }

    @Override
    public Behaviour getBehaviour() {
        return behaviour;
    }

    //Setters
    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.behaviour);
        hash = 41 * hash + this.id;
        hash = 41 * hash + this.lvl;
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
        final NonPlayerCharacter other = (NonPlayerCharacter) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.lvl != other.lvl) {
            return false;
        }
        if (this.behaviour != other.behaviour) {
            return false;
        }
        return true;
    }
    
   

}
