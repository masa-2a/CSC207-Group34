package use_case.leaderboard;

import entity.CommonUser;

import java.util.Map;

public class LeaderboardOutputData {

    private Map<Integer, CommonUser> topUsers;
    private int currentUserRank;
    private String currentUsername;

    public LeaderboardOutputData(Map<Integer, CommonUser> topUsers, int currentUserRank, String currentUsername) {
        this.topUsers = topUsers;
        this.currentUserRank = currentUserRank;
        this.currentUsername = currentUsername;
    }

    public Map<Integer, CommonUser> getTopUsers() {
        return topUsers;
    }

    public int getCurrentUserRank() {
        return currentUserRank;
    }

    public String getCurrentUsername() { return currentUsername; }
}
