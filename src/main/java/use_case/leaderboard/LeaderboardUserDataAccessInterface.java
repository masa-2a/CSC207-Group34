package use_case.leaderboard;

import java.util.ArrayList;

import entity.CommonUser;

/**
 * Interface for the leaderboard user data access.
 */
public interface LeaderboardUserDataAccessInterface {
    /**
     * Returns a list of all the users in the database.
     * @return ArrayList
     */
    ArrayList<CommonUser> returnAllUsers();

    /**
     * Returns the current username.
     * @return String currentusername
     */
    String getCurrentUsername();
}
