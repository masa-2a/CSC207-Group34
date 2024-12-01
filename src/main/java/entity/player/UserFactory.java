package entity.player;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     * @param name the name of the new user
     * @param password the password of the new user
     * @return the new user
     */
    User create(String name, String password);

    /**
     * Creates a new User.
     * @param name the name of the new user
     * @param password the password of the new user
     * @param Points the points of the new user
     * @param numberOfGames the number of games of the new user
     * @return the new user
     */
    User create(String name, String password, int Points, int numberOfGames);

}
