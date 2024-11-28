package interface_adapter.main_menu;

import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInputData;
import use_case.round.RoundInputBoundary;

import java.util.Map;

/**
 * Controller for the Menu Use Case.
 */
public class MenuController {
    private final MenuInputBoundary menuUseCaseInteractor;
    private final RoundInputBoundary roundUseCaseInteractor;

    public MenuController(MenuInputBoundary menuUseCaseInteractor,
                          RoundInputBoundary roundUseCaseInteractor) {
        this.menuUseCaseInteractor = menuUseCaseInteractor;
        this.roundUseCaseInteractor = roundUseCaseInteractor;
    }

    /**
     * Executes the Menu Use Case.
     */
    public void execute() {
        final MenuInputData menuInputData = new MenuInputData();

        menuUseCaseInteractor.execute(menuInputData);
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
    }
    /**
     * Executes the "switch to New Round" Use Case.
     */
    public void switchToNewRoundView() {
        menuUseCaseInteractor.switchToNewRoundView();
    }

    /**
     * Chooses a random location and creates a new StreetViewMap
     */
    public void createStreetViewMap() {
        Map.Entry<String, Map<String, String>> randLocation =
                roundUseCaseInteractor.getRandLocation();

    }



}
