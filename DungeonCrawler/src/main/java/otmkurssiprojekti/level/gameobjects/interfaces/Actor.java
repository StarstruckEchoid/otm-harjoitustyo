/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects.interfaces;

import otmkurssiprojekti.level.GameLevel;

/**
 *
 * @author gjuho
 */
public interface Actor extends GameObject {

    public void act(GameLevel gameLevel);
}
