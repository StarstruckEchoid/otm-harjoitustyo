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
public class FileUserDao implements UserDao {

    private final Path source;

    /**
     * The FileUserDao fetches subdirectories, named after users, from a source
     * directory.
     *
     * @param source
     */
    public FileUserDao(Path source) {
        if (!source.toFile().isDirectory()) {
            throw new IllegalArgumentException();
        }
        this.source = source;
    }

    @Override
    public List<Path> loadUsers() {
        List<Path> users = new ArrayList<>();
        File[] subdirs = source.toFile().listFiles(f -> f.isDirectory());
        for (File f : subdirs) {
            users.add(f.toPath());
        }
        return users;
    }

    @Override
    public void saveUser(String user) {
        File userFile = new File(source.toFile(), user);
        userFile.mkdirs();
    }
}
