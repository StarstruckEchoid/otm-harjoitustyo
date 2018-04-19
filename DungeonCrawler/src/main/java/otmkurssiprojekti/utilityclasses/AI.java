/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import java.util.*;
import java.util.stream.Collectors;
import otmkurssiprojekti.level.GameLevel;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;
import otmkurssiprojekti.level.gameobjects.MobileObject;
import otmkurssiprojekti.level.gameobjects.PlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
public class AI {

    private static int clock = 0;

    public static void moveAll(List<MobileObject> mos, GameLevel gameLevel) {
        mos.stream()
                .filter((mo) -> !(clock % mo.getSlowness() != 0))
                .forEachOrdered((mo) -> {
                    move(mo, gameLevel);
                });
        clock++;
    }

    private static void move(MobileObject mo, GameLevel gameLevel) {
        switch (mo.getBehaviour()) {
            case PASSIVE:
                passive(mo, gameLevel);
                break;
            case FOLLOW:
                follow(mo, gameLevel);
                break;
            case HUNT:
                hunt(mo, gameLevel);
                break;
            case FLEE:
                flee(mo, gameLevel);
                break;
            case PATROL:
                patrol(mo, gameLevel);
                break;
            default:
                throw new AssertionError(mo.getBehaviour().name());
        }
    }

    public static Stack<Coords> bestRoute(Coords from, Coords to, GameLevel gameLevel) {
        //Perform a breadth-first search
        Queue<Coords> searchQueue = new ArrayDeque();
        searchQueue.add(from);

        Map<Coords, Coords> backTrack = performSearchOnQueue(gameLevel, searchQueue, to);

        return walkMap(backTrack, to, from);
    }

    public static Stack<Coords> greedyRoute(Coords from, Coords to, GameLevel gameLevel) {
        //Perform a best-first search
        Queue<Coords> searchQueue = new PriorityQueue<>(nearestEuclidean(to));
        searchQueue.add(from);

        Map<Coords, Coords> backTrack = performSearchOnQueue(gameLevel, searchQueue, to);

        return walkMap(backTrack, to, from);
    }

    private static void passive(MobileObject mo, GameLevel gameLevel) {
        //Do nothing.
    }

    private static void follow(MobileObject mo, GameLevel gameLevel) {
        PlayerCharacter pc = gameLevel.getPlayerCharacter();
        //An AI that follows will not get too close to player.
        final int distance = 4;
        if (mo.getCoords().squaredEuclideanDistance(pc.getCoords()) < distance * distance) {
            return;
        }

        Stack<Coords> stack = greedyRoute(mo.getCoords(), pc.getCoords(), gameLevel);
        if (!stack.empty()) {
            mo.setCoords(stack.pop());
        }
    }

    private static void hunt(MobileObject mo, GameLevel gameLevel) {
        PlayerCharacter pc = gameLevel.getPlayerCharacter();
        //An AI hunts will stop hunting once the distance to player is too much.
        final int maxDistance = 8;
        if (mo.getCoords().squaredEuclideanDistance(pc.getCoords()) >= maxDistance * maxDistance) {
            return;
        }
        //An AI that hunts will attack the player once in melee range.
        final int attackDistance = 2;
        if (mo.getCoords().squaredEuclideanDistance(pc.getCoords()) < attackDistance * attackDistance) {
            mo.doDamage(pc);
            return;
        }
        Stack<Coords> stack = greedyRoute(mo.getCoords(), pc.getCoords(), gameLevel);
        if (!stack.empty()) {
            mo.setCoords(stack.pop());
        }
    }

    private static void flee(MobileObject mo, GameLevel gameLevel) {
        Coords pcCoords = gameLevel.getPlayerCharacter().getCoords();
        Coords npcCoords = mo.getCoords();
        //An AI flees will stop fleeing once the distance to player is sufficient.
        final int distance = 8;
        if (mo.getCoords().squaredEuclideanDistance(pcCoords) > distance * distance) {
            return;
        }

        Direction escapePlan = Arrays.stream(Direction.values())
                .filter(d -> !d.equals(Direction.IN) && !d.equals(Direction.OUT))
                .filter(d -> !gameLevel.isOccupied(npcCoords.sum(d.getCoords())))
                .max((a, b) -> { //Pick the direction that gets the npc as far away from the player as possible.
                    Coords aCoords = npcCoords.sum(a.getCoords());
                    Coords bCoords = npcCoords.sum(b.getCoords());
                    return Integer.compare(pcCoords.squaredEuclideanDistance(aCoords), pcCoords.squaredEuclideanDistance(bCoords));
                })
                .orElse(null);

        if (escapePlan != null) {
            mo.move(escapePlan);
        }

    }

    private static void patrol(MobileObject mo, GameLevel gameLevel) {
        List<Direction> directions = Arrays.stream(Direction.values())
                .filter(d -> !d.equals(Direction.IN) && !d.equals(Direction.OUT))
                .filter(d -> !gameLevel.isOccupied(mo.getCoords().sum(d.getCoords())))
                .collect(Collectors.toList());
        //A patrolling NPC will go to a random direction.
        int idx = new Random().nextInt(directions.size());
        Direction d = directions.get(idx);
        mo.move(d);
    }

    private static Comparator<Coords> nearestEuclidean(Coords to) {
        return (a, b) -> Integer.compare(a.squaredEuclideanDistance(to), b.squaredEuclideanDistance(to));
    }

    private static Comparator<Coords> nearestManhattan(Coords to) {
        return (a, b) -> Integer.compare(a.manhattanDistance(to), b.manhattanDistance(to));
    }

    private static Map<Coords, Coords> performSearchOnQueue(GameLevel gameLevel, Queue<Coords> searchQueue, Coords to) {
        Set<Coords> searched = new HashSet<>();
        Map<Coords, Coords> ret = new HashMap<>();
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
                    ret.put(next, current);
                }
            }
        }
        return ret;
    }

    private static Stack<Coords> walkMap(Map<Coords, Coords> map, Coords to, Coords from) {
        Stack<Coords> ret = new Stack<>();

        Coords c = to;
        while (!c.equals(from)) {
            ret.push(c);
            c = map.getOrDefault(c, from);
        }
        return ret;
    }

}
