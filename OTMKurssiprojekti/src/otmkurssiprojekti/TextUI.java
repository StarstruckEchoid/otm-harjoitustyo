/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti;

import otmkurssiprojekti.Level.TopDownObject;

/**
 *
 * @author gjuho
 */
public class TextUI {

    public static char[][] project(char[][][] levelData) {
        int xLim = levelData[0][0].length;
        int yLim = levelData[0].length;
        int zLim = levelData.length;

        char[][] projection = new char[xLim][yLim];

        for (int y = 0; y < yLim; y++) {
            for (int x = 0; x < xLim; x++) {
                for (int z = 0; z < zLim; z++) {
                    char tdlo = levelData[x][y][z];
                    if (tdlo != ' ') {
                        projection[x][y] = tdlo;
                        break;
                    }
                }
            }
        }
        return projection;
    }

}
