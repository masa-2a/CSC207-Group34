package interface_adapter.main_menu;

import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInputData;

/**
 * Controller for the Menu Use Case.
 */
public class MenuController {
    private final MenuInputBoundary menuUseCaseInteractor;

    public MenuController(MenuInputBoundary menuUseCaseInteractor) {
        this.menuUseCaseInteractor = menuUseCaseInteractor;
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




}
