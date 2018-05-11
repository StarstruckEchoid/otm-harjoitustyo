/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter;

import otmkurssiprojekti.domain.gameobject.archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.gamecharacter.BasicStatsCharacter;
import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.interfaces.Destructible;
import otmkurssiprojekti.domain.gameobject.interfaces.PointsSource;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class BasicPlayerCharacter extends BasicStatsCharacter implements PlayerCharacter {

    private static final char ID = '@';
    private int points;

    public BasicPlayerCharacter(int hp, int str, int per, int end, int agl, Coords coords, Direction direction) {
        super(hp, str, per, end, agl, coords, direction);
        this.points = 0;
    }

    public BasicPlayerCharacter(int hp, int str, int per, int end, int agl, Coords coords, Direction direction, int points) {
        super(hp, str, per, end, agl, coords, direction);
        this.points = points;
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

    public Coords coordsAt(Direction dir) {
        return coords.sum(dir.getCoords());
    }

    @Override
    public char getId() {
        return ID;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    /**
     * Hurts a Destructible. But, also, if the Destructible is a PointsSource,
     * then, if the Destructible dies as a result of the PlayerCharacter's
     * attack, increase the player's points.
     */
    @Override
    public void hurt(Destructible d) {
        if (d.isDead()) {
            return;
        }
        super.hurt(d);
        if (d.isDead() && d instanceof PointsSource) {
            this.points += ((PointsSource) d).getPoints();
        }
    }

    @Override
    public boolean collectPoint(PointsBall pb) {
        if (pb.getCoords().equals(this.coords)) {
            this.points += pb.getPoints();
            return true;
        } else {
            return false;
        }
    }

}
