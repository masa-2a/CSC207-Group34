package use_case.leaderboard;

import entity.CommonUser;

import java.util.Map;

public class LeaderboardOutputData {

    private Map<Integer, CommonUser> topUsers;
    private int currentUserRank;
    private String currentUsername;
    private int currentUserPoints;

    public LeaderboardOutputData(Map<Integer, CommonUser> topUsers, int currentUserRank, String currentUsername, int currentUserPoints) {
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
