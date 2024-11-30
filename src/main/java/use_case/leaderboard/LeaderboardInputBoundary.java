package use_case.leaderboard;

/**
 * Input boundary for actions which are related to the leaderboard usecase.
 */
public interface LeaderboardInputBoundary {
    /**
     * Executes leaderboard usecase.
     *
     */
    void execute();

    /**
     * Executes the switch to the main menu
     */
    void switchToMenuView();
}
