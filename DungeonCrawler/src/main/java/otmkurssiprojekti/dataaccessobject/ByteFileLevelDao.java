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
import java.util.logging.Level;
import java.util.logging.Logger;
import otmkurssiprojekti.level.GameLevel;
import otmkurssiprojekti.utilityclasses.Serializer;

/**
 *
 * @author Juho Gröhn
 */
public class ByteFileLevelDao extends AbstractLevelDao implements GameLevelDao {

    public ByteFileLevelDao(Path directory) {
        super(directory);
    }

    @Override
    public void saveLevel(GameLevel level) {
        try {
            Path levelPath = Paths.get(directory.toString(), level.getLevelName());
            byte[] bytedata = Serializer.serialize(level);
            Files.write(levelPath, bytedata);
        } catch (IOException ex) {
            Logger.getLogger(ByteFileLevelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GameLevel loadLevel(Path levelPath) {
        try {
            byte[] byteData = Files.readAllBytes(levelPath);
            GameLevel glvl = (GameLevel) Serializer.deserialize(byteData);
            return glvl;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ByteFileLevelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void saveLevel(GameLevel level, String name) {
        try {
            Path levelPath = Paths.get(directory.toString(), name);
            byte[] bytedata = Serializer.serialize(level);
            Files.write(levelPath, bytedata);
        } catch (IOException ex) {
            Logger.getLogger(ByteFileLevelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
