package use_case.leaderboard;

import entity.CommonUser;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public interface LeaderboardUserDataAccessInterface {
    /**
     * Returns a list of all the users in the database
     * @return List<CommonUser>
     */
    List<CommonUser> returnAllUsers();

    /**
     * Returns a list of the top 3 users based on points
     * @return List<CommonUser> topUsers
     */
    ArrayList<CommonUser> topUsers();

    /**
     * Returns the current logged-in User
     * @return CommonUser currentUser
     */
    User getCurrentUser();

    /**
     * Returns the current username
     * @return String currentusername
     */
    String getCurrentUsername();
}
