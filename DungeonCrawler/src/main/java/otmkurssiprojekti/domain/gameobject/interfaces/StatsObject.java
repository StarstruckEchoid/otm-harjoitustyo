/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.interfaces;

/**
 * A StatsObject is an object with statistics: namely health points, strength,
 * perception, endurance, and agility.
 *
 * @author Juho Gröhn
 */
public interface StatsObject extends GameObject {

    public int getHp();

    public int getStr();

    public int getPer();

    public int getEnd();

    public int getAgl();

}
