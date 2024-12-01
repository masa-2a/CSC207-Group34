package use_case.leaderboard;

/**
 * This class contains the input data required for the leaderboard use case.
 */
public class LeaderboardInputData {
    private String username;

    public LeaderboardInputData(String username) {
        this.setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
