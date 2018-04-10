/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UtilityClasses;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.Level.GameLevel;

/**
 *
 * @author gjuho
 */
public class SerializerTest {

    private GameLevel glvl;

    public SerializerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        glvl = new GameLevel("beta", null, new ArrayList<>(), null, null, null, null);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGameLevels() {
        testSerializeDeserialize(glvl);
    }

    public void testSerializeDeserialize(Object o) {
        try {
            byte[] bytes = Serializer.serialize(o);
            Object copy = Serializer.deserialize(bytes);
            assertTrue(o.equals(copy));
        } catch (IOException ex) {
            assertTrue("Threw and IOException when trying to serialize " + o.toString(), false);
        } catch (ClassNotFoundException ex) {
            assertTrue("Threw an exception when trying to deserialize bytes of " + o.toString(), false);
        } catch (AssertionError ex) {
            assertTrue(
                    "Threw an assertion error when trying to serialize or deserialize " + o.toString()
                    + "\nHave you implemented a parameterless constructor, equals, and toString?", false);
        }
    }

}
