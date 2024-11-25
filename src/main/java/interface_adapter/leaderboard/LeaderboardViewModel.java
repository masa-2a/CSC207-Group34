package interface_adapter.leaderboard;
import interface_adapter.ViewModel;

/**
 *  The View model for the leaderboard.
 */
public class LeaderboardViewModel extends ViewModel<LeaderboardState> {
    public static final String BACK_TO_MENU_BUTTON = "Back";
    public static final String TITLE_LABEL = "Leaderboard";
    public static final String YOU_LABEL = "You";
    public static String FIRST_PLACE_NAME;
    public static String SECOND_PLACE_NAME;
    public static String THIRD_PLACE_NAME;
    public static String CURRENT_USERNAME;
    public static String CURRENT_USER_PLACE;
    public static String CURRENT_USER_POINTS;
    public static String FIRST_PLACE_POINTS;
    public static String SECOND_PLACE_POINTS;
    public static String THIRD_PLACE_POINTS;

    public LeaderboardViewModel() {
        super(TITLE_LABEL);
        setState(new LeaderboardState());
    }


    public void setCurrentUserStats(Integer currentUserRank, String currentUsername, Integer averagePoints) {
        CURRENT_USERNAME = currentUsername;
        CURRENT_USER_PLACE = String.valueOf(currentUserRank);
        CURRENT_USER_POINTS = String.valueOf(averagePoints);
    }

    public void setFirstPlaceStats(String name, int averagePoints) {
        FIRST_PLACE_NAME = name;
        FIRST_PLACE_POINTS = String.valueOf(averagePoints);
    }

    public void setSecondPlaceStats(String name, int averagePoints) {
        SECOND_PLACE_NAME = name;
        SECOND_PLACE_POINTS = String.valueOf(averagePoints);
    }

    public void setThirdPlaceStats(String name, int averagePoints) {
        THIRD_PLACE_NAME = name;
        THIRD_PLACE_POINTS = String.valueOf(averagePoints);
    }
}
