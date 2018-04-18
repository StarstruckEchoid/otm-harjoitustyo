/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects;

import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;

/**
 *
 * @author gjuho
 */
public interface GameObject extends java.io.Serializable {

    public char getId();
    public boolean isTransparent();    
    public boolean isSolid();
    public Coords getCoords();
    public Direction getDirection();
    public void move(Direction dir);
    public void turn(Direction dir);
}
