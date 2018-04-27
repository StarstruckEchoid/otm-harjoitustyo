/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import java.util.*;
import java.util.stream.Collectors;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.Mobile;
import otmkurssiprojekti.domain.gameobject.interfaces.NonPlayerCharacter;

/**
 *
 * @author Juho Gröhn
 */
public class AI {

    /**
     * Does nothing at all.
     *
     */
    @Deprecated
    public static void passive() {
        //Do nothing.
    }

    /**
     * Will make this Mobile follow the player in gameLevel. The Mobile will
     * keep a small distance to the player.
     *
     * @param mo
     * @param gameLevel
     */
    @Deprecated
    public static void follow(Mobile mo, GameLevel gameLevel) {
        PlayerCharacter pc = gameLevel.getPlayer();
        //An AI that follows will not get too close to player.
        final int distance = 4;
        if (mo.getCoords().squaredEuclideanDistance(pc.getCoords()) < distance * distance) {
            return;
        }

        Stack<Coords> stack = greedyRoute(mo.getCoords(), pc.getCoords(), gameLevel);
        if (!stack.empty()) {
            mo.move(stack.pop());
        }
    }

    /**
     * Will make this NonPlayerCharacter hunt the playerCharacter in the
     * gameLevel. The NonPlayerCharacter will deal damage to the player once
     * close enough.
     *
     * @param npc
     * @param gameLevel
     */
    @Deprecated
    public static void hunt(NonPlayerCharacter npc, GameLevel gameLevel) {
        PlayerCharacter pc = gameLevel.getPlayer();
        //An AI hunts will stop hunting once the distance to player is too much.
        final int maxDistance = 8;
        if (npc.getCoords().squaredEuclideanDistance(pc.getCoords()) >= maxDistance * maxDistance) {
            return;
        }
        //An AI that hunts will attack the player once in melee range.
        final int attackDistance = 2;
        if (npc.getCoords().squaredEuclideanDistance(pc.getCoords()) < attackDistance * attackDistance) {
            pc.takeDamage(npc);
            return;
        }
        Stack<Coords> stack = greedyRoute(npc.getCoords(), pc.getCoords(), gameLevel);
        if (!stack.empty()) {
            npc.move(stack.pop());
        }
    }

    /**
     * This Mobile will flee the playerCharacter, always going to the
     * furthest-away block possible from the player.
     *
     * @param mo
     * @param gameLevel
     */
    @Deprecated
    public static void flee(Mobile mo, GameLevel gameLevel) {
        Coords pcCoords = gameLevel.getPlayer().getCoords();
        Coords npcCoords = mo.getCoords();
        //An AI flees will stop fleeing once the distance to player is sufficient.
        final int distance = 8;
        if (mo.getCoords().squaredEuclideanDistance(pcCoords) > distance * distance) {
            return;
        }

        Direction escapePlan = Arrays.stream(Direction.values())
                .filter(d -> !d.equals(Direction.IN) && !d.equals(Direction.OUT))
                .filter(d -> !gameLevel.isInaccessible(npcCoords.sum(d.getCoords())))
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

    /**
     * The Mobile will go to a random direction every time this function is
     * called.
     *
     * @param mo
     * @param gameLevel
     */
    @Deprecated
    public static void shamble(Mobile mo, GameLevel gameLevel) {
        List<Direction> directions = Arrays.stream(Direction.values())
                .filter(d -> !d.equals(Direction.IN) && !d.equals(Direction.OUT))
                .filter(d -> !gameLevel.isInaccessible(mo.getCoords().sum(d.getCoords())))
                .collect(Collectors.toList());
        //A patrolling NPC will go to a random direction.
        int idx = new Random().nextInt(directions.size());
        Direction d = directions.get(idx);
        mo.move(d);
    }

    //Pathfinding algorithms.
    /**
     * Searches for the best route from coordinates from to coordinates to in
     * the context of gameLevel. bestRoute uses breadth-first search to find the
     * route. Method returns the path as a stack of coordinates.
     *
     * @param from
     * @param to
     * @param gameLevel
     * @return
     */
    public static Stack<Coords> bestRoute(Coords from, Coords to, GameLevel gameLevel) {
        //Perform a breadth-first search
        Queue<Coords> searchQueue = new ArrayDeque();
        searchQueue.add(from);

        Map<Coords, Coords> backTrack = performSearchOnQueue(gameLevel, searchQueue, to);

        return walkMap(backTrack, to, from);
    }

    /**
     * Searches for a naive suboptimal route from coordinates from to
     * coordinates to in the context of gameLevel. greedyRoute uses best-first
     * search to find the route. Method returns the path as a stack of
     * coordinates.
     *
     * @param from
     * @param to
     * @param gameLevel
     * @return
     */
    public static Stack<Coords> greedyRoute(Coords from, Coords to, GameLevel gameLevel) {
        //Perform a best-first search
        Queue<Coords> searchQueue = new PriorityQueue<>(nearestEuclidean(to));
        searchQueue.add(from);

        Map<Coords, Coords> backTrack = performSearchOnQueue(gameLevel, searchQueue, to);

        return walkMap(backTrack, to, from);
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
                if (!searched.contains(next) && !gameLevel.isInaccessible(current)) {
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
