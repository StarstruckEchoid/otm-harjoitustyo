/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.interfaces.derivatives;

import otmkurssiprojekti.domain.gameobject.interfaces.Destructible;
import otmkurssiprojekti.domain.gameobject.interfaces.Hurtful;
import otmkurssiprojekti.domain.gameobject.interfaces.Mobile;
import otmkurssiprojekti.domain.gameobject.interfaces.PointsSource;

/**
 *
 * @author Juho Gröhn
 */
public interface GameCharacter extends Mobile, Destructible, Hurtful, PointsSource {
    
}
