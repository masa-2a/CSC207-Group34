package use_case.menu;

public class MenuInteractor implements MenuInputBoundary {
    private final MenuOutputBoundary menuPresenter;
    private MenuOutputData menuOutputData;

    public MenuInteractor(MenuOutputBoundary menuOutputBoundary) {
        this.menuPresenter = menuOutputBoundary;
    }

    /**
     * Executes the menu use case.
     *
     */
    @Override
    public void execute(MenuInputData menuInputData) {

        menuOutputData = new MenuOutputData(menuInputData.getUsername());

        menuPresenter.prepareSuccessView(menuOutputData);
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
        menuPresenter.switchToNewRoundView(menuOutputData);
    }

    /**
     * Executes the switch to Leaderboard view use case.
     */
    @Override
    public void switchToLeaderboardView() {
        menuPresenter.switchToLeaderboardView();
    }
}
