package interface_adapter.main_menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import use_case.menu.MenuOutputBoundary;

public class MenuPresenter implements MenuOutputBoundary {

    private final MenuViewModel menuViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    //private final LeaderboardViewModel leaderboardViewModel;
    //private final NewRoundViewModel newRoundViewModel;


    public MenuPresenter(MenuViewModel menuViewModel, LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {
        this.menuViewModel = menuViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    /**
     * Switches to the NewRound View.
     */
    @Override
    public void switchToNewRoundView() {

    }

    /**
     * Switches to the Leaderboard View.
     */
    @Override
    public void switchToLeaderboardView() {

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