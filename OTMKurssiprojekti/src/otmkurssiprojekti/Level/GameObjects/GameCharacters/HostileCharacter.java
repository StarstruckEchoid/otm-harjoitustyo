/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects.GameCharacters;

import otmkurssiprojekti.Level.GameObjects.TopDownObject;

/**
 *
 * @author Juho Gröhn
 */
class HostileCharacter extends GameCharacter {
    private char id;

    public HostileCharacter(char id, int hp, int str, int end) {
        super(hp, str, end);
        this.id = id;
    }

    @Override
    public char getId() {
        return id;
    }

    //Anything that touches the hostile NPC will get damaged.
    @Override
    public void touch(TopDownObject t) {
        t.takeDamage(this.getAttackStrength());
    }

    //A hostile NPC can not be interacted with.
    @Override
    public void interact(TopDownObject t) {
    }

}
