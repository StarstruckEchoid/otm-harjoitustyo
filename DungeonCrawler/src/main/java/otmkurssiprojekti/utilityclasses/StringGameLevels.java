/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import static java.lang.String.copyValueOf;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import otmkurssiprojekti.domain.gameobject.archetypes.BlockArchetype;
import static otmkurssiprojekti.domain.gameobject.archetypes.BlockArchetype.AIR;
import otmkurssiprojekti.domain.gameobject.gameinanimates.Block;
import otmkurssiprojekti.domain.gameobject.gameinanimates.InteractiveObject;
import otmkurssiprojekti.domain.gameobject.gameinanimates.LevelLink;
import otmkurssiprojekti.domain.gameobject.gameinanimates.PointsBall;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.NonPlayerCharacter;
import otmkurssiprojekti.domain.gameobject.interfaces.derivatives.PlayerCharacter;
import otmkurssiprojekti.domain.gameobject.location.Coords;
import static otmkurssiprojekti.domain.gameobject.location.Direction.DOWN;
import otmkurssiprojekti.domain.level.BasicGameLevel;
import otmkurssiprojekti.domain.level.GameLevel;
import static otmkurssiprojekti.utilityclasses.StringGameObjects.*;

/**
 * An utility class whose purpose is to convert GameLevels into Strings and
 * Strings into GameLevels.
 *
 * @author gjuho
 */
public class StringGameLevels {

    /**
     * The z coordinate on which ImmutableObjects are assumed to reside in.
     */
    public static final int BLOCKS_LEVEL = 1;

    /**
     * The string that identifies that the given field contains no values.
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
    public static GameLevel makeGameLevel(String string) throws IllegalArgumentException {
        String[] fields = string.split("\n\n");
        String levelName = fields[0];
        PlayerCharacter player = makePlayerCharacter(fields[1]).get();
        List<NonPlayerCharacter> npcs = makeNPCs(fields[2]);
        List<Block> blocks = makeBlocks(fields[3]);
        List<InteractiveObject> interactives = makeInteractiveObjects(fields[4]);
        List<LevelLink> levelLinks = makeLevelLinks(fields[5]);
        List<PointsBall> points = makePointsBalls(fields[6]);

        return new BasicGameLevel(levelName, player, npcs, blocks, interactives, levelLinks, points);
    }

    /**
     * Does the inverse operation of makeGameLevel:
     * {@link #makeGameLevel(java.lang.String)}.
     *
     * @param gameLevel The GameLevel to be converted.
     * @return Returns the string representation of the level data.
     */
    public static String printGameLevel(GameLevel gameLevel) {
        StringBuilder sb = new StringBuilder();
        sb.append(gameLevel.getLevelName()).append("\n\n");
        sb.append(printPlayerCharacter(gameLevel.getPlayer())).append("\n\n");
        sb.append(printNPCs(gameLevel.getNonPlayerCharacters())).append("\n");
        sb.append(printBlocks(gameLevel.getBlocks())).append("\n");
        sb.append(printInteractiveObjects(gameLevel.getInteractiveObjects())).append("\n");
        sb.append(printLevelLinks(gameLevel.getLevelLinks())).append("\n");
        sb.append(printPointsBalls(gameLevel.getPointsBalls()));

        return sb.toString();
    }

