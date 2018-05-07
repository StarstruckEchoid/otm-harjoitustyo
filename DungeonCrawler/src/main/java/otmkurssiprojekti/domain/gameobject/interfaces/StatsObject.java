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

    /**
     *
     * @return Hit points of a StatsObject.
     */
    public int getHp();

    /**
     *
     * @return Strength of a StatsObject.
     */
    public int getStr();

    /**
     *
     * @return Perception of a StatsObject.
     */
    public int getPer();

    /**
     *
     * @return Endurance of a StatsObject.
     */
    public int getEnd();

    /**
     *
     * @return Agility of a StatsObject.
     */
    public int getAgl();

}
