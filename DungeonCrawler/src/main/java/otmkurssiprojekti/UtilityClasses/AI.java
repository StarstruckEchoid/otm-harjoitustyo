/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UtilityClasses;

import java.util.*;
import java.util.stream.Collectors;
import otmkurssiprojekti.Level.GameLevel;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.Level.GameObjects.MobileObject;
import otmkurssiprojekti.Level.GameObjects.PlayerCharacter;

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
        //Perform a width-first search
        Queue<Coords> searchQueue = new ArrayDeque();
        Set<Coords> searched = new HashSet<>();
        Map<Coords, Coords> backTrack = new HashMap<>();

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
                    backTrack.put(next, current);
                }
            }
        }

        Stack<Coords> ret = new Stack<>();

        Coords c = to;
        while (!c.equals(from)) {
            ret.push(c);
            c = backTrack.getOrDefault(c, from);
        }
        return ret;
    }

    public static Stack<Coords> greedyRoute(Coords from, Coords to, GameLevel gameLevel) {
        //Perform a width-first search
        Queue<Coords> searchQueue = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.squaredEuclideanDistance(to), b.squaredEuclideanDistance(to))
        );
        Set<Coords> searched = new HashSet<>();
        Map<Coords, Coords> backTrack = new HashMap<>();

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
                    backTrack.put(next, current);
                }
            }
        }

        Stack<Coords> ret = new Stack<>();

        Coords c = to;
        while (!c.equals(from)) {
            ret.push(c);
            c = backTrack.getOrDefault(c, from);
        }
        return ret;
    }

    private static void passive(MobileObject mo, GameLevel gameLevel) {
        //Do nothing.
    }

    private static void follow(MobileObject mo, GameLevel gameLevel) {
        PlayerCharacter pc = gameLevel.getPlayerCharacter();
        //An AI that follows will not get too close to player.
        final int DISTANCE = 4;
        if (mo.getCoords().squaredEuclideanDistance(pc.getCoords()) < DISTANCE * DISTANCE) {
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
        final int MAX_DISTANCE = 8;
        if (mo.getCoords().squaredEuclideanDistance(pc.getCoords()) >= MAX_DISTANCE * MAX_DISTANCE) {
            return;
        }
        //An AI that hunts will attack the player once in melee range.
        final int ATTACK_DISTANCE = 2;
        if (mo.getCoords().squaredEuclideanDistance(pc.getCoords()) < ATTACK_DISTANCE * ATTACK_DISTANCE) {
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
        final int DISTANCE = 8;
        if (mo.getCoords().squaredEuclideanDistance(pcCoords) > DISTANCE * DISTANCE) {
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

}
