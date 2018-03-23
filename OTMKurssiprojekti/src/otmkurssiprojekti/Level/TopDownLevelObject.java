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
public class TopDownLevelObject {
    
    private TopDownObject object;
    private Coords coords;
    private Direction direction;
    
    public void move(Direction dir) {
        coords.add(dir.getCoords());
    }
    
}
