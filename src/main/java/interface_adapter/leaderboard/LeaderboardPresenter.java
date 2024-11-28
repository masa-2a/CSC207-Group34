package interface_adapter.leaderboard;

import entity.CommonUser;
import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MenuState;
import interface_adapter.main_menu.MenuViewModel;
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
        Map<Integer, CommonUser> topUsers = outputData.getTopUsers();
        for(Integer rank : topUsers.keySet()) {
            CommonUser currentUser = topUsers.get(rank);
            if(currentUser.getName().equals(outputData.getCurrentUsername())){
                System.out.println("Were looking at you rn (current logged in)");
                LeaderboardViewModel.setCurrentUserStats(rank, outputData.getCurrentUsername(), currentUser.getAveragePoints() );
            }
            switch(rank) {
                default:
                case 1:
                    LeaderboardViewModel.setFirstPlaceStats(currentUser.getName(), currentUser.getAveragePoints());
                    break;
                case 2:
                    LeaderboardViewModel.setSecondPlaceStats(currentUser.getName(), currentUser.getAveragePoints());
                    break;
                case 3:
                    LeaderboardViewModel.setThirdPlaceStats(currentUser.getName(), currentUser.getAveragePoints());
                    break;
            }
        }
        viewManagerModel.setState(leaderboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.out.println("failed");
    }

    @Override
    public void switchToMenuView() {
        //might have to add something here?
        viewManagerModel.setState(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged("leaderboard");
    }
}
