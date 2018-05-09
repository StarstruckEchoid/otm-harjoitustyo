/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otmkurssiprojekti.domain.dataservice;

import java.io.IOException;
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
     * Fetches the current gameLevel. Note that current level has to be set
     * beforehand.
     *
     * @return Returns the current GameLevel stored in DataService.
     */
    public GameLevel fetchGameLevel();

    /**
     * Saves the game of the player. Note that player as well as game level has
     * to be set beforehand.
     *
     * @param gameLevel The GameLevel to be saved in the GameSave.
     * @throws java.io.IOException Throw this if gameLevel could not be saved.
     * This may happen, for example, because player directory is null.
     */
    void saveGame(GameLevel gameLevel) throws IOException;

    /**
     * Returns the list of game saves for the player. Note that player name has
     * to be set beforehand.
     *
     * @return The list of game saves for the player.
     * @throws java.io.IOException Throw this if could not load game saves.
     */
    List<GameSave> fetchGameSaves() throws IOException;

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
     * Set the currently loaded game level.
     *
     * @param levelName The name of the GameLevel.
     * @throws java.io.IOException Throw this if could not swap to a level with
     * this name.
     */
    void swapLevel(String levelName) throws IOException;

    /**
     * Sets the default directory for game levels.
     *
     * @param levelsDir The default directory from where levels are fetched.
     * @throws java.io.IOException If levelsDir could not be set, for example
     * because root directory is null, throw this. Very unlikely to happen.
     */
    void setLevelsDir(String levelsDir) throws IOException;

    /**
     * Sets the name of the player. Note that user has to be set before calling
     * this method.
     *
     * @param playerName The name of the player currently being played as.
     * @throws java.io.IOException If a player by this name could not be
     * created, for example because user hadn't been set beforehand, throw this.
     */
    void setPlayer(String playerName) throws IOException;

    /**
     * Sets the name of the user currently playing. Note that UsersDir has to be
     * set before calling this method.
     *
     * @param userName The name of the user currently playing.
     * @throws java.io.IOException Throw this if user could not be set, for
     * example because usersDir has not been set.
     */
    void setUser(String userName) throws IOException;

    /**
     * Sets the default directory from where users are found. This should be
     * done shortly after object creation.
     *
     * @param usersDir The string representation of the path in which users are
     * stored.
     * @throws java.io.IOException Throws this if usersDir could not be set, for
     * example when root directory is null. Very unlikely to happen.
     */
    void setUsersDir(String usersDir) throws IOException;

    /**
     * Sets the current game level. NB! levelsDir has to be set beforehand!
     *
     * @param levelName The name of the level to be set.
     * @throws java.io.IOException If level could not be set, throw this. This
     * may happen for example because of a misspelled name or the level file
     * simply not existing.
     */
    void setCurrentLevel(String levelName) throws IOException;

    /**
     * Sets the current game level directly.
     *
     * @param gameLevel The GameLevel to be set.
     */
    public void setCurrentLevel(GameLevel gameLevel);

}
