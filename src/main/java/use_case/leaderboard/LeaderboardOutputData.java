package use_case.leaderboard;

import java.util.Map;

import CommonUser;

/**
 * Holds output data for leaderboard use case.
 */
public class LeaderboardOutputData {

    private final Map<Integer, CommonUser> topUsers;
    private final int currentUserRank;
    private final String currentUsername;
    private final int currentUserPoints;

    public LeaderboardOutputData(Map<Integer,
            CommonUser> topUsers,
                                 int currentUserRank,
                                 String currentUsername,
                                 int currentUserPoints) {
        this.topUsers = topUsers;
        this.currentUserRank = currentUserRank;
        this.currentUsername = currentUsername;
        this.currentUserPoints = currentUserPoints;
    }

    public Map<Integer, CommonUser> getTopUsers() {
        return topUsers;
    }

    public int getCurrentUserRank() {
        return currentUserRank;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public int getCurrentUserPoints() {
        return currentUserPoints;
    }
}
