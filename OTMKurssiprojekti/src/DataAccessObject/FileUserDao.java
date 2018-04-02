/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juho Gröhn
 */
public class FileUserDao implements UserDao {

    private final Path source;

    //The FileUserDao fetches subdirectories, named after users, from a source directory.
    public FileUserDao(Path source) {
        if (!source.toFile().isDirectory()) {
            throw new IllegalArgumentException();
        }
        this.source = source;
    }

    @Override
    public List<String> loadUsers() {
        List<String> users = new ArrayList<>();
        File[] subdirs = source.toFile().listFiles(f -> f.isDirectory());
        for (File f : subdirs) {
            users.add(f.getName());
        }
        return users;
    }

    @Override
    public void saveUser(String user) {
        File userFile = new File(source.toFile(), user);
        userFile.mkdirs();
    }
}
