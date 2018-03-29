/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects.GameBlocks;

import otmkurssiprojekti.Level.GameObjects.GameObject;

/**
 *
 * @author Juho Gröhn
 */
public class InertBlock extends GameBlock {

    public InertBlock(char id) {
        super(id);
    }

    @Override
    public void touch(GameObject t) {
    }

    @Override
    public void interact(GameObject t) {
    }

}
