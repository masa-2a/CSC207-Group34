package use_case.leaderboard;

import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LeaderboardOutputData {

    private ArrayList<User> topUsers;
    private int currentUserRank;

    public LeaderboardOutputData(ArrayList<User> topUsers, int currentUserRank) {
        this.topUsers = topUsers;
        this.currentUserRank = currentUserRank;
    }

    public ArrayList<User> getTopUsers() {
        return topUsers;
    }

    public int getCurrentUserRank() {
        return currentUserRank;
    }
}
