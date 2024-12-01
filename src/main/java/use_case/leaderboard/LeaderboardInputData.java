package use_case.leaderboard;

/**
 * Input data for leaderboard usecase.
 */
public class LeaderboardInputData {
    private String username;

    /**
     * Create leaderboard input data instance.
     * @param username current logged in
     */
    public LeaderboardInputData(String username) {
        this.setUsername(username);
    }

    /**
     * Returns current username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets current username.
     * @param username current username
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
