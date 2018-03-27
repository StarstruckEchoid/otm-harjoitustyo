/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level;

/**
 *
 * @author gjuho
 */
class Coords {

    int x;
    int y;
    int z;

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

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }

}
