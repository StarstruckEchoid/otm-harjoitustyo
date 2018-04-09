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
public class Coords implements java.io.Serializable {

    public int x;
    public int y;
    public int z;

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

}
