package use_case.leaderboard;

/**
 * Output boundary for actions which are related to the leaderboard usecase.
 */
public interface LeaderboardOutputBoundary {

    /**
     * Prepares the success view.
     * @param outputData the output data
     */
    void prepareSuccessView(LeaderboardOutputData outputData);

    /**
     * Prepares the fail view.
     * @param errorMessage the error message
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the main menu view.
     */
    void switchToMenuView();
}
