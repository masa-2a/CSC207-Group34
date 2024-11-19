package use_case.leaderboard;

import entity.User;
import use_case.login.LoginInteractor;
import use_case.login.LoginUserDataAccessInterface;

public class LeaderboardInteractor {

    private final LeaderboardOutputBoundary leaderboardOutputBoundary;
    private final LeaderboardUserDataAccessInterface userDataAccessInterface;


    public LeaderboardInteractor(LeaderboardOutputBoundary leaderboardOutputBoundary, LeaderboardUserDataAccessInterface userDataAccessInterface) {
        this.leaderboardOutputBoundary = leaderboardOutputBoundary;
        this.userDataAccessInterface = userDataAccessInterface;
    }
}
