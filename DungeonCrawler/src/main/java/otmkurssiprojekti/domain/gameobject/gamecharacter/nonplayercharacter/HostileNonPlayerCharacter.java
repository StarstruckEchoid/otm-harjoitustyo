/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter;

import java.util.Stack;
import otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.domain.level.GameLevel;
import static otmkurssiprojekti.utilityclasses.AI.greedyRoute;

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
        PlayerCharacter pc = gameLevel.getPlayer();
        if (this.getCoords().manhattanDistance(pc.getCoords()) < 2) {
            this.hurt(pc);
        } else {
            huntPlayer(gameLevel);
        }
    }

    private void huntPlayer(GameLevel gameLevel) {
        final int maxDetectionRange = 6;
        Coords npcCoords = this.coords;
        Coords pcCoords = gameLevel.getPlayer().getCoords();
        //If distance to player is more than 10, don't bother.
        if (npcCoords.manhattanDistance(pcCoords) >= maxDetectionRange) {
            return;
        }
        if (plan.size() < 8) {
            plan = greedyRoute(npcCoords, pcCoords, gameLevel);
        }
        if (!plan.empty()) {
            this.move(plan.pop());
        }
    }
}
