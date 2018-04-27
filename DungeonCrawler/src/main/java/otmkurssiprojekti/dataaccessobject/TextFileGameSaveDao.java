/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.nio.file.Path;

public class TextFileGameSaveDao extends AbstractGameSaveDao {
    
    public TextFileGameSaveDao(Path directory) {
        super(directory, new TextFileLevelDao(directory));
    }
    
}
