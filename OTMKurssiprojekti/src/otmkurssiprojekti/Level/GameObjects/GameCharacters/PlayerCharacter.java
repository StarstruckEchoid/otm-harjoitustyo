/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects.GameCharacters;

import otmkurssiprojekti.Level.GameObjects.GameObject;

/**
 *
 * @author Juho Gröhn
 */
public class PlayerCharacter extends GameCharacter {

    public PlayerCharacter(int hp, int str, int end) {
        super(hp, str, end);
    }

    @Override
    public char getId() {
        return '@';
    }

    @Override
    public void touch(GameObject t) {
        //By default, player doesn't do anything to TDOs it touches, eg. it doesn't hurt them or interact with them.
    }

    @Override
    public void interact(GameObject t) {
        //Others can not interact with the player.
    }
    
    
}
