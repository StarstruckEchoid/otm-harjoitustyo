/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.interfaces.derivatives;

import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.interfaces.Destructible;
import otmkurssiprojekti.domain.gameobject.interfaces.Hurtful;
import otmkurssiprojekti.domain.gameobject.interfaces.Mobile;
import otmkurssiprojekti.domain.gameobject.interfaces.PointsSource;
import otmkurssiprojekti.domain.gameobject.interfaces.StatsObject;

/**
 *
 * @author gjuho
 */
public interface PlayerCharacter extends Mobile, Destructible, Hurtful, PointsSource, StatsObject {

    /**
     * Attempt to collect a PointsBall.
     *
     * @param pb The pointsBall to be collected.
     * @return If collection was successful, return true. Else return false.
     */
    public boolean collectPoint(PointsBall pb);
}
