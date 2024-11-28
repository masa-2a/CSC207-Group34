package interface_adapter.leaderboard;
import interface_adapter.ViewModel;

/**
 *  The View model for the leaderboard.
 */
public class LeaderboardViewModel extends ViewModel<LeaderboardState> {
    public static final String BACK_TO_MENU_BUTTON = "Back";
    public static final String TITLE_LABEL = "Leaderboard";
    public static final String YOU_LABEL = "You";
    private static String firstPlaceName;
    private static String secondPlaceName;
    private static String thirdPlaceName;
    private static String currentUsername;
    private static String currentUserPlace;
    private static String currentUserPoints;
    private static String firstPlacePoints;
    private static String secondPlacePoints;
    private static String thirdPlacePoints;

    public LeaderboardViewModel() {
        super("leaderboard");
        setState(new LeaderboardState());
    }

    public static String getFirstPlacePoints() {
        return firstPlacePoints;
    }

    public static void setFirstPlacePoints(String firstPlacePoints) {
        LeaderboardViewModel.firstPlacePoints = firstPlacePoints;
    }

    public static String getSecondPlacePoints() {
        return secondPlacePoints;
    }

    public static String getThirdPlacePoints() {
        return thirdPlacePoints;
    }

    public static String getThirdPlaceName() {
        return thirdPlaceName;
    }

    public static String getSecondPlaceName() {
        return secondPlaceName;
    }

    public static String getFirstPlaceName() {
        return firstPlaceName;
    }

    public static String getCurrentUserPlace() {
        return currentUserPlace;
    }

    public static String getCurrentUserPoints() {
        return currentUserPoints;
    }


    public static void setCurrentUserStats(Integer currentUserRank, String currentUsername, Integer averagePoints) {
        System.out.println("just set current user stats"+currentUsername+"with"+currentUserRank);
        LeaderboardViewModel.currentUsername = currentUsername;
        currentUserPlace = String.valueOf(currentUserRank);
        currentUserPoints = String.valueOf(averagePoints);
    }

    public static void setFirstPlaceStats(String name, int averagePoints) {
        System.out.println("just set first place stats with "+firstPlaceName +" at "+firstPlacePoints);
        firstPlaceName = name;
        setFirstPlacePoints(String.valueOf(averagePoints));
    }

    public static void setSecondPlaceStats(String name, int averagePoints) {
        System.out.println("just set second place stats with "+secondPlaceName +" at "+secondPlacePoints);
        secondPlaceName = name;
        secondPlacePoints = String.valueOf(averagePoints);
    }

    public static void setThirdPlaceStats(String name, int averagePoints) {
        System.out.println("just set third place stats with "+thirdPlaceName +" at "+thirdPlacePoints);
        thirdPlaceName = name;
        thirdPlacePoints = String.valueOf(averagePoints);
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

}
