package use_case.leaderboard;

import java.util.ArrayList;

import entity.User.CommonUser;

/**
 * Data Access interface for leaderboard Usecase.
 */
public interface LeaderboardUserDataAccessInterface {
    /**
     * Returns a list of all the users in the database.
     * @return List of common users
     */
    ArrayList<CommonUser> returnAllUsers();

    /**
     * Returns the current username.
     * @return String currentusername
     */
    String getCurrentUsername();
}
