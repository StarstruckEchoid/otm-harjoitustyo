/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import static java.lang.Integer.compare;
import java.util.*;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;
import static otmkurssiprojekti.domain.gameobject.location.Direction.values;
import otmkurssiprojekti.domain.level.GameLevel;

/**
 * An utility class that implements path finding algorithms to be used by Mobile
 * Actors, most notably NonPlayerCharacters.
 *
 * @see NonPlayerCharacter
 *
 * @author Juho Gröhn
 */
public class AI {

    /**
     * Searches for the best route from coordinates from to coordinates to in
     * the context of gameLevel. bestRoute uses breadth-first search to find the
     * route. Method returns the path as a stack of coordinates. NB! This method
     * is much slower than greedyRoute, and will cause considerable lag if run
     * too often.
     *
     * @param from The coords from which path generation begins.
     * @param to The coords to which we want to find the path.
     * @param gameLevel The GameLevel in the context of which the path is being
     * found.
     * @return Returns the path as a a stack of coordinates. The first step to
     * take is on the top of the stack. The last step is on the bottom.
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
     * @param from The coords from which path generation begins.
     * @param to The coords to which we want to find the path.
     * @param gameLevel The GameLevel in the context of which the path is being
     * found.
     * @return Returns the path as a a stack of coordinates. The first step to
     * take is on the top of the stack. The last step is on the bottom.
     */
    public static Stack<Coords> greedyRoute(Coords from, Coords to, GameLevel gameLevel) {
        //Perform a best-first search
        Queue<Coords> searchQueue = new PriorityQueue<>(nearestManhattan(to));
        searchQueue.add(from);

        Map<Coords, Coords> backTrack = performSearchOnQueue(gameLevel, searchQueue, to);

        return walkMap(backTrack, to, from);
    }

    private static Comparator<Coords> nearestManhattan(Coords to) {
        return (a, b) -> compare(a.manhattanDistance(to), b.manhattanDistance(to));
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
            for (Direction d : values()) {
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
