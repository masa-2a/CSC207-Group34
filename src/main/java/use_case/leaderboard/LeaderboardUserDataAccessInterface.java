package use_case.leaderboard;

import entity.CommonUser;

import java.util.List;

public interface LeaderboardUserDataAccessInterface {
    /**
     * Returns a list of all the users in the database
     * @return List<CommonUser>
     */
    List<CommonUser> returnAllUsers();
}
