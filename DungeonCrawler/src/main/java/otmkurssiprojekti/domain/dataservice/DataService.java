/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.dataservice;

import java.util.List;
import otmkurssiprojekti.dataaccessobject.dataobject.GameSave;
import otmkurssiprojekti.domain.level.GameLevel;

/**
 * A data service is the class that performs calls to data access objects. The
 * point of DataService is to allow for all disc operations of DungeonCrawler to
 * be performed by a single object.
 *
 * @author Juho Gröhn
 */
public interface DataService {

    /**
     * Fetch a game level by name. Note that levelsDir has to be set beforehand.
     *
     * @param levelName Name of the level to be fetched.
     * @return Returns the GameLevel by this name.
     */
    public GameLevel fetchGameLevel(String levelName);

    /**
     * Fetch a particular game save.
     *
     * @param saveName The name of the game save. NB! name of the file, not
     * toString()!
     * @return Returns the GameSave by this name.
     */
    GameSave fetchGameSave(String saveName);

    /**
     * Fetch the GameSave that is currently loaded.
     *
     * @return Current game save.
     */
    GameSave fetchGameSave();

    /**
     * Saves the game of the player. Note that player as well as game level has
     * to be set beforehand.
     *
     */
    void saveGame();

    /**
     * Returns the list of game saves for the player. Note that player name has
     * to be set beforehand.
     *
     * @return The list of game saves for the player.
     */
    List<GameSave> fetchGameSaves();

    /**
     * Returns the list of players for the user. Note that username has to be
     * set beforehand.
     *
     * @return The list of players for the user.
     */
    List<String> fetchPlayers();

    /**
     * Fetches the names of the users. Note that usersDir has to be set
     * beforehand.
     *
     * @return The list of usernames.
     */
    List<String> fetchUsers();

    /**
     * Sets the currently loaded game save. Note that player name has to be set
     * beforehand.
     *
     * @param saveName The name of the GameSave.
     */
    void setGameSave(String saveName);

    /**
     * Set the currently loaded game level.
     *
     * @param gameLevel The name of the GameLevel.
     */
    void setGameLevel(GameLevel gameLevel);

    /**
     * Sets the default directory for game levels.
     *
     * @param levelsDir The default directory from where levels are fetched.
     */
    void setLevelsDir(String levelsDir);

    /**
     * Sets the name of the player. Note that user has to be set before calling
     * this method.
     *
     * @param playerName The name of the player currently being played as.
     */
    void setPlayer(String playerName);

    /**
     * Sets the name of the user currently playing. Note that UsersDir has to be
     * set before calling this method.
     *
     * @param userName The name of the user currently playing.
     */
    void setUser(String userName);

    /**
     * Sets the default directory from where users are found. This should be
     * done shortly after object creation.
     *
     * @param usersDir The string representation of the path in which users are
     * stored.
     */
    void setUsersDir(String usersDir);

}
