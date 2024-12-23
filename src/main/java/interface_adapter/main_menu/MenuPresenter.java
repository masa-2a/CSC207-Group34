package interface_adapter.main_menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.round.RoundViewModel;
import use_case.menu.MenuOutputBoundary;
import use_case.menu.MenuOutputData;

/**
 * Presenter for the Menu Use Case.
 */
public class MenuPresenter implements MenuOutputBoundary {

    private final MenuViewModel menuViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LeaderboardViewModel leaderboardViewModel;
    private final RoundViewModel roundViewModel;
  
    public MenuPresenter(MenuViewModel menuViewModel,
                         LoggedInViewModel loggedInViewModel,
                         ViewManagerModel viewManagerModel,
                         RoundViewModel roundViewModel,
                         LeaderboardViewModel leaderboardViewModel) {
        this.menuViewModel = menuViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
        this.roundViewModel = roundViewModel;
        this.leaderboardViewModel = leaderboardViewModel;
    }

    @Override
    public void prepareSuccessView(MenuOutputData outputData) {
        final MenuState menuState = menuViewModel.getState();

        menuState.setCurrentUsername(outputData.getUsername());

        menuViewModel.setState(menuState);
        menuViewModel.firePropertyChanged("User updated");
    }

    /**
     * Switches to the NewRound View.
     */
    @Override
    public void switchToNewRoundView(MenuOutputData menuOutputData) {

        roundViewModel.getState().setUsername(menuViewModel.getState().getCurrentUsername());

        viewManagerModel.setState(roundViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches to the Leaderboard View.
     */
    @Override
    public void switchToLeaderboardView() {
        viewManagerModel.setState(leaderboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches to the LogoutView.
     */
    @Override
    public void switchToLogoutView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
