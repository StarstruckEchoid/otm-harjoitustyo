/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.nio.file.Path;
import java.util.List;

/**
 * A data access object interface for saving and loading files in (presumably) a
 * folder.
 *
 * @author Juho Gröhn
 */
public interface FileDao {

    public List<Path> loadFiles();

    public void saveFile(String user);
}
