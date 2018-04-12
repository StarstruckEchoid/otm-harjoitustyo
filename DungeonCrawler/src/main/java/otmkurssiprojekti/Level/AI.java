/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.Level;

import java.util.*;
import java.util.stream.Collectors;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.Level.GameObjects.NonPlayerCharacter;
import otmkurssiprojekti.Level.GameObjects.PlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
class AI {

    public static void move(NonPlayerCharacter npc, GameLevel gameLevel) {
        switch (npc.getBehaviour()) {
            case PASSIVE:
                passive(npc, gameLevel);
                break;
            case FOLLOW:
                follow(npc, gameLevel);
                break;
            case HUNT:
                hunt(npc, gameLevel);
                break;
            case FLEE:
                flee(npc, gameLevel);
                break;
            case PATROL:
                patrol(npc, gameLevel);
                break;
            default:
                throw new AssertionError(npc.getBehaviour().name());

        }
    }

    public static Stack<Direction> bestRoute(Coords from, Coords to, GameLevel gameLevel) {
        //Perform a width-first search
        Queue<Coords> searchQueue = new ArrayDeque();
        Set<Coords> searched = new HashSet<>();
        Map<Coords, Direction> backTrack = new HashMap<>();

        searchQueue.add(from);

        while (!searchQueue.isEmpty()) {
            Coords current = searchQueue.poll();
            if (current.equals(to)) {
                break;
            }
            searched.add(current);
            //Iterate on neighbours.
            for (Direction d : Direction.values()) {
                Coords next = current.sum(d.getCoords());
                if (!searched.contains(next) && !gameLevel.isOccupied(current)) {
                    searchQueue.add(next);
                    backTrack.put(next, d);
                }
            }
        }

        Stack<Direction> ret = new Stack<>();

        Coords c = to;
        while (c != from) {
            Direction d = backTrack.get(c);
            ret.push(d);
            Coords delta = d.getCoords();
            delta.multiply(-1);
            c = c.sum(delta);
        }

        return ret;
    }

    public static Stack<Direction> greedyRoute(Coords from, Coords to, GameLevel gameLevel) {
        //Perform a width-first search
        Queue<Coords> searchQueue = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.squaredEuclideanDistance(to), b.squaredEuclideanDistance(to))
        );
        Set<Coords> searched = new HashSet<>();
        Map<Coords, Direction> backTrack = new HashMap<>();

        searchQueue.add(from);

        while (!searchQueue.isEmpty()) {
            Coords current = searchQueue.poll();
            if (current.equals(to)) {
                break;
            }
            searched.add(current);
            //Iterate on neighbours.
            for (Direction d : Direction.values()) {
                Coords next = current.sum(d.getCoords());
                if (!searched.contains(next) && !gameLevel.isOccupied(current)) {
                    searchQueue.add(next);
                    backTrack.put(next, d);
                }
            }
        }

        Stack<Direction> ret = new Stack<>();
        
        ret.push(backTrack.get(to));

        return ret;
    }

    private static void passive(NonPlayerCharacter npc, GameLevel gameLevel) {
        //Do nothing.
    }

    private static void follow(NonPlayerCharacter npc, GameLevel gameLevel) {
        PlayerCharacter pc = gameLevel.getPlayerCharacter();
        //An AI that follows will not get too close to player.
        final int DISTANCE = 4;
        if (npc.getCoords().squaredEuclideanDistance(pc.getCoords()) < DISTANCE * DISTANCE) {
            return;
        }

        Stack<Direction> directions = greedyRoute(npc.getCoords(), pc.getCoords(), gameLevel);
        if (!directions.empty()) {
            npc.move(directions.pop());
        }
    }

    private static void hunt(NonPlayerCharacter npc, GameLevel gameLevel) {
        PlayerCharacter pc = gameLevel.getPlayerCharacter();
        //An AI hunts will stop hunting once the distance to player is too much.
        final int MAX_DISTANCE = 5;
        if (npc.getCoords().squaredEuclideanDistance(pc.getCoords()) > MAX_DISTANCE * MAX_DISTANCE) {
            return;
        }
        //An AI that hunts will attack the player once in melee range.
        final int ATTACK_DISTANCE = 2;
        if (npc.getCoords().squaredEuclideanDistance(pc.getCoords()) < ATTACK_DISTANCE * ATTACK_DISTANCE) {
            pc.takeDamage(npc);
            return;
        }
        Stack<Direction> directions = greedyRoute(npc.getCoords(), pc.getCoords(), gameLevel);
        if (!directions.empty()) {
            npc.move(directions.pop());
        }
    }

    private static void flee(NonPlayerCharacter npc, GameLevel gameLevel) {
        Coords pcCoords = gameLevel.getPlayerCharacter().getCoords();
        //An AI flees will stop fleeing once the distance to player is sufficient.
        final int DISTANCE = 5;
        if (npc.getCoords().squaredEuclideanDistance(pcCoords) < DISTANCE * DISTANCE) {
            return;
        }

        Direction escapePlan = Arrays.stream(Direction.values())
                .filter(d -> gameLevel.isOccupied(d.getCoords()))
                .max((a, b) -> { //Pick the direction that gets the npc as far away from the player as possible.
                    Coords aCoords = pcCoords.sum(a.getCoords());
                    Coords bCoords = pcCoords.sum(b.getCoords());
                    return pcCoords.squaredEuclideanDistance(aCoords) - pcCoords.squaredEuclideanDistance(bCoords);
                })
                .orElse(null);

        if (escapePlan != null) {
            npc.move(escapePlan);
        }

    }

    private static void patrol(NonPlayerCharacter npc, GameLevel gameLevel) {
        List<Direction> directions = Arrays.stream(Direction.values())
                .filter(d -> gameLevel.isOccupied(d.getCoords()))
                .collect(Collectors.toList());
        //A patrolling NPC will go to a random direction.
        int idx = new Random().nextInt(directions.size());
        Direction d = directions.get(idx);
        npc.move(d);
    }

}
