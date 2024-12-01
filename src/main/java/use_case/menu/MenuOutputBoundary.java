package use_case.menu;

/**
 * Output Boundary for the Menu Use Case.
 */
public interface MenuOutputBoundary {
    /**
     * Prepares the success view for the Menu Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(MenuOutputData outputData);

    /**
     * Switches to the NewRound View.
     * @param menuOutputData the output data
     */
    void switchToNewRoundView(MenuOutputData menuOutputData);

    /**
     * Switches to the Leaderboard View.
     */
    void switchToLeaderboardView();

    /**
     * Switches to the LogoutView.
     */
    void switchToLogoutView();
}
