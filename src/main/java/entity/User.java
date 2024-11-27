package entity;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the points of the users
     * @return the points of the user
     */
    int getPoints();

    void addEarnedPoints(int newpoints);

    /**
     * Return the number of games played
     * @return the number of games played
     */
    int getNumberOfGames();

    void addGame();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();
}
