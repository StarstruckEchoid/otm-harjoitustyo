/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.DataAccessObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import otmkurssiprojekti.Level.GameLevel;

/**
 *
 * @author Juho Gröhn
 */
public class ByteFileLevelDao implements LevelDao {

    private final Path defaultSource;

    private static byte[] serialize(GameLevel obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        byte[] value = out.toByteArray();
        os.close();
        out.close();
        return value;
    }

    private static GameLevel deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        GameLevel value = (GameLevel) is.readObject();
        is.close();
        in.close();
        return value;
    }

    public ByteFileLevelDao(Path source) {
        this.defaultSource = source;
    }

    @Override
    public GameLevel loadLevel(Path user, String levelName) {

        Path gamePath = Paths.get(user.toString(), levelName);
        if (!Files.exists(gamePath)) {
            gamePath = Paths.get(defaultSource.toString(), levelName);
        }

        try {
            byte[] byteData = Files.readAllBytes(gamePath);
            GameLevel glvl = deserialize(byteData);
            return glvl;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ByteFileLevelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void saveLevel(Path user, GameLevel level) {
        try {
            Path gamePath = Paths.get(user.toString(), level.getLevelName());
            byte[] bytedata = serialize(level);
            Files.write(gamePath, bytedata);
        } catch (IOException ex) {
            Logger.getLogger(ByteFileLevelDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
