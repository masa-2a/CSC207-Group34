package use_case.menu;

public interface MenuOutputBoundary {
    /**
     * Prepares the success view for the Menu Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(MenuOutputData outputData);

    /**
     * Switches to the NewRound View.
     */
    void switchToNewRoundView();
    /**
     * Switches to the Leaderboard View.
     */
    void switchToLeaderboardView();
    /**
     * Switches to the LogoutView.
     */
    void switchToLogoutView();
}
