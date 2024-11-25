package use_case.leaderboard;

/**
 * Input boundary for actions which are related to the leaderboard usecase.
 */
public interface LeaderboardInputBoundary {
    /**
     * Executes leaderboard usecase.
     * @param inputData the input data
     */
    void execute(LeaderboardInputData inputData);

    /**
     * Executes the switch to the main menu
     */
    void switchToMenuView();
}
