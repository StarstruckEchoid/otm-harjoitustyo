/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects;

/**
 *
 * @author Juho Gröhn
 */
public interface GameCharacter extends GameObject {
    public void takeDamage(int dmg);
    public int getAttackStrength();
    public boolean isDead();
}
