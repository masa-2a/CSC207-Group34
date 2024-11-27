package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password) {
        return new CommonUser(name, password);
    }
    public User create(String name, String password, int points, int numberOfGames) { return new CommonUser(name, password, points, numberOfGames); }
}
