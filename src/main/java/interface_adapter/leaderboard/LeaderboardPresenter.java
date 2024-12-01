package interface_adapter.leaderboard;

import java.util.Map;

import entity.player.CommonUser;
import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MenuViewModel;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.leaderboard.LeaderboardOutputData;

/**
 * Presenter for the leaderboard Use Case.
 */
public class LeaderboardPresenter implements LeaderboardOutputBoundary {
    private final LeaderboardViewModel leaderboardViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuViewModel menuViewModel;

    public LeaderboardPresenter(LeaderboardViewModel leaderboardViewModel, ViewManagerModel viewManager,
                                MenuViewModel menuViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.viewManagerModel = viewManager;
        this.menuViewModel = menuViewModel;
    }

    /**
     * Prepares the success view for the leaderboard Use Case.
     *
     * @param outputData a Map of the top users.
     */
    @Override
    public void prepareSuccessView(LeaderboardOutputData outputData) {
        final LeaderboardState leaderboardState = leaderboardViewModel.getState();

        final Map<Integer, CommonUser> topUsers = outputData.getTopUsers();

        leaderboardState.setCurrentUserName(outputData.getCurrentUsername());
        leaderboardState.setCurrentUserPoints(outputData.getCurrentUserPoints());
        leaderboardState.setCurrentUserPlace(String.valueOf(outputData.getCurrentUserRank()));

        for (Integer rank : topUsers.keySet()) {
            final CommonUser currentUser = topUsers.get(rank);
            switch (rank) {
                case 1:
                    leaderboardState.setFirstPlaceName(currentUser.getName());
                    leaderboardState.setFirstPlacePoints(currentUser.getPoints());
                    break;
                case 2:
                    leaderboardState.setSecondPlaceName(currentUser.getName());
                    leaderboardState.setSecondPlacePoints(currentUser.getPoints());
                    break;
                default:
                    leaderboardState.setThirdPlaceName(currentUser.getName());
                    leaderboardState.setThirdPlacePoints(currentUser.getPoints());
                    break;
            }
        }
        leaderboardViewModel.setState(leaderboardState);
        leaderboardViewModel.firePropertyChanged("Leaderboard State Update");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.out.println("failed");
    }

    @Override
    public void switchToMenuView() {
        viewManagerModel.setState(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
