/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import java.util.List;

/**
 *
 * @author Juho Gröhn
 */
public interface UserDao {
    public List<String> loadUsers();
    public void saveUser(String user);
}
