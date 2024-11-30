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
    ArrayList<CommonUser> returnAllUsers();

    /**
     * Returns the current username
     * @return String currentusername
     */
    String getCurrentUsername();
}
