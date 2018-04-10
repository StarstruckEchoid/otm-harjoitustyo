/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.UtilityClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import otmkurssiprojekti.Level.GameLevel;
import otmkurssiprojekti.Level.GameObjects.NonPlayerCharacter;

/**
 *
 * @author gjuho
 */
public class SerializerTest {
    
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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGameLevels() {
        testSerializeDeserialize(new GameLevel("beta", null, new ArrayList<>(), null, null, null, null));
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
        }
    }
    
}
