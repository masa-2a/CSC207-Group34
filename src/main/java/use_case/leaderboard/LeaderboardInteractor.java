package use_case.leaderboard;

import java.util.*;

import entity.User.CommonUser;

/**
 * Leaderboard Interactor for the Leaderboard usecase.
 */
public class LeaderboardInteractor implements LeaderboardInputBoundary {

    private final LeaderboardOutputBoundary leaderboardPresenter;
    private final LeaderboardUserDataAccessInterface userDataAccess;

    public LeaderboardInteractor(LeaderboardOutputBoundary leaderboardPresenter,
                                 LeaderboardUserDataAccessInterface userDataAccess) {
        this.leaderboardPresenter = leaderboardPresenter;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void execute(LeaderboardInputData leaderboardInputData) {
        final ArrayList<CommonUser> allUsers = userDataAccess.returnAllUsers();
        final List<CommonUser> sortedUsers = sortAllUsers(allUsers);

        final String currentUsername = userDataAccess.getCurrentUsername();
        int currentUserRank = 0;
        int currentUserPoints = 0;
        final Map<Integer, CommonUser> topThreeUsers = new HashMap<>();
        // find rank of current user AND add top three into map.
        for (int i = 0; i < sortedUsers.size(); i++) {
            if (i == 0 || i == 1 || i == 2) {
                final int rank = i + 1;
                topThreeUsers.put(rank, sortedUsers.get(i));
            }
            if (sortedUsers.get(i).getName().equals(currentUsername)) {
                currentUserRank = i + 1;
                currentUserPoints = sortedUsers.get(i).getPoints();
            }
        }

        final LeaderboardOutputData leaderboardOutputData = new LeaderboardOutputData(topThreeUsers,
                                                                                currentUserRank,
                                                                                currentUsername,
                                                                                currentUserPoints);
        leaderboardPresenter.prepareSuccessView(leaderboardOutputData);
    }

    private List<CommonUser> sortAllUsers(ArrayList<CommonUser> allUsers) {
        return allUsers.stream()
                .sorted(Comparator.comparingDouble(CommonUser::getPoints).reversed())
                .toList();
    }

    @Override
    public void switchToMenuView() {
        leaderboardPresenter.switchToMenuView();
    }
}
