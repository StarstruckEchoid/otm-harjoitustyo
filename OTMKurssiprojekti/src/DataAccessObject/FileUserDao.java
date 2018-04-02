/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public FileUserDao(Path source) {
        this.source = source;
    }

    @Override
    public List<String> loadUsers() {
        List<String> users = new ArrayList<>();
        try {
            Files.lines(source).forEach(l -> users.add(l));
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public void saveUser(String user) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(source.toFile(), true));
            bw.write(user);
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
