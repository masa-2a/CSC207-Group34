package interface_adapter.leaderboard;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MenuViewModel;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.leaderboard.LeaderboardOutputData;
import view.ViewManager;

public class LeaderboardPresenter implements LeaderboardOutputBoundary {
    private final LeaderboardViewModel leaderboardViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuViewModel menuViewModel;

    public LeaderboardPresenter(LeaderboardViewModel leaderboardViewModel, ViewManagerModel viewManager, MenuViewModel menuViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.viewManagerModel = viewManager;
        this.menuViewModel = menuViewModel;
    }

    /**
     * Prepares the success view for the leaderboard Use CAse
     *
     * @param outputData a Map of the top users.
     */
    @Override
    public void prepareSuccessView(LeaderboardOutputData outputData) {
        viewManagerModel.setState(leaderboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToMenuView() {
        viewManagerModel.setState(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
