/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects.location;

/**
 *
 * @author Juho Gröhn
 */
public class FlatCoords implements Comparable<FlatCoords>, java.io.Serializable {

    protected int x;
    protected int y;

    public FlatCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void add(FlatCoords c) {
        this.x += c.x;
        this.y += c.y;
    }

    public void multiply(int s) {
        this.x *= s;
        this.y *= s;
    }

    //Sum is like add, but modifies neither this nor c.
    public FlatCoords sum(FlatCoords c) {
        int x = this.x + c.x;
        int y = this.y + c.y;
        return new FlatCoords(x, y);
    }

    //Distance functions
    public int squaredEuclideanDistance(FlatCoords coords) {
        int dxSq = this.x - coords.x;
        dxSq *= dxSq;
        int dySq = this.y - coords.y;
        dySq *= dySq;

        return dxSq + dySq;
    }

    public int manhattanDistance(FlatCoords coords) {
        int dx = Math.abs(this.x - coords.x);
        int dy = Math.abs(this.y - coords.y);

        return dx + dy;
    }

    //BOOLEANS
    //This is true if all coords less than c. Good for analysing whether the coords are within a set box boundary.
    public Boolean lesserThan(FlatCoords c) {
        return this.x < c.x && this.y < c.y;
    }

    //Similar, but in reverse.
    public Boolean greaterThanOrEqualTo(FlatCoords c) {
        return this.x >= c.x && this.y >= c.y;
    }

    //Boring function:
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.x;
        hash = 29 * hash + this.y;
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
        final FlatCoords other = (FlatCoords) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }

    @Override
    public int compareTo(FlatCoords o) {
        int dy = this.y - o.y;
        if (dy != 0) {
            return dy;
        }
        int dx = this.x - o.x;
        return dx;
    }

    public Coords toCoords() {
        return new Coords(x, y, 0);
    }

}
