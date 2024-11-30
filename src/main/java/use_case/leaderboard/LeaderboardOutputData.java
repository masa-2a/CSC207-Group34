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
        for( Map.Entry<Integer, CommonUser> entry : topUsers.entrySet() ) {
            System.out.println( entry.getKey() + " : " + entry.getValue() );
        }
        return topUsers;
    }

    public int getCurrentUserRank() {
        System.out.println("current user rank"+ currentUserRank);
        return currentUserRank;
    }

    public String getCurrentUsername() {
        System.out.println("current username "+ currentUsername);
        return currentUsername;
    }

    public int getCurrentUserPoints() {
        System.out.println("current user points"+ currentUserPoints);
        return currentUserPoints;
    }
}
