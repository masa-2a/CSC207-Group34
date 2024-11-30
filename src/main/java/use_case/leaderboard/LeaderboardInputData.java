package use_case.leaderboard;

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
