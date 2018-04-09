/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.DataAccessObject;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juho Gröhn
 */
public class FakeUserDao implements UserDao {

    private static final List<Path> LIST = new ArrayList<>();

    static {
        LIST.add(Paths.get("foo"));
        LIST.add(Paths.get("bar"));
    }

    @Override
    public List<Path> loadUsers() {
        return LIST;
    }

    @Override
    public void saveUser(String user) {
        LIST.add(Paths.get(user));
    }

}
