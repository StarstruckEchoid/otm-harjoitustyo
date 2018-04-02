/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juho Gröhn
 */
public class FakeUserDao implements UserDao {

    private static List<String> LIST = new ArrayList<>();
    static {
        LIST.add("foo");
        LIST.add("bar");
    }

    @Override
    public List<String> loadUsers() {
        return LIST;
    }

    @Override
    public void saveUser(String user) {
        LIST.add(user);
    }

}
