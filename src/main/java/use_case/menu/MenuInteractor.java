package use_case.menu;

import interface_adapter.main_menu.MenuPresenter;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.leaderboard.LeaderboardOutputBoundary;

public class MenuInteractor implements MenuInputBoundary {
    private final MenuOutputBoundary menuPresenter;
    private final LeaderboardInputBoundary leaderboardInteractor;

    public MenuInteractor(MenuOutputBoundary menuOutputBoundary, LeaderboardInputBoundary leaderboardInteractor) {

        this.menuPresenter = menuOutputBoundary;
        this.leaderboardInteractor = leaderboardInteractor;
    }

    /**
     * Executes the menu use case.
     *
     * @param menuInputData
     */
    @Override
    public void execute(MenuInputData menuInputData) {
        leaderboardInteractor.execute();

    }

    /**
     * Executes the switch to Logout view use case.
     */
    @Override
    public void switchToLogoutView() {
        menuPresenter.switchToLogoutView();
    }

    /**
     * Executes the switch to new round view use case.
     */
    @Override
    public void switchToNewRoundView() {
        menuPresenter.switchToNewRoundView();
    }

    /**
     * Executes the switch to Leaderboard view use case.
     */
    @Override
    public void switchToLeaderboardView() {
        menuPresenter.switchToLeaderboardView();
    }
}
