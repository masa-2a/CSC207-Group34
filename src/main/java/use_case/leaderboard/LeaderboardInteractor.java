package use_case.leaderboard;

import entity.CommonUser;
import entity.User;

import java.util.*;

public class LeaderboardInteractor implements LeaderboardInputBoundary{

    private final LeaderboardOutputBoundary leaderboardPresenter;
    private final LeaderboardUserDataAccessInterface userDataAccess;


    public LeaderboardInteractor(LeaderboardOutputBoundary leaderboardPresenter,
                                 LeaderboardUserDataAccessInterface userDataAccess) {
        this.leaderboardPresenter = leaderboardPresenter;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void execute() {
        ArrayList<CommonUser> allUsers = userDataAccess.returnAllUsers();
        List<CommonUser> sortedUsers = sortAllUsers(allUsers);
        String currentUsername = userDataAccess.getCurrentUsername();
        int currentUserRank =0;
        Map<Integer, CommonUser> topThreeUsers = new HashMap<>();
        //find rank of current user AND add top three into map.
        for (int i = 0; i < sortedUsers.size(); i++) {
            if ( i == 0 || i == 1 || i == 2 ) {
                topThreeUsers.put(i+1,sortedUsers.get(i));
            }
            if ( sortedUsers.get(i).getName().equals(currentUsername) ) {
                currentUserRank = i+1;
            }
        }

        LeaderboardOutputData leaderboardOutputData = new LeaderboardOutputData(topThreeUsers, currentUserRank, currentUsername);
        leaderboardPresenter.prepareSuccessView(leaderboardOutputData);
    }

    private List<CommonUser> sortAllUsers(ArrayList<CommonUser> allUsers) {
        return allUsers.stream()
                .sorted(Comparator.comparingDouble(CommonUser::getAveragePoints).reversed())
                .toList();
    }

    @Override
    public void switchToMenuView() {
        leaderboardPresenter.switchToMenuView();
    }
}
