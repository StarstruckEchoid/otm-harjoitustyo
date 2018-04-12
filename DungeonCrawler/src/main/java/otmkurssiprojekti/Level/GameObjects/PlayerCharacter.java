/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level.GameObjects;

import otmkurssiprojekti.Level.GameObjects.Archetypes.Behaviour;
import otmkurssiprojekti.Level.GameObjects.Archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class PlayerCharacter extends BasicStatsCharacter {

    private static final char ID = '@';

    public PlayerCharacter(int hp, int str, int per, int end, int agl, Coords coords, Direction direction) {
        super(hp, str, per, end, agl, coords, direction);
    }

    public PlayerCharacter(PlayerCharacterArchetype pca, Coords coords, Direction direction) {
        super(
                pca.getHp(),
                pca.getStr(),
                pca.getPer(),
                pca.getEnd(),
                pca.getAgl(),
                coords,
                direction
        );
    }

    @Override
    public char getId() {
        return ID;
    }

    @Override
    public Behaviour getBehaviour() {
        return Behaviour.PASSIVE;
    }

}
