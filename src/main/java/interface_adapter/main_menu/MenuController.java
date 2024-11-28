package interface_adapter.main_menu;

import interface_adapter.round.RoundController;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInputData;
import use_case.round.RoundInputBoundary;

import java.util.Map;

/**
 * Controller for the Menu Use Case.
 */
public class MenuController {
    private final MenuInputBoundary menuUseCaseInteractor;
    private final RoundController roundController;

    public MenuController(MenuInputBoundary menuUseCaseInteractor,
                          RoundController roundController) {
        this.menuUseCaseInteractor = menuUseCaseInteractor;
        this.roundController = roundController;
    }

//    /**
//     * Executes the Menu Use Case.
//     */
//    public void execute() {
//        final MenuInputData menuInputData = new MenuInputData();
//
//        menuUseCaseInteractor.execute(menuInputData);
//    }
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
    public void createNewRound() {
        // Switches to the NewRoundView
        menuUseCaseInteractor.switchToNewRoundView();
        roundController.execute();

    }



}
