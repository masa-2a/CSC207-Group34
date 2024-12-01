package use_case.leaderboard;

/**
 * Output boundary for leaderboard Usecase.
 */
public interface LeaderboardOutputBoundary {

    /**
     * Prepares leaderboard success view.
     *
     * @param outputData for leaderboard.
     */
    void prepareSuccessView(LeaderboardOutputData outputData);

    /**
     * Prepares failed view.
     *
     * @param errorMessage for failed view.
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to menu view.
     */
    void switchToMenuView();
}
