/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gameinanimates;

import java.util.Objects;
import otmkurssiprojekti.domain.gameobject.archetypes.BlockArchetype;
import otmkurssiprojekti.domain.gameobject.interfaces.GameObject;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class Block implements GameObject {

    private final char id;
    private final boolean transparent;
    private final boolean solid;
    private final Coords coords;
    private final Direction direction;

    public Block(char id, boolean transparent, boolean solid, Coords coords, Direction direction) {
        this.id = id;
        this.transparent = transparent;
        this.solid = solid;
        this.coords = coords;
        this.direction = direction;
    }

    public Block(BlockArchetype archetype, Coords coords, Direction direction) {
        this.id = archetype.getId();
        this.transparent = archetype.isTransparent();
        this.solid = archetype.isSolid();
        this.coords = coords;
        this.direction = direction;
    }

    @Override
    public char getId() {
        return id;
    }

    @Override
    public boolean isTransparent() {
        return transparent;
    }

    @Override
    public boolean isSolid() {
        return solid;
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
    public String toString() {
        return this.id + ": " + this.coords.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.coords);
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
        final Block other = (Block) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.coords, other.coords)) {
            return false;
        }
        return true;
    }

}
