/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.level.gameobjects.gamecharacter.playercharacter;

import otmkurssiprojekti.level.gameobjects.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.level.gameobjects.gamecharacter.BasicStatsCharacter;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;
import otmkurssiprojekti.level.gameobjects.interfaces.Mobile;

/**
 *
 * @author Juho Gröhn
 */
public class PlayerCharacter extends BasicStatsCharacter implements Mobile {

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

}
