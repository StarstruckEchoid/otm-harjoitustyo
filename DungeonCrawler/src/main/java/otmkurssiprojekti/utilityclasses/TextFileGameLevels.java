/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import java.util.ArrayList;
import java.util.List;
import otmkurssiprojekti.domain.level.BasicGameLevel;
import otmkurssiprojekti.domain.level.GameLevel;
import otmkurssiprojekti.domain.gameobject.archetypes.ImmutableObjectArchetype;
import otmkurssiprojekti.domain.gameobject.archetypes.NonPlayerCharacterArchetype;
import otmkurssiprojekti.domain.gameobject.gamecharacter.nonplayercharacter.HostileNonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gamecharacter.playercharacter.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.gameinanimates.ImmutableObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LinkObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.interfaces.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import otmkurssiprojekti.domain.gameobject.location.Direction;

/**
 *
 * @author gjuho
 */
public class TextFileGameLevels {
    
    public static int BLOCKS_LEVEL = 1;
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
     * @param string
     * @return
     */
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
     * Does the reverse operation of makeGameLevel. It turns the gameLevel into
     * a string of the form:
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
     * @param gameLevel
     * @return
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
        throw new IllegalArgumentException("Expected attr to be a string representation of an enum constant but it was \"" + attr + "\".");
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
        if (attrs.length < 2) {
            throw new IllegalArgumentException("Expected \"" + field + "\" to have at least two attributes.");
        }
        NonPlayerCharacterArchetype npca = makeArcheType(NonPlayerCharacterArchetype.class, attrs[0]);
        Coords coords = makeCoords(attrs[1]);
        
        return new HostileNonPlayerCharacter(npca, coords, Direction.DOWN);
    }
    
    public static String printNonPlayerCharacter(NonPlayerCharacter npc) {
        return npc.getId() + ";" + printCoords(npc.getCoords());
    }
    
    public static List<NonPlayerCharacter> makeNPCList(String field) {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return null;
        }
        List<NonPlayerCharacter> npcs = new ArrayList<>();
        String[] npcDats = field.split("\n");
        for (String npcDat : npcDats) {
            npcs.add(makeNonPlayerCharacter(npcDat));
        }
        return npcs;
    }
    
    public static String printNPCList(List<NonPlayerCharacter> npcs) {
        if (npcs.isEmpty()) {
            return EMPTY_IDENTIFIER + "\n";
        }
        StringBuilder sb = new StringBuilder();
        npcs.forEach(npc -> sb.append(printNonPlayerCharacter(npc)).append("\n"));
        return sb.toString();
    }
    
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
                blocks.add(new ImmutableObject(makeArcheType(ImmutableObjectArchetype.class, id), new Coords(x, y, BLOCKS_LEVEL), Direction.DOWN));
            }
        }
        return blocks;
    }
    
    public static String printBlockList(List<ImmutableObject> blocks) {
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
    
    public static List<InteractiveObject> makeInteractiveObjectList(String field) {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
    
    public static String printInteractiveObjectList(List<InteractiveObject> interactiveObjects) {
        return EMPTY_IDENTIFIER + "\n";
    }
    
    public static List<LinkObject> makeLevelLinkList(String field) {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
    
    public static String printLevelLinkList(List<LinkObject> interactiveObjects) {
        return EMPTY_IDENTIFIER + "\n";
    }
    
    public static List<PointsBall> makePointsSourceList(String field) {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
    
    public static String printPointsSourceList(List<PointsBall> interactiveObjects) {
        return EMPTY_IDENTIFIER + "\n";
    }
    
}
