package entity;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private String name = "";
    private String password = "";
    private int points;
    private int numberOfGames;

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public CommonUser(String name, String password, int points, int numberOfGames) {
        this.name = name;
        this.password = password;
        this.points = points;
        this.numberOfGames = numberOfGames;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPoints() { return points; }

    @Override
    public void addEarnedPoints(int newpoints) {this.points += newpoints;}

    @Override
    public int getNumberOfGames() {return numberOfGames; }

    @Override
    public void addGame() {this.numberOfGames++;}

    @Override
    public String getPassword() {
        return password;
    }
}
