/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.level.BasicGameLevel;
import otmkurssiprojekti.level.GameLevel;
import otmkurssiprojekti.level.gameobjects.archetypes.ImmutableObjectArchetype;
import otmkurssiprojekti.level.gameobjects.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.level.gameobjects.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.level.gameobjects.gamecharacter.playercharacter.PlayerCharacter;
import otmkurssiprojekti.level.gameobjects.gameinanimates.ImmutableObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.InteractiveObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.LinkObject;
import otmkurssiprojekti.level.gameobjects.gameinanimates.PointsBall;
import otmkurssiprojekti.level.gameobjects.interfaces.NonPlayerCharacter;
import otmkurssiprojekti.level.gameobjects.location.Coords;
import otmkurssiprojekti.level.gameobjects.location.Direction;

/**
 *
 * @author gjuho
 */
public class TextFileGameLevels {

    public static GameLevel makeGameLevel(String string) {
        String[] fields = string.split("\n\n");
        String levelName = fields[0];
        PlayerCharacter player = makePlayerCharacter(fields[1]);
        List<NonPlayerCharacter> npcs = makeNPCList(fields[2]);
        List<ImmutableObject> blocks = makeBlockList(fields[3]);
        List<InteractiveObject> interactives = makeInteractiveObjectList(fields[4]);
        List<LinkObject> levelLinks = makeLevelLinkList(fields[5]);
        List<PointsBall> points = makePointsSourceList(fields[6]);

        return new BasicGameLevel(levelName, player, npcs, blocks, interactives, levelLinks, points);
    }

    /**
     * Converts string of the form [0-9]+,[0-9]+,[0-9]+ into coords. Example:
     * "0,9,11" -> new Coords(0, 9, 11). "-1,3,22" -> new Coords(-1, 3, 22)
     *
     * @param attr
     * @return
     */
    public static Coords makeCoords(String attr) {
        if (!attr.matches("[0-9]+,[0-9]+,[0-9]+")) {
            throw new IllegalArgumentException();
        }

        String[] attrs = attr.split(",");
        int x = Integer.parseInt(attrs[0]);
        int y = Integer.parseInt(attrs[1]);
        int z = Integer.parseInt(attrs[2]);

        return new Coords(x, y, z);
    }

    public static String printCoords(Coords coords) {
        return coords.getX() + "," + coords.getY() + "," + coords.getZ();
    }

    /**
     * Creates an archetype based on the enumerator class as well as the string
     * identity given. Example: (PlayercharacterArchetype.class, "ASSASSIN") ->
     * PlayerCharacterArchetype.ASSASSIN
     *
     * @param <T>
     * @param t
     * @param attr
     * @return
     */
    public static <T extends Enum<?>> T makeArcheType(Class<T> t, String attr) {
        for (T arch : t.getEnumConstants()) {
            if (arch.toString().equals(attr)) {
                return arch;
            }
        }
        throw new IllegalArgumentException();
    }

    public static <T extends Enum<?>> String printArchetype(T t) {
        return t.toString();
    }

    public static PlayerCharacter makePlayerCharacter(String field) {
        String[] attrs = field.split(";");
        int hp = Integer.parseInt(attrs[0]);
        int str = Integer.parseInt(attrs[1]);
        int per = Integer.parseInt(attrs[2]);
        int end = Integer.parseInt(attrs[3]);
        int agl = Integer.parseInt(attrs[4]);
        Coords coords = makeCoords(attrs[5]);

        return new PlayerCharacter(hp, str, per, end, agl, coords, Direction.DOWN);
    }

    public static String printPlayerCharacter(PlayerCharacter pc) {
        return pc.getHp() + ";" + pc.getStr() + ";" + pc.getPer() + ";" + pc.getEnd() + ";" + pc.getAgl() + ";" + printCoords(pc.getCoords());
    }

    public static NonPlayerCharacter makeNonPlayerCharacter(String field) {
        String[] attrs = field.split(";");
        NonPlayerCharacterArchetype npca = makeArcheType(NonPlayerCharacterArchetype.class, attrs[0]);
        Coords coords = makeCoords(attrs[1]);

        return new HostileNonPlayerCharacter(npca, coords, Direction.DOWN);
    }

    public static String printNonPlayerCharacter(NonPlayerCharacter npc) {
        return npc.getId() + ";" + printCoords(npc.getCoords());
    }

    public static List<NonPlayerCharacter> makeNPCList(String field) {
        List<NonPlayerCharacter> npcs = new ArrayList<>();
        String[] npcDats = field.split("\n");
        for (String npcDat : npcDats) {
            npcs.add(makeNonPlayerCharacter(npcDat));
        }
        return npcs;
    }

    public static List<ImmutableObject> makeBlockList(String field) {
        List<ImmutableObject> blocks = new ArrayList<>();
        String[] rows = field.split("\n");
        for (int y = 0; y < rows.length; y++) {
            String row = rows[y];
            for (int x = 0; x < row.length(); x++) {
                String id = Character.toString(row.charAt(x));
                blocks.add(new ImmutableObject(makeArcheType(ImmutableObjectArchetype.class, id), new Coords(x, y, 1), Direction.DOWN));
            }
        }
        return blocks;
    }

    public static List<InteractiveObject> makeInteractiveObjectList(String field) {
        return new ArrayList<>();
    }

    public static List<LinkObject> makeLevelLinkList(String field) {
        return new ArrayList<>();
    }

    public static List<PointsBall> makePointsSourceList(String field) {
        return new ArrayList<>();
    }

}
