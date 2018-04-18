/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.utilityclasses;

import otmkurssiprojekti.level.gameobjects.GameObject;

/**
 *
 * @author gjuho
 */
public class FormatConverter {

    public static char[][] project(char[][][] levelData) {
        int zLim = levelData.length;
        int yLim = levelData[0].length;
        int xLim = levelData[0][0].length;

        char[][] projection = new char[yLim][xLim];

        for (int x = 0; x < xLim; x++) {
            for (int y = 0; y < yLim; y++) {
                for (int z = 0; z < zLim; z++) {
                    projection[y][x] = ' ';
                    char tdlo = levelData[z][y][x];
                    if (tdlo != ' ') {
                        projection[y][x] = tdlo;
                        break;
                    }
                }
            }
        }
        return projection;
    }

    public static char[][][] StringToMatrix(String s) {
        String[] zLevels = s.split("\n\n");
        int zLim = zLevels.length;
        int yLim = zLevels[0].split("\n").length;
        int xLim = zLevels[0].split("\n")[0].length();

        char[][][] matrix = new char[zLim][yLim][xLim];
        for (int z = 0; z < zLim; z++) {
            for (int y = 0; y < yLim; y++) {
                matrix[z][y] = zLevels[z].split("\n")[y].toCharArray();
            }
        }
        return matrix;
    }

    public static char[][][] levelDataToMatrix(GameObject[][][] levelData) {
        int xLim = levelData[0][0].length;
        int yLim = levelData[0].length;
        int zLim = levelData.length;
        char[][][] matrix = new char[zLim][yLim][xLim];
        for (int z = 0; z < zLim; z++) {
            for (int y = 0; y < yLim; y++) {
                for (int x = 0; x < xLim; x++) {
                    GameObject go = levelData[z][y][x];
                    if (go == null) {
                        matrix[z][y][x] = ' ';
                    } else {
                        matrix[z][y][x] = go.getId();
                    }
                }
            }
        }
        return matrix;
    }

    public static String projectionToDenseString(char[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = matrix.length; i > 0; i--) {
            char[] row = matrix[i - 1];
            sb.append(">");
            sb.append(String.valueOf(row));
            sb.append("<\n");
        }
        return sb.toString();
    }


}
