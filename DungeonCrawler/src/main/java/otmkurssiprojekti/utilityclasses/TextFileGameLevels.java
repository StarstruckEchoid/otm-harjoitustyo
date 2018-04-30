/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import otmkurssiprojekti.domain.level.BasicGameLevel;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.domain.gameobject.archetypes.ImmutableObjectArchetype;
import otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.BasicPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gameinanimates.ImmutableObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LinkObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author gjuho
 */
public class TextFileGameLevels {

    /**
     *
     */
    public static final int BLOCKS_LEVEL = 1;

    /**
     *
     */
    public static final String EMPTY_IDENTIFIER = "EMPTY";

    /**
     * Makes a game level from a text file. The text file should be something
     * like the following:
     * <pre>
     * Dungeon
     *
     * 120;10;4;3;5,3,4,0
     *
     * v;10,12,0
     * r;9,4,1
     * +;2,0,0
     *
     * 0,,0,,,,,0
     * 0,,,,000,0
     * 0,,,,0,,,0
     * 0,0,,0,0,0
     * 0000000000
     *
     * ...
     * </pre>
     *
     * @param string The game level data as a string representation.
     * @return Returns the concrete GameLevel based on the string.
     */
    public static GameLevel makeGameLevel(String string) {
        String[] fields = string.split("\n\n");
        String levelName = fields[0];
        BasicPlayerCharacter player = makePlayerCharacter(fields[1]);
        List<NonPlayerCharacter> npcs = makeNPCList(fields[2]);
        List<ImmutableObject> blocks = makeBlockList(fields[3]);
        List<InteractiveObject> interactives = makeInteractiveObjectList(fields[4]);
        List<LinkObject> levelLinks = makeLevelLinkList(fields[5]);
        List<PointsBall> points = makePointsSourceList(fields[6]);

        return new BasicGameLevel(levelName, player, npcs, blocks, interactives, levelLinks, points);
    }

    /**
     * Does the inverse operation of makeGameLevel:
     * {@link #makeGameLevel(java.lang.String)}.
     *
     * @param gameLevel The GameLevel to be converted.
     * @return Returns the string representation of the level data.
     */
    public static String printGameLevel(BasicGameLevel gameLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append(gameLevel.getLevelName()).append("\n\n");
        sb.append(printPlayerCharacter(gameLevel.getPlayer())).append("\n\n");
        sb.append(printNPCList(gameLevel.getNpcs())).append("\n");
        sb.append(printBlockList(gameLevel.getBlocks())).append("\n");
        sb.append(printInteractiveObjectList(gameLevel.getInteractives())).append("\n");
        sb.append(printLevelLinkList(gameLevel.getLevelLinks())).append("\n");
        sb.append(printPointsSourceList(gameLevel.getPoints())).append("\n");

        return sb.toString();
    }

    /**
     * Converts string of the form [0-9]+,[0-9]+,[0-9]+ into coords. Example:
     * "0,9,11" becomes new Coords(0, 9, 11). "-1,3,22" becomes new Coords(-1, 3, 22)
     *
     * @param attr The coords as a string.
     * @return The converted coords.
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

    /**
     * Inverse operation of makeCoords():{@link #makeCoords(java.lang.String)}.
     *
     * @param coords Coords.
     * @return The TextFileGameLevels compatible string representation of the
     * given coords.
     */
    public static String printCoords(Coords coords) {
        return coords.getX() + "," + coords.getY() + "," + coords.getZ();
    }

    /**
     * Creates an archetype based on the enumerator class as well as the string
     * identity given. Example: (PlayercharacterArchetype.class, "ASSASSIN") becomes
     * PlayerCharacterArchetype.ASSASSIN
     *
     * @param <T> An enumerator class.
     * @param t The enumerator class from which the object is to be obtained.
     * @param attr The string by which the enumerator object is identified.
     * @return Returns the enumerator that matches the string, if one exists.
     */
    public static <T extends Enum<?>> Optional<T> makeArcheType(Class<T> t, String attr) {
        for (T arch : t.getEnumConstants()) {
            if (arch.toString().equals(attr)) {
                return Optional.of(arch);
            }
        }
        return Optional.empty();
    }

    /**
     * Inverse operation of makeArchetype():
     * {@link #makeArcheType(java.lang.Class, java.lang.String)}.
     *
     * @param <T> An enumerator class.
     * @param t An enumerator object.
     * @return The string representation of t.
     */
    public static <T extends Enum<?>> String printArchetype(T t) {
        return t.toString();
    }

    /**
     * Makes a PlayerCharacter. Example usage: "10;9;8;7;6;0,1,2" becomes new
     * PlayerCharacter(10,9,8,7,6, new Coords(0,1,2).
     *
     * @param field The string representation of the PlayerCharacter.
     * @return The parsed PlayerCharacter.
     * @see PlayerCharacter
     * @see BasicPlayerCharacter
     */
    public static BasicPlayerCharacter makePlayerCharacter(String field) {
        String[] attrs = field.split(";");
        int hp = Integer.parseInt(attrs[0]);
        int str = Integer.parseInt(attrs[1]);
        int per = Integer.parseInt(attrs[2]);
        int end = Integer.parseInt(attrs[3]);
        int agl = Integer.parseInt(attrs[4]);
        Coords coords = makeCoords(attrs[5]);

        return new BasicPlayerCharacter(hp, str, per, end, agl, coords, Direction.DOWN);
    }

