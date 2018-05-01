/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juho Gröhn
 */
public class BasicFileDao implements FileDao {

    private final Path source;

    /**
     * The FileUserDao fetches subdirectories, named after users, from a source
     * directory.
     *
     * @param source The source directory. Most likely something like
     * "../DungeonCrawler/users".
     */
    public BasicFileDao(Path source) {
        if (!source.toFile().isDirectory()) {
            throw new IllegalArgumentException();
        }
        this.source = source;
    }

    @Override
    public List<String> loadFiles() {
        List<String> users = new ArrayList<>();
        File[] subdirs = source.toFile().listFiles(f -> f.isDirectory());
        for (File f : subdirs) {
            users.add(f.getName());
        }
        return users;
    }

    @Override
    public void saveFile(String name) {
        File userFile = new File(source.toFile(), name);
        userFile.mkdirs();
    }
}
