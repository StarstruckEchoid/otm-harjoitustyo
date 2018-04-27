/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import java.util.*;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author Juho Gröhn
 */
public class AI {

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
        Queue<Coords> searchQueue = new PriorityQueue<>(nearestManhattan(to));
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
