/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gameinanimates;

import java.util.Objects;
import otmkurssiprojekti.domain.gameobject.interfaces.GameObject;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import static otmkurssiprojekti.domain.gameobject.location.Direction.UP;

/**
 *
 * @author Juho Gröhn
 */
public class LevelLink implements GameObject {

    private final char id;
    public static final boolean TRANSPARENT = true;
    public static final boolean SOLID = false;
    private final Coords coords;
    public static final Direction DIRECTION = UP;

    private final String linkAddress;

    public LevelLink(char id, Coords coords, String linkAddress) {
        this.id = id;
        this.coords = coords;
        this.linkAddress = linkAddress;
    }

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
        return DIRECTION;
    }

    //Special method.
    public String getLinkAddress() {
        return linkAddress;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.coords);
        hash = 89 * hash + Objects.hashCode(this.linkAddress);
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
        final LevelLink other = (LevelLink) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.linkAddress, other.linkAddress)) {
            return false;
        }
        return Objects.equals(this.coords, other.coords);
    }

    @Override
    public String toString() {
        return this.id + ": " + this.coords.toString() + " -> " + this.linkAddress;
    }

}
