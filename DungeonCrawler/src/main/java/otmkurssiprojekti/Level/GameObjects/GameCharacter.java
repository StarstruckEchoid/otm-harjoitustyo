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
public interface GameCharacter extends MobileObject {

    public void takeDamage(int dmg);

    public void takeDamage(StatsCharacter gc);

    public boolean isDead();
}
