/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.TextUI;

/**
 *
 * @author gjuho
 */
public class TextUITest {

    public TextUITest() {
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
    public void sanityCheck() {
        char[][] matrix = new char[][]{
            " z ".toCharArray(),
            "a  ".toCharArray(),
            "   ".toCharArray()
        };
        char result = matrix[0][1];

        assertTrue("In coordinates (0,1) we expected z but got " + result, result == 'z');
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

    public static void testProject(char[][][] projectee, char[][] correct) {
        String correctString = Arrays.deepToString(correct);
        String projectionString = Arrays.deepToString(TextUI.project(projectee));

        assertTrue("Char matrix was projected to " + projectionString + " when what should have been projected was " + correctString + ".", correctString.equals(projectionString));
    }

}
