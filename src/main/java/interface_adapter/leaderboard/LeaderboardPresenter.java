package interface_adapter.leaderboard;

import entity.CommonUser;
import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MenuState;
import interface_adapter.main_menu.MenuViewModel;
import interface_adapter.round.RoundState;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.leaderboard.LeaderboardOutputData;
import view.ViewManager;

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
        for(Integer rank : topUsers.keySet()) {
            CommonUser currentUser = topUsers.get(rank);
            if(currentUser.getName().equals(outputData.getCurrentUsername())){

                leaderboardState.setCurrentUserName(outputData.getCurrentUsername());
                leaderboardState.setCurrentUserAvgPoints(currentUser.getAveragePoints());

            }
            switch(rank) {
                case 1:
                    leaderboardState.setFirstPlaceName(currentUser.getName());
                    leaderboardState.setFirstPlacePoints(currentUser.getAveragePoints());
                    break;
                case 2:
                    leaderboardState.setSecondPlaceName(currentUser.getName());
                    leaderboardState.setSecondPlacePoints(currentUser.getAveragePoints());
                    break;
                case 3:
                    leaderboardState.setThirdPlaceName(currentUser.getName());
                    leaderboardState.setThirdPlacePoints(currentUser.getAveragePoints());
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
