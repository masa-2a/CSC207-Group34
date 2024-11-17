package use_case.menu;

import use_case.login.LoginInputData;

public interface MenuInputBoundary {
    /**
     * Executes the menu use case.
     * @param menuInputData the input data
     */
    void execute(MenuInputData menuInputData);
    /**
     * Executes the switch to Logout view use case.
     */
    void switchToLogoutView();
    /**
     * Executes the switch to new round view use case.
     */
    void switchToNewRoundView();
    /**
     * Executes the switch to Leaderboard view use case.
     */
    void switchToLeaderboardView();

}
