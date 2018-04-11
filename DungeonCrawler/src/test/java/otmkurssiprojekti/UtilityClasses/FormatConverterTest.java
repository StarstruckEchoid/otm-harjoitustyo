package otmkurssiprojekti.UtilityClasses;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import otmkurssiprojekti.Level.GameObjects.Dependencies.Direction;
import otmkurssiprojekti.Level.GameObjects.Dependencies.Coords;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.Level.GameLevel;
import otmkurssiprojekti.Level.GameObjects.Archetypes.PlayerCharacterArchetype;
import otmkurssiprojekti.Level.GameObjects.PlayerCharacter;
import otmkurssiprojekti.Level.GameObjects.GameObject;

/**
 *
 * @author gjuho
 */
public class FormatConverterTest {

    public FormatConverterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void sanityCheck1() {
        char[][] matrix = new char[][]{
            " z ".toCharArray(),
            "a  ".toCharArray(),
            "   ".toCharArray()
        };
        char result = matrix[0][1];

        assertTrue("In coordinates (0,1) we expected z but got " + result, result == 'z');
    }

    @Test
    public void sanityCheck2() {
        char[][][] matrix = new char[][][]{
            new char[][]{
                " z ".toCharArray(),
                "a  ".toCharArray(),
                "   ".toCharArray()
            }, new char[][]{
                " bx".toCharArray(),
                " v ".toCharArray(),
                "  d".toCharArray()
            }
        };

        assertTrue("Expected z.", matrix[0][0][1] == 'z');
        assertTrue("Expected x.", matrix[1][0][2] == 'x');
        assertTrue("Expected v.", matrix[1][1][1] == 'v');
    }

    @Test
    public void sanityCheck3() {
        char[][][] matrix = new char[][][]{
            new char[][]{
                "fxxx".toCharArray(),
                "oxxx".toCharArray(),
                "oxxx".toCharArray()
            },
            new char[][]{
                "bxxx".toCharArray(),
                "axxx".toCharArray(),
                "rxxx".toCharArray()
            }
        };

        assertTrue("matrix[0][0].length has to be matrix widtht. 2. Now it is " + matrix[0][0].length, matrix[0][0].length == 4);
        assertTrue("matrix[0].length has to be matrix length, 3. Now it is " + matrix[0].length, matrix[0].length == 3);
        assertTrue("matrix.length has to be matrix depth, 1. Now it is " + matrix.length, matrix.length == 2);
    }

    @Test
    public void sanityCheck4() {
        char[] vector = "   ".toCharArray();
        assertTrue(vector.length == 3);
    }

    @Test
    public void testProject0() {
        char[][][] projectee = new char[3][3][1];
        projectee[0] = new char[][]{
            "abc".toCharArray(),
            "def".toCharArray(),
            "ghi".toCharArray()
        };

        testProject(projectee, projectee[0]);
    }

    @Test
    public void testProject1() {
        char[][][] projectee = new char[3][3][2];
        projectee[0] = new char[][]{
            "000".toCharArray(),
            "111".toCharArray(),
            "222".toCharArray()
        };
        projectee[1] = new char[][]{
            "   ".toCharArray(),
            "   ".toCharArray(),
            "   ".toCharArray()
        };

        testProject(projectee, projectee[0]);

    }

    @Test
    public void testProject2() {
        char[][][] projectee = new char[][][]{
            new char[][]{
                "00 0".toCharArray(),
                "0000".toCharArray(),
                "  00".toCharArray()
            }, new char[][]{
                "  1 ".toCharArray(),
                "    ".toCharArray(),
                "11  ".toCharArray()
            }};

        char[][] correct = new char[][]{
            "0010".toCharArray(),
            "0000".toCharArray(),
            "1100".toCharArray()
        };

        testProject(projectee, correct);
    }

    @Test
    public void testProject3() {
        char[][][] projectee = new char[][][]{
            new char[][]{
                "0   ".toCharArray(),
                "0   ".toCharArray(),
                "0   ".toCharArray()
            }, new char[][]{
                "1111".toCharArray(),
                "    ".toCharArray(),
                "1111".toCharArray()
            }, new char[][]{
                "2222".toCharArray(),
                "2222".toCharArray(),
                "2222".toCharArray()
            }
        };

        char[][] correct = new char[][]{
            "0111".toCharArray(),
            "0222".toCharArray(),
            "0111".toCharArray()
        };

        testProject(projectee, correct);
    }

    public static void testProject(char[][][] projectee, char[][] correct) {
        String projectionString = Arrays.deepToString(FormatConverter.project(projectee));
        String correctString = Arrays.deepToString(correct);

        assertTrue("Char matrix was projected to " + projectionString + " when what should have been projected was " + correctString + ".", correctString.equals(projectionString));
    }

    @Test
    public void testStringToMatrix1() {
        String testable
                = "111\n"
                + "222\n"
                + "333\n"
                + "\n"
                + "444\n"
                + "555\n"
                + "666";

        char[][][] correct = new char[][][]{
            new char[][]{
                "111".toCharArray(),
                "222".toCharArray(),
                "333".toCharArray()
            }, new char[][]{
                "444".toCharArray(),
                "555".toCharArray(),
                "666".toCharArray()
            }
        };

        testStringToMatrix(testable, correct);
    }

    @Test
    public void testStringToMatrix2() {
        String testable
                = "1111\n"
                + "2222\n"
                + "\n"
                + "3333\n"
                + "4444\n"
                + "\n"
                + "5555\n"
                + "6666";

        char[][][] correct = new char[][][]{
            new char[][]{
                "1111".toCharArray(),
                "2222".toCharArray()
            }, new char[][]{
                "3333".toCharArray(),
                "4444".toCharArray()
            }, new char[][]{
                "5555".toCharArray(),
                "6666".toCharArray()
            }
        };

        testStringToMatrix(testable, correct);
    }

    @Test
    public void testLevelDataToMatrix1() {
        GameLevel glvl = new GameLevel("TestLevel",
                new PlayerCharacter(PlayerCharacterArchetype.THIEF, new Coords(3, 2, 1), Direction.DOWN),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>());

        char[][][] matrix = FormatConverter.levelDataToMatrix(glvl.getLevelData());
        assertTrue(matrix[1][2][3] == '@');
    }

    @Test
    public void testLevelDataToMatrix2() {
        GameLevel glvl = new GameLevel("testLevel",
                new PlayerCharacter(PlayerCharacterArchetype.THIEF, new Coords(7, 0, 3), Direction.DOWN),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );

        char[][][] matrix = FormatConverter.levelDataToMatrix(glvl.getLevelData());
        assertTrue(matrix[3][0][7] == '@');
    }

    public static void testLevelDataToMatrix(GameObject[][][] levelData, char[][][] matrix) {
        char[][][] conversion = FormatConverter.levelDataToMatrix(levelData);

        assertTrue(Arrays.deepEquals(conversion, matrix));
    }

    public static void testStringToMatrix(String testable, char[][][] correct) {
        String testableString = Arrays.deepToString(FormatConverter.StringToMatrix(testable));
        String correctString = Arrays.deepToString(correct);

        assertTrue("String was projected to " + testableString + " when what should have been projected was " + correctString + ".", testableString.equals(correctString));
    }

}
