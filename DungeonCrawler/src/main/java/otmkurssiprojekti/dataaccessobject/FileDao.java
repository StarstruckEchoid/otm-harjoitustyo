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

    /**
     *
     * @return Returns a list of paths to files. Presumably these paths are all
     * children of a given root.
     */
    public List<Path> loadFiles();

    /**
     *
     * @param name The name of the path to be saved. Presumably it will be saved
     * as the child of some given root.
     */
    public void saveFile(String name);
}
