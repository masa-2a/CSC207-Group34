package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    /**
     * Creates a new CommonUser with the given paramters.
     *
     * @param name     the name of the new user
     * @param password the password of the new user
     * @return
     */
    @Override
    public User create(final String name, final String password) {
        return new CommonUser(name, password);
    }

    /**
     * Creates a new CommonUser with the given parameters.
     *
     * @param name
     * @param password
     * @param points
     * @param numberOfGames
     * @return
     */
    @Override
    public CommonUser create(final String name,
                             final String password,
                             final int points,
                             final int numberOfGames) {
        return new CommonUser(name, password, points, numberOfGames);
    }
}
