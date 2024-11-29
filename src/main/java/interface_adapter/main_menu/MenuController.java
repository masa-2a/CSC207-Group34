package interface_adapter.main_menu;

import use_case.menu.MenuInputBoundary;
import java.util.Map;

/**
 * Controller for the Menu Use Case.
 */
public class MenuController {
    private final MenuInputBoundary menuUseCaseInteractor;

    public MenuController(MenuInputBoundary menuUseCaseInteractor) {
        this.menuUseCaseInteractor = menuUseCaseInteractor;
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
    public void switchToLeaderboardView() { menuUseCaseInteractor.switchToLeaderboardView(); }

    /**
     * Chooses a random location and creates a new StreetViewMap
     */
    public void switchToNewRoundView() {
        // Switches to the NewRoundView
        menuUseCaseInteractor.switchToNewRoundView();
    }



}
