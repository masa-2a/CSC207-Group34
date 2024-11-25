package use_case.leaderboard;

import entity.CommonUser;
import use_case.login.LoginInteractor;
import use_case.login.LoginUserDataAccessInterface;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeaderboardInteractor implements LeaderboardInputBoundary{

    private final LeaderboardOutputBoundary leaderboardOutputBoundary;
    private final LeaderboardUserDataAccessInterface userDataAccessInterface;


    public LeaderboardInteractor(LeaderboardOutputBoundary leaderboardOutputBoundary, LeaderboardUserDataAccessInterface userDataAccessInterface) {
        this.leaderboardOutputBoundary = leaderboardOutputBoundary;
        this.userDataAccessInterface = userDataAccessInterface;
    }

    @Override
    public void execute(LeaderboardInputData inputData){
        List<CommonUser> allUsers = userDataAccessInterface.returnAllUsers();

        if (allUsers.isEmpty()){
            leaderboardOutputBoundary.prepareFailView("No users found");
        }

        List<CommonUser> sortedUsers = allUsers.stream()
                .sorted(Comparator.comparingDouble(CommonUser::getAveragePoints).reversed())
                .toList();

        Map<Integer, CommonUser> topUsers = new HashMap<>();
        int currentUserRank = 1;
        for (int i=0; i<sortedUsers.size(); i++){
            if (sortedUsers.get(i).getName().equals(inputData.getCurrentUsername())) {
                topUsers.put(i+1, sortedUsers.get(i));
                currentUserRank = i+1;
                continue;
                }
            if ( i == 0 || i== 1 || i== 2 ){
                topUsers.put(i+1, sortedUsers.get(i));
            }
        }

        LeaderboardOutputData leaderboardOutputData = new LeaderboardOutputData(topUsers, currentUserRank , inputData.getCurrentUsername() );
        leaderboardOutputBoundary.prepareSuccessView(leaderboardOutputData);

    }
}
