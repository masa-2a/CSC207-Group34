package use_case.leaderboard;

import entity.CommonUser;
import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeaderboardOutputData {

    private Map<Integer, CommonUser> topUsers;
    private int currentUserRank;

    public LeaderboardOutputData(Map<Integer, CommonUser> topUsers, int currentUserRank) {
        this.topUsers = topUsers;
        this.currentUserRank = currentUserRank;
    }

    public Map<Integer, CommonUser> getTopUsers() {
        return topUsers;
    }

    public int getCurrentUserRank() {
        return currentUserRank;
    }
}
