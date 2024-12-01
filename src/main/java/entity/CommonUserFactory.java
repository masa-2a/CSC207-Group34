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
     * @return the new user
     */
    @Override
    public User create(final String name, final String password) {
        return new CommonUser(name, password);
    }

    /**
     * Creates a new CommonUser with the given parameters.
     *
     * @param name        the name of the new common user
     * @param password    the password of the new common user
     * @param points    the points of the new common user
     * @param numberOfGames     the number of games of the new common user
     * @return the new common user
     */
    @Override
    public CommonUser create(final String name,
                             final String password,
                             final int points,
                             final int numberOfGames) {
        return new CommonUser(name, password, points, numberOfGames);
    }
}
