/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects.Dependencies;

/**
 *
 * @author gjuho
 */
public class Coords implements java.io.Serializable, Comparable<Coords> {

    private int x;
    private int y;
    private int z;

    public Coords() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Coords(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void add(Coords c) {
        this.x += c.x;
        this.y += c.y;
        this.z += c.z;
    }

    public void multiply(int s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
    }

    //Sum is like add, but modifies neither this nor c.
    public Coords sum(Coords c) {
        int x = this.x + c.x;
        int y = this.y + c.y;
        int z = this.z + c.z;
        return new Coords(x, y, z);
    }

    //Distance functions
    public int squaredEuclideanDistance(Coords coords) {
        int dxSq = this.x - coords.x;
        dxSq *= dxSq;
        int dySq = this.y - coords.y;
        dySq *= dySq;
        int dzSq = this.z - coords.z;
        dzSq *= dzSq;

        return dxSq + dySq + dzSq;
    }

    public int manhattanDistance(Coords coords) {
        int dx = Math.abs(this.x - coords.x);
        int dy = Math.abs(this.y - coords.y);
        int dz = Math.abs(this.z - coords.z);
        
        return dx + dy + dz;
    }

    //BOOLEANS
    //This is true if all coords less than c. Good for analysing whether the coords are within a set box boundary.
    public Boolean lesserThan(Coords c) {
        return this.x < c.x
                && this.y < c.y
                && this.z < c.z;
    }

    //Similar, but in reverse.
    public Boolean greaterThanOrEqualTo(Coords c) {
        return this.x >= c.x
                && this.y >= c.y
                && this.z >= c.z;
    }

    //Boring function:
    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.x;
        hash = 29 * hash + this.y;
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
        if (this.z != other.z) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Coords o) {
        int dz = this.z - o.z;
        if (dz != 0) {
            return dz;
        }
        int dy = this.y - o.y;
        if (dy != 0) {
            return dy;
        }
        int dx = this.x - o.x;
        return dx;
    }

}
