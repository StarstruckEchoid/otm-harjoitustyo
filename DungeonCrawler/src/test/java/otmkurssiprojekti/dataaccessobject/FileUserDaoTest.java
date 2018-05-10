/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.dataaccessobject;

import java.io.IOException;
import static java.nio.file.Files.createTempDirectory;
import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.deleteIfExists;
import java.nio.file.Path;
import static java.nio.file.Paths.get;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Juho Gröhn
 */
public class FileUserDaoTest {

    private BasicFileDao fudao;
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
        path = createTempDirectory("testFolder");
        sub = createTempDirectory(path, "testSubfolder");
        fudao = new BasicFileDao(sub);
    }

    @After
    public void tearDown() throws IOException {
    }

    @Test
    public void testSaveAndLoad1() {
        String user = "Test Testington";
        fudao.saveFile(user);

        List<String> userList = fudao.loadFiles();
        assertTrue("Expected list to contain " + get(user).toString() + " but list onlu had " + userList.toString(), userList.contains(user));
    }

    @Test
    public void testSaveAndLoad2() {
        String user0 = "Alice";
        String user1 = "Bob";
        String user2 = "Caroline";
        fudao.saveFile(user0);
        fudao.saveFile(user1);
        fudao.saveFile(user2);

        List<String> userList = fudao.loadFiles();

        assertTrue(userList.contains(user0));
        assertTrue(userList.contains(user2));
        assertTrue(userList.contains(user1));
    }

    @Test
    public void testFail() throws IOException {
        Path broke = createTempFile("broke", "txt");

        try {
            FileDao brokeDao = new BasicFileDao(broke);
            assertTrue("FileUserDao accepts invalid path" + broke.toString() + "in constructor.", false);
        } catch (IllegalArgumentException iae) {
            assertTrue(true);
        }
        deleteIfExists(broke);
    }

}
