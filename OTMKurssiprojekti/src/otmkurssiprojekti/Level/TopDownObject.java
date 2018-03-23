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
public interface TopDownObject {
    public char getId();
    public Boolean isDead();
    public int getAttackStrength();
    
    public void touch(TopDownObject t);
    public void interact(TopDownObject t);
    public void takeDamage(int dmg);
    
}