    /**
     * The inverse operation of makePlayerCharacter:
     * {@link #makePlayerCharacter(java.lang.String)}.
     *
     * @param pc The PlayerCharacter.
     * @return The TextFileGameLevels compatible string representation of pc.
     */
    public static String printPlayerCharacter(BasicPlayerCharacter pc) {
        return pc.getHp() + ";" + pc.getStr() + ";" + pc.getPer() + ";" + pc.getEnd() + ";" + pc.getAgl() + ";" + printCoords(pc.getCoords());
    }

    /**
     * Accepts strings of the form (id);(coords) to generate a
     * NonPlayerCharacter.
     *
     * @param field The string based on which the NonPlayerCharacter is
     * generated.
     * @return Returns a NonPlayerCharacter, if one can be parsed from the given
     * string. Else returns empty.
     * @see NonPlayerCharacter
     */
    public static Optional<NonPlayerCharacter> makeNonPlayerCharacter(String field) {
        String[] attrs = field.split(";");
        if (attrs.length < 2) {
            return Optional.empty();
        }
        Optional<NonPlayerCharacterArchetype> npca = makeArcheType(NonPlayerCharacterArchetype.class, attrs[0]);
        Coords coords = makeCoords(attrs[1]);

        if (npca.isPresent()) {
            return Optional.of(new HostileNonPlayerCharacter(npca.get(), coords, Direction.DOWN));
        }
        return Optional.empty();
    }

    /**
     * The inverse operation of makeNonPlayerCharacter:
     * {@link #makeNonPlayerCharacter(java.lang.String)}.
     *
     * @param npc NonPlayerCharacter to be converted.
     * @return Returns TextFileGameLevels compatible string representation.
     */
    public static String printNonPlayerCharacter(NonPlayerCharacter npc) {
        return npc.getId() + ";" + printCoords(npc.getCoords());
    }

    /**
     * Makes a list of NonPlayerCharacters from a list of NonPlayerCharacter
     * string representations. The string representations are presumed to be
     * separated by line breaks.
     *
     * @param field The list of NonPlayerCharacters as a string.
     * @return The concrete list of NonPlayerCharacters.
     */
    public static List<NonPlayerCharacter> makeNPCList(String field) {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return new ArrayList<>();
        }
        List<NonPlayerCharacter> npcs = new ArrayList<>();
        String[] npcDats = field.split("\n");
        for (String npcDat : npcDats) {
            makeNonPlayerCharacter(npcDat).ifPresent(i -> npcs.add(i));
        }
        return npcs;
    }

    /**
     * Inverse operation of makeNPCList:{@link #makeNPCList(java.lang.String)}.
     *
     * @param npcs The list of NonPlayerCharacters.
     * @return String representation.
     */
    public static String printNPCList(List<NonPlayerCharacter> npcs) {
        if (npcs.isEmpty()) {
            return EMPTY_IDENTIFIER + "\n";
        }
        StringBuilder sb = new StringBuilder();
        npcs.forEach(npc -> sb.append(printNonPlayerCharacter(npc)).append("\n"));
        return sb.toString();
    }

    /**
     *
     * @param field
     * @return
     */
    public static List<ImmutableObject> makeBlockList(String field) {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return new ArrayList<>();
        }
        List<ImmutableObject> blocks = new ArrayList<>();
        String[] rows = field.split("\n");
        for (int y = 0; y < rows.length; y++) {
            String row = rows[y];
            for (int x = 0; x < row.length(); x++) {
                String id = Character.toString(row.charAt(x));
                blocks.add(
                        new ImmutableObject(makeArcheType(ImmutableObjectArchetype.class, id).orElse(ImmutableObjectArchetype.AIR),
                                new Coords(x, y, BLOCKS_LEVEL),
                                Direction.DOWN)
                );
            }
        }
        return blocks;
    }

    /**
     *
     * @param blocks
     * @return
     */
    public static String printBlockList(List<ImmutableObject> blocks) {
        if (blocks.isEmpty()) {
            return EMPTY_IDENTIFIER + "\n";
        }
        int biggestY = blocks.stream().mapToInt(o -> o.getCoords().getY()).max().orElse(0);
        int biggestX = blocks.stream().mapToInt(o -> o.getCoords().getX()).max().orElse(0);
        char[][] matrix = new char[biggestY + 1][biggestX + 1];
        for (ImmutableObject block : blocks) {
            Coords blockCoords = block.getCoords();
            matrix[blockCoords.getY()][blockCoords.getX()] = block.getId();
        }
        StringBuilder sb = new StringBuilder();
        for (char[] line : matrix) {
            sb.append(String.copyValueOf(line));
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     *
     * @param field
     * @return
     */
    public static List<InteractiveObject> makeInteractiveObjectList(String field) {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    /**
     *
     * @param interactiveObjects
     * @return
     */
    public static String printInteractiveObjectList(List<InteractiveObject> interactiveObjects) {
        return EMPTY_IDENTIFIER + "\n";
    }

    /**
     *
     * @param field
     * @return
     */
    public static List<LinkObject> makeLevelLinkList(String field) {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    /**
     *
     * @param interactiveObjects
     * @return
     */
    public static String printLevelLinkList(List<LinkObject> interactiveObjects) {
        return EMPTY_IDENTIFIER + "\n";
    }

    /**
     *
     * @param field
     * @return
     */
    public static List<PointsBall> makePointsSourceList(String field) {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    /**
     *
     * @param interactiveObjects
     * @return
     */
    public static String printPointsSourceList(List<PointsBall> interactiveObjects) {
        return EMPTY_IDENTIFIER + "\n";
    }

}
