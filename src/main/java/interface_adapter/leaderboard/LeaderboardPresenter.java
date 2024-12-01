package interface_adapter.leaderboard;

import CommonUser;
import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MenuViewModel;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.leaderboard.LeaderboardOutputData;

import java.util.Map;

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
        LeaderboardState leaderboardState = leaderboardViewModel.getState();

        Map<Integer, CommonUser> topUsers = outputData.getTopUsers();
        //set current user data
        leaderboardState.setCurrentUserName(outputData.getCurrentUsername()); //setting current username
        leaderboardState.setCurrentUserPoints(outputData.getCurrentUserPoints()); //set current points
        leaderboardState.setCurrentUserPlace(String.valueOf(outputData.getCurrentUserRank())); //set current rank

        for (Integer rank : topUsers.keySet()) {
            CommonUser currentUser = topUsers.get(rank);
            switch (rank) {
                case 1:
                    leaderboardState.setFirstPlaceName(currentUser.getName());
                    leaderboardState.setFirstPlacePoints(currentUser.getPoints());
                    break;
                case 2:
                    leaderboardState.setSecondPlaceName(currentUser.getName());
                    leaderboardState.setSecondPlacePoints(currentUser.getPoints());
                    break;
                case 3:
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
