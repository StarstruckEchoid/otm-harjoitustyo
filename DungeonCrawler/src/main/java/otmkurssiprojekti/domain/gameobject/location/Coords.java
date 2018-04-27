/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.location;

/**
 *
 * @author gjuho
 */
public class Coords extends FlatCoords {

    private int z;

    public Coords() {
        super(0, 0);
        this.z = 0;
    }

    public Coords(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public void add(Coords c) {
        super.add(c);
        this.z += c.z;
    }

    @Override
    public void multiply(int s) {
        super.multiply(s);
        this.z *= s;
    }

    //Sum is like add, but modifies neither this nor c.
    public Coords sum(Coords c) {
        int nx = this.x + c.x;
        int ny = this.y + c.y;
        int nz = this.z + c.z;
        return new Coords(nx, ny, nz);
    }

    //Distance functions
    public int squaredEuclideanDistance(Coords coords) {
        int dzSq = this.z - coords.z;
        dzSq *= dzSq;
        return super.squaredEuclideanDistance(coords) + dzSq;
    }

    public int manhattanDistance(Coords coords) {
        int dz = Math.abs(this.z - coords.z);
        return super.manhattanDistance(coords) + dz;
    }

    //BOOLEANS
    //This is true if all coords less than c. Good for analysing whether the coords are within a set box boundary.
    public Boolean lesserThan(Coords c) {
        return super.lesserThan(c) && this.z < c.z;
    }

    //Similar, but in reverse.
    public Boolean greaterThanOrEqualTo(Coords c) {
        return super.greaterThanOrEqualTo(c) && this.z >= c.z;
    }

    //Boring function:
    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 29 * hash + this.z;
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
        final Coords other = (Coords) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return this.z == other.z;
    }

    @Override
    public int compareTo(FlatCoords o) {
        if (o instanceof Coords) {
            return this.compareTo((Coords) o);
        } else {
            return super.compareTo(o);
        }
    }

    public int compareTo(Coords o) {
        int dz = this.z - o.z;
        if (dz != 0) {
            return dz;
        }
        return super.compareTo(o);
    }

    public FlatCoords toFlatCoords() {
        return new FlatCoords(x, y);
    }

}
