package interface_adapter.leaderboard;
import interface_adapter.ViewModel;

/**
 *  The View model for the leaderboard.
 */
public class LeaderboardViewModel extends ViewModel<LeaderboardState> {
    public static final String BACK_TO_MENU_BUTTON = "Back";
    public static final String TITLE_LABEL = "Leaderboard";
    public static final String YOU_LABEL = "You";


    public LeaderboardViewModel() {
        super("Leaderboard");
        setState(new LeaderboardState("Leaderboard View"));
    }

}
