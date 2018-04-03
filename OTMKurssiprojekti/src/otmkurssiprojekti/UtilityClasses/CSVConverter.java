/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UtilityClasses;

import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.Level.GameLevel;
import otmkurssiprojekti.Level.GameObjects.Archetypes.*;
import otmkurssiprojekti.Level.GameObjects.Dependencies.*;
import otmkurssiprojekti.Level.GameObjects.*;

/**
 *
 * @author Juho Gröhn
 */
public class CSVConverter {

    //Tier 0: Highest level methods.
    public static GameLevel CSVToGameLevel(String csv) throws IllegalArgumentException {
        String[] data = csv.split("\n\n");
        String name = data[0];
        PlayerCharacter player = CSVToPlayerCharacter(data[1]);
        List<NonPlayerCharacter> npcs = CSVToNPCList(data[2]);
        List<ImmutableObject> blocks = CSVToImmoList(data[3]);
        List<InteractiveObject> interactives = CSVToInteroList(data[4]);
        List<LinkObject> levelLinks = CSVToLinkoList(data[5]);
        List<PointsObject> points = CSVToPointsoList(data[6]);

        return new GameLevel(name, player, npcs, blocks, interactives, levelLinks, points);
    }

    //Tier 1: List methods.
    public static List<NonPlayerCharacter> CSVToNPCList(String csv) {
        List<NonPlayerCharacter> npcs = new ArrayList<>();
        String[] data = csv.split("\n");
        for (String datum : data) {
            npcs.add(CSVToNonPlayerCharacter(datum));
        }
        return npcs;
    }

    public static List<ImmutableObject> CSVToImmoList(String csv) {
        List<ImmutableObject> blocks = new ArrayList<>();
        String[] data = csv.split("\n");
        for (String datum : data) {
            if (!datum.equals("empty")) {
                blocks.add(CSVToImmutableObject(datum));
            }
        }
        return blocks;
    }

    public static List<InteractiveObject> CSVToInteroList(String csv) {
        List<InteractiveObject> interactives = new ArrayList<>();
        String[] data = csv.split("\n");
        for (String datum : data) {
            if (!datum.equals("empty")) {
                interactives.add(CSVToInteractiveObject(datum));
            }
        }
        return interactives;
    }

    public static List<LinkObject> CSVToLinkoList(String csv) {
        List<LinkObject> levelLinks = new ArrayList<>();
        String[] data = csv.split("\n");
        for (String datum : data) {
            if (!datum.equals("empty")) {
                levelLinks.add(CSVToLinkObject(datum));
            }
        }
        return levelLinks;
    }

    public static List<PointsObject> CSVToPointsoList(String csv) {
        List<PointsObject> levelLinks = new ArrayList<>();
        String[] data = csv.split("\n");
        for (String datum : data) {
            if (!datum.equals("empty")) {
                levelLinks.add(CSVToPointsObject(datum));
            }
        }
        return levelLinks;
    }

    //Tier 2: GameObject methods.
    public static PlayerCharacter CSVToPlayerCharacter(String csv) throws IllegalArgumentException {
        String[] data = csv.split(";");
        Coords coords = CSVtoCoords(data[0]);
        int str = Integer.parseInt(data[1]);
        int end = Integer.parseInt(data[2]);
        Direction direction = CSVToDirection(data[3]);
        int hp = Integer.parseInt(data[4]);

        return new PlayerCharacter(coords, str, end, direction, hp);
    }

    public static NonPlayerCharacter CSVToNonPlayerCharacter(String csv) throws IllegalArgumentException {
        String[] data = csv.split(";");
        NonPlayerCharacterArchetype npca = CSVToNPCA(data[0]);
        Coords coords = CSVtoCoords(data[1]);
        Direction direction = CSVToDirection(data[2]);

        return new NonPlayerCharacter(npca, coords, direction);
    }

    public static ImmutableObject CSVToImmutableObject(String csv) {
        String[] data = csv.split(";");
        ImmutableObjectArchetype immoa = CSVtoImmoA(data[0]);
        Coords coords = CSVtoCoords(data[1]);
        Direction direction = CSVToDirection(data[2]);

        return new ImmutableObject(immoa, coords, direction);
    }

    public static InteractiveObject CSVToInteractiveObject(String csv) throws IllegalArgumentException { //NOTE: Does not create children nodes. Needs to be done separately.
        String[] data = csv.split(";");
        InteractiveObjectArchetype interoa = CSVToInteroA(data[0]);
        Coords coords = CSVtoCoords(data[1]);
        Direction direction = CSVToDirection(data[2]);

        return new InteractiveObject(interoa, coords, direction, new ArrayList<>());
    }

    private static LinkObject CSVToLinkObject(String datum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static PointsObject CSVToPointsObject(String datum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Tier 3a: Dependency methods
    public static Coords CSVtoCoords(String csv) throws IllegalArgumentException {
        String[] data = csv.split(",");
        int x = Integer.parseInt(data[0]);
        int y = Integer.parseInt(data[1]);
        int z = Integer.parseInt(data[2]);
        return new Coords(x, y, z);
    }

    public static Direction CSVToDirection(String csv) throws IllegalArgumentException {
        for (Direction dir : Direction.values()) {
            if (csv.equals(dir.getName())) {
                return dir;
            }
        }
        throw new IllegalArgumentException();
    }

    //Tier 3b: Archetype methods
    public static NonPlayerCharacterArchetype CSVToNPCA(String csv) throws IllegalArgumentException {
        char id = csv.charAt(0);
        for (NonPlayerCharacterArchetype npca : NonPlayerCharacterArchetype.values()) {
            if (id == npca.getId()) {
                return npca;
            }
        }
        throw new IllegalArgumentException();
    }

    public static ImmutableObjectArchetype CSVtoImmoA(String csv) throws IllegalArgumentException {
        char id = csv.charAt(0);
        for (ImmutableObjectArchetype immoa : ImmutableObjectArchetype.values()) {
            if (id == immoa.getId()) {
                return immoa;
            }
        }
        throw new IllegalArgumentException();
    }

    private static InteractiveObjectArchetype CSVToInteroA(String csv) throws IllegalArgumentException {
        char id = csv.charAt(0);
        for (InteractiveObjectArchetype interoa : InteractiveObjectArchetype.values()) {
            if (id == interoa.getId()) {
                return interoa;
            }
        }
        throw new IllegalArgumentException();
    }

}