    /**
     * Makes a list of NonPlayerCharacters from a list of NonPlayerCharacter
     * string representations. The string representations are presumed to be
     * separated by line breaks.
     *
     * @param field The list of NonPlayerCharacters as a string.
     * @return The concrete list of NonPlayerCharacters.
     */
    public static List<NonPlayerCharacter> makeNPCs(String field) throws IllegalArgumentException {
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
     * Inverse operation of makeNPCs:{@link #makeNPCs(java.lang.String)}.
     *
     * @param npcs The list of NonPlayerCharacters.
     * @return String representation.
     */
    public static String printNPCs(List<NonPlayerCharacter> npcs) {
        if (npcs.isEmpty()) {
            return EMPTY_IDENTIFIER + "\n";
        }
        StringBuilder sb = new StringBuilder();
        npcs.forEach(npc -> sb.append(printNonPlayerCharacter(npc)).append("\n"));
        return sb.toString();
    }

    /**
     * Makes a list of ImmutableObjects based on the string input given. The
     * string input, exceptionally, is a matrix of characters, for example:
     * <pre>
     * 0...0000..0
     * 00..0..0..0
     * .00.00.00.0
     * .....0..0.0
     * 00.0.00...0
     * .0...0..000
     * ...0...00..
     * </pre> would make a valid block list consisting of immutable objects with
     * the identities '.' and '0'.
     *
     * @param field Input string.
     * @return List of ImmutableObjects.
     * @see Block
     */
    protected static List<Block> makeBlocks(String field) throws IllegalArgumentException {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return new ArrayList<>();
        }
        List<Block> blocks = new ArrayList<>();
        String[] rows = field.split("\n");
        for (int y = 0; y < rows.length; y++) {
            String row = rows[y];
            for (int x = 0; x < row.length(); x++) {
                String id = Character.toString(row.charAt(x));
                blocks.add(new Block(makeArcheType(BlockArchetype.class, id).orElse(AIR),
                        new Coords(x, y, BLOCKS_LEVEL), DOWN)
                );
            }
        }
        return blocks;
    }

    /**
     * Inverse operation of makeBlocks:{@link #makeBlocks(java.lang.String)}.
     *
     * @param blocks List of ImmutableObjects.
     * @return StringGameLevels compatible string representation.
     */
    protected static String printBlocks(List<Block> blocks) {
        if (blocks.isEmpty()) {
            return EMPTY_IDENTIFIER + "\n";
        }
        int biggestY = blocks.stream().mapToInt(o -> o.getCoords().getY()).max().orElse(0);
        int biggestX = blocks.stream().mapToInt(o -> o.getCoords().getX()).max().orElse(0);
        char[][] matrix = new char[biggestY + 1][biggestX + 1];
        blocks.forEach((block) -> {
            Coords blockCoords = block.getCoords();
            matrix[blockCoords.getY()][blockCoords.getX()] = block.getId();
        });
        StringBuilder sb = new StringBuilder();
        for (char[] line : matrix) {
            sb.append(copyValueOf(line));
            sb.append("\n");
        }
        return sb.toString();
    }

    protected static List<InteractiveObject> makeInteractiveObjects(String field) throws IllegalArgumentException {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return new ArrayList<>();
        }
        Stack<InteractiveObject> interactives = new Stack<>();
        String[] iaoDats = field.split("\n");
        for (String iaoDat : iaoDats) {
            if (iaoDat.startsWith("\t")) {
                makeInteractiveObject(iaoDat.substring(1)).ifPresent(iao -> interactives.peek().getChildren().add(iao));
            } else {
                makeInteractiveObject(iaoDat).ifPresent(iao -> interactives.push(iao));
            }
        }
        return interactives;
    }

    protected static String printInteractiveObjects(List<InteractiveObject> interactiveObjects) {
        if (interactiveObjects.isEmpty()) {
            return EMPTY_IDENTIFIER + "\n";
        }
        StringBuilder sb = new StringBuilder();
        for (InteractiveObject interactiveObject : interactiveObjects) {
            sb.append(printInteractiveObject(interactiveObject)).append("\n");
            for (InteractiveObject child : interactiveObject.getChildren()) {
                sb.append("\t").append(printInteractiveObject(child)).append("\n");
            }
        }
        return sb.toString();
    }

    protected static List<LevelLink> makeLevelLinks(String field) throws IllegalArgumentException {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return new ArrayList<>();
        }
        List<LevelLink> links = new ArrayList<>();
        String[] linkDats = field.split("\n");
        for (String linkDat : linkDats) {
            makeLinkObject(linkDat).ifPresent(i -> links.add(i));
        }
        return links;
    }

    protected static String printLevelLinks(List<LevelLink> linkObjects) {
        if (linkObjects.isEmpty()) {
            return EMPTY_IDENTIFIER + "\n";
        }
        StringBuilder sb = new StringBuilder();
        linkObjects.forEach(link -> sb.append(printLinkObject(link)).append("\n"));
        return sb.toString();
    }

    protected static List<PointsBall> makePointsBalls(String field) throws IllegalArgumentException {
        if (field.equals(EMPTY_IDENTIFIER)) {
            return new ArrayList<>();
        }
        List<PointsBall> balls = new ArrayList<>();
        String[] pointsDats = field.split("\n");
        for (String pointsDat : pointsDats) {
            makePointsBall(pointsDat).ifPresent(pb -> balls.add(pb));
        }
        return balls;
    }

    protected static String printPointsBalls(List<PointsBall> pointsBalls) {
        if (pointsBalls.isEmpty()) {
            return EMPTY_IDENTIFIER + "\n";
        } else {
            StringBuilder sb = new StringBuilder();
            pointsBalls.forEach(pb -> sb.append(printPointsBall(pb)).append("\n"));
            return sb.toString();
        }
    }

}
