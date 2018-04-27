/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juho Gröhn
 */
public class FileUserDaoTest {

    private FileUserDao fudao;
    private Path sub;
    private Path path;

    public FileUserDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        path = Files.createTempDirectory("testFolder");
        sub = Files.createTempDirectory(path, "testSubfolder");
        fudao = new FileUserDao(sub);
    }

    @After
    public void tearDown() throws IOException {
    }

    @Test
    public void testSaveAndLoad1() {
        String user = "Test Testington";
        fudao.saveUser(user);

        List<String> userList = fudao.loadUsers().stream()
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toList());
        assertTrue("Expected list to contain " + Paths.get(user).toString() + " but list onlu had " + userList.toString(), userList.contains(user));
    }

    @Test
    public void testSaveAndLoad2() {
        String user0 = "Alice";
        String user1 = "Bob";
        String user2 = "Caroline";
        fudao.saveUser(user0);
        fudao.saveUser(user1);
        fudao.saveUser(user2);

        List<String> userList = fudao.loadUsers().stream()
                .map(p -> p.getFileName().toString())
                .collect(Collectors.toList());

        assertTrue(userList.contains(user0));
        assertTrue(userList.contains(user2));
        assertTrue(userList.contains(user1));
    }

    @Test
    public void testFail() throws IOException {
        Path broke = Files.createTempFile("broke", "txt");

        try {
            UserDao brokeDao = new FileUserDao(broke);
            assertTrue("FileUserDao accepts invalid path" + broke.toString() + "in constructor.", false);
        } catch (IllegalArgumentException iae) {
            assertTrue(true);
        }
        Files.deleteIfExists(broke);
    }

}
