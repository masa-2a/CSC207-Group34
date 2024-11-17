package use_case.menu;

public interface MenuOutputBoundary {

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
