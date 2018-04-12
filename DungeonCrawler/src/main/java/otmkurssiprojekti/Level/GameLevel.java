/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level;

import java.io.Serializable;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.Level.GameObjects.GameObject;
import otmkurssiprojekti.Level.GameObjects.PlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
public interface GameLevel extends Serializable {

    String getLevelName();
    
    PlayerCharacter getPlayerCharacter();

    GameObject[][][] getLevelData();
    
    boolean isOccupied(Coords coords);

    void setPlayer(PlayerCharacter player);

    void movePlayer(Direction dir);

    void doGameTick();

    @Override
    String toString();

    @Override
    boolean equals(Object obj);

}
