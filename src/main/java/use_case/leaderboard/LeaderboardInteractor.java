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
//        ArrayList<CommonUser> topUsers = userDataAccessInterface.topUsers();
//        topUsers.add((CommonUser) userDataAccessInterface.getCurrentUser());//does this work doe
//        Map<Integer, CommonUser> leaderboardInfo = new HashMap<>();
//
//        List<CommonUser> sortedUsers = topUsers.stream()
//                .sorted(Comparator.comparingDouble(CommonUser::getAveragePoints).reversed())
//                .toList();
//
//
//        int currentUserRank=0;
//        for (int i=0; i<sortedUsers.size(); i++){
//            if (sortedUsers.get(i).getName().equals(inputData.getCurrentUsername())) {
//                leaderboardInfo.put(i+1, sortedUsers.get(i));
//                currentUserRank = i+1;
//                continue;
//                }
//            if ( i == 0 || i== 1 || i== 2 ){
//                leaderboardInfo.put(i+1, sortedUsers.get(i));
//            }
//        }
//        System.out.println(leaderboardInfo);
//        LeaderboardOutputData leaderboardOutputData = new LeaderboardOutputData(leaderboardInfo, currentUserRank , inputData.getCurrentUsername() );
//        leaderboardOutputBoundary.prepareSuccessView(leaderboardOutputData);
        ArrayList<CommonUser> topUsers = userDataAccess.topUsers();
        CommonUser currentUser = (CommonUser) userDataAccess.getCurrentUser();

        if (currentUser != null) {
            topUsers.add(currentUser); // Add the current user if they exist
        }

        // Sort users by their average points in descending order
        List<CommonUser> sortedUsers = topUsers.stream()
                .sorted(Comparator.comparingDouble(CommonUser::getAveragePoints).reversed())
                .toList();

        // Map for leaderboard data with ranks as keys
        Map<Integer, CommonUser> leaderboardInfo = new HashMap<>();
        int currentUserRank = 0;

        // Populate leaderboard information
        for (int i = 0; i < sortedUsers.size(); i++) {
            CommonUser user = sortedUsers.get(i);
            int rank = i + 1; // 1-based ranking

            // Include the current user and the top 3 in the leaderboard map
            if (rank <= 3 || user.getName().equals(userDataAccess.getCurrentUsername())) {
                leaderboardInfo.put(rank, user);
            }

            // Update the rank of the current user
            if (user.getName().equals(userDataAccess.getCurrentUsername())) {
                currentUserRank = rank;
            }
        }

        // Create output data and pass to output boundary
        LeaderboardOutputData leaderboardOutputData = new LeaderboardOutputData(
                leaderboardInfo,
                currentUserRank,
                userDataAccess.getCurrentUsername()
        );
        leaderboardPresenter.prepareSuccessView(leaderboardOutputData);

    }

    @Override
    public void switchToMenuView() {
        leaderboardPresenter.switchToMenuView();
    }
}
