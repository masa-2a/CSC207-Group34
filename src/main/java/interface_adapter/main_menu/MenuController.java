package interface_adapter.main_menu;

import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.menu.MenuInputBoundary;

/**
 * Controller for the Menu Use Case.
 */
public class MenuController {
    private final MenuInputBoundary menuUseCaseInteractor;
    private final LeaderboardInputBoundary leaderboardInteractor;

    public MenuController(MenuInputBoundary menuUseCaseInteractor,
                          LeaderboardInputBoundary leaderboardInteractor) {
        this.menuUseCaseInteractor = menuUseCaseInteractor;
        this.leaderboardInteractor = leaderboardInteractor;
    }

    /**
     * Executes the "switch to LogoutView" Use Case.
     */
    public void switchToLogoutView() {
        menuUseCaseInteractor.switchToLogoutView();
    }

    /**
     * Executes the "switch to Leaderboard" Use Case.
     */
    public void switchToLeaderboardView() {
        menuUseCaseInteractor.switchToLeaderboardView();
    }

    /**
     * Chooses a random location and creates a new StreetViewMap.
     */
    public void switchToNewRoundView() {
        // Switches to the NewRoundView
        menuUseCaseInteractor.switchToNewRoundView();
    }
}
