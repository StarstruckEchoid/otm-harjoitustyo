/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter;

import java.util.Stack;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.utilityclasses.AI;

public class HostileNonPlayerCharacter extends BasicNonPlayerCharacter {

    private Stack<Coords> plan = new Stack<>();

    public HostileNonPlayerCharacter(char id, int lvl, int hp, int str, int per, int end, int agl, Coords coords, Direction direction) {
        super(id, lvl, hp, str, per, end, agl, coords, direction);
    }

    public HostileNonPlayerCharacter(NonPlayerCharacterArchetype npca, Coords coords, Direction direction) {
        super(npca, coords, direction);
    }

    @Override
    public void act(GameLevel gameLevel) {
        if (plan.size() < 5) {
            plan = AI.greedyRoute(this.getCoords(), gameLevel.getPlayer().getCoords(), gameLevel);
        }
        if (!plan.empty()) {
            this.move(plan.pop());
        }
    }
}
