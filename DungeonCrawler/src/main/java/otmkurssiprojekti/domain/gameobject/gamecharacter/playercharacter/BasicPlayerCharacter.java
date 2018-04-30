/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter;

import otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.gamecharacter.BasicStatsCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
public class BasicPlayerCharacter extends BasicStatsCharacter implements PlayerCharacter {

    private static final char ID = '@';

    public BasicPlayerCharacter(int hp, int str, int per, int end, int agl, Coords coords, Direction direction) {
        super(hp, str, per, end, agl, coords, direction);
    }

    public BasicPlayerCharacter(PlayerCharacterArchetype pca, Coords coords, Direction direction) {
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

    public Coords attack(Direction dir) {
        return coords.sum(dir.getCoords());
    }

    @Override
    public char getId() {
        return ID;
    }

    public int getHp() {
        return hp;
    }

    public int getStr() {
        return str;
    }

    public int getPer() {
        return per;
    }

    public int getEnd() {
        return end;
    }

    public int getAgl() {
        return agl;
    }

}
