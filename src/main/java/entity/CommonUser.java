package entity;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private int points;
    private int numberOfGames;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPoints() { return points; }

    @Override
    public void addPoints(int newpoints) {this.points += newpoints;}

    @Override
    public int getNumberOfGames() {return numberOfGames; }

    @Override
    public void addGame() {this.numberOfGames++;}

    @Override
    public String getPassword() {
        return password;
    }

}
